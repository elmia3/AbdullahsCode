package practicumopdracht.controllers;

import comparators.VolleybalteamComparator;
import data.SpelerDAO;
import data.VolleybalteamDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Speler;
import practicumopdracht.models.Volleybalteam;
import views.View;
import views.VolleybalteamView;

import java.util.List;
import java.util.Optional;
/**
 * @author Abdullah Elmi
 */
public class VolleybalteamController extends Controller {
    private VolleybalteamView volleybalteamView;
    private SpelerDAO spelerDAO;
    private VolleybalteamDAO volleybalteamDAO;
    private ObservableList<Volleybalteam> volleybalteamObservableList;

    public VolleybalteamController() {
        volleybalteamView = new VolleybalteamView();
        spelerDAO = MainApplication.getSpelerDAO();
        volleybalteamDAO = MainApplication.getVolleybalteamDAO();
        volleybalteamView.getSchakelen().setOnAction(actionEvent -> paginaVeranderen());
        volleybalteamView.getNieuw().setOnAction(actionEvent -> nieuwAlert());
        volleybalteamView.getVerwijderen().setOnAction(actionEvent -> verwijderKnop());
        volleybalteamView.getOpslaan().setOnAction(actionEvent -> opslaanFout());
        volleybalteamView.getLaadMenuItem().setOnAction(actionEvent -> laadMenu());
        volleybalteamView.getOpslaanMenuItem().setOnAction(actionEvent -> opslaanMenu());
        volleybalteamView.getAfsluitenMenuItem().setOnAction(actionEvent -> afsluitenMenu());
        volleybalteamView.getaSorteren().setOnAction(actionEvent -> soorteerNaamA_Z());
        volleybalteamView.getzSorteren().setOnAction(actionEvent -> soorteerNaamZ_A());
        input();
        listView();
        soorteerNaamA_Z();
    }

    public Volleybalteam kiesTeam() {
        return volleybalteamView.getvolleybalteamListView().getSelectionModel().getSelectedItem();
    }

    /**
     * zorgt ervoor dat de pagina schakelt
     */
    public void paginaVeranderen() {
        if (kiesTeam() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Geen toegang");
            alert.setHeaderText("Je moet een team hebben geselecteerd!");
            alert.showAndWait();
            return;
        }
        MainApplication.switchController(new SpelerController(kiesTeam()));
    }

    public void veldenLegen() {
        volleybalteamView.getNaam().clear();
        volleybalteamView.getAantalTrofeeën().clear();
        volleybalteamView.getvolleybalteamListView().getSelectionModel().clearSelection();
    }
    private void input() {
        volleybalteamView.getvolleybalteamListView().getSelectionModel().selectedItemProperty().addListener(((observableValue, basketbalteam, t1) ->
        {
            Volleybalteam volleybalteam = t1;
            if (volleybalteam == null) {

            } else {
                volleybalteamView.getNaam().setText(volleybalteam.getNaam());
                volleybalteamView.getAantalTrofeeën().setText(String.valueOf(volleybalteam.getAantalTrofeeën()));
            }

        }));

    }

    /**
     * een functie voor het 'nieuw' knop
     */
    public void nieuwAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("nieuw");
        alert.setHeaderText(" je maakt nu een nieuw team");
        alert.setResizable(false);
        alert.setContentText("Kies Ok of Cancel deze alert");
        Optional<ButtonType> nieuw = alert.showAndWait();
        if (nieuw.get().equals(ButtonType.OK)) {
            veldenLegen();
        }
    }

    /**
     * een functie voor het 'verwijder' knop
     */
    public void verwijderKnop() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        Volleybalteam volleybalteam = volleybalteamView.getvolleybalteamListView().getSelectionModel().getSelectedItem();

        List<Speler> spelerList = spelerDAO.getAllFor(volleybalteam);
        alert.setTitle("Verwijderen");
        alert.setHeaderText("Wordt verwijderd");
        alert.setResizable(false);
        alert.setContentText("Kies Ok of Cancel deze alert");
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().remove(ButtonType.CANCEL);
        Optional<ButtonType> verwijder = alert.showAndWait();
        if (verwijder.get().equals(ButtonType.YES)) {
            volleybalteamDAO.remove(volleybalteam);
            for (Speler speler : spelerList) {
                spelerDAO.remove(speler);
            }
            volleybalteamView.getvolleybalteamListView().getItems().remove(volleybalteam);
            veldenLegen();
        }

    }
    public void opslaanFout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Foutmelding");
        String naamVeld = volleybalteamView.getNaam().getText();

        String aantalTrofeeënVeld = volleybalteamView.getAantalTrofeeën().getText();

        StringBuilder stringBuilder = new StringBuilder();
        if (naamVeld.isBlank()) {
            stringBuilder.append("-Naam is verplicht!");
            volleybalteamView.getNaam().setStyle("-fx-border-color: red");
        }
        if (aantalTrofeeënVeld.isBlank() || !aantalTrofeeënVeld.matches("\\d+")) {
            stringBuilder.append("\n-Aantal trofeeën is verplicht of wat je invoert is geen getal!!");
            volleybalteamView.getAantalTrofeeën().setStyle("-fx-border-color: red");
        } else {
            try {
                int trofee = Integer.parseInt(aantalTrofeeënVeld);
                System.out.println(trofee);
            } catch (NumberFormatException exception) {
                stringBuilder.append(" Wat je invoert is geen getal! \n");
                volleybalteamView.getAantalTrofeeën().setStyle("-fx-border-color: red");
            }
        }
//        Label t = new Label(stringBuilder.toString());
//        alert.getDialogPane().setContent(t);
//        alert.showAndWait();
        if (stringBuilder.isEmpty()) {
            Volleybalteam volleybalteam = volleybalteamView.getvolleybalteamListView().getSelectionModel().getSelectedItem();
            if (volleybalteam== null) {
                volleybalteam = new Volleybalteam(naamVeld, Integer.parseInt(aantalTrofeeënVeld));
                volleybalteamView.getvolleybalteamListView().getItems().add(volleybalteam);
            } else {
                volleybalteam.setNaam(naamVeld);
                volleybalteam.setAantalTrofeeën(Integer.parseInt(aantalTrofeeënVeld));

                volleybalteamView.getvolleybalteamListView().refresh();
            }
            volleybalteamDAO.addOrUpdate(volleybalteam);
        } else {
            alert.setHeaderText(stringBuilder.toString());
            alert.setContentText("Kies Ok of Cancel deze alert.");
            alert.showAndWait();

        }
    }

    private void laadMenu() {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        Alert alertConf = new Alert(Alert.AlertType.CONFIRMATION);

        alertConf.setTitle("Laden");
        alertConf.setHeaderText("Wil je je teams inladen?");
        alertConf.setContentText("als je op yes klikt worden je Basketbalteams en Basketbalspelers ingeladen");
        alertConf.getButtonTypes().add(ButtonType.NO);
        alertConf.getButtonTypes().add(ButtonType.YES);
        alertConf.getButtonTypes().remove(ButtonType.CANCEL);
        alertConf.getButtonTypes().remove(ButtonType.OK);

        Optional<ButtonType> antwoord = alertConf.showAndWait();

        if (antwoord.get().equals(ButtonType.YES)) {
            MainApplication.getSpelerDAO().load();
            MainApplication.getVolleybalteamDAO().load();
            listView();
            alertInfo.setTitle(" je opgeslaagte data");
            alertInfo.setHeaderText(" je hebt de album info");
        } else {
            alertInfo.setTitle("niet ingeladen");
        }

    }

    private void opslaanMenu() {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        Alert alertConf = new Alert(Alert.AlertType.CONFIRMATION);

        alertConf.setTitle("Opslaan");
        alertConf.setHeaderText("wil je deze team opslaan");
        alertConf.setContentText("Weet je het zeker?");
        alertConf.getButtonTypes().add(ButtonType.NO);
        alertConf.getButtonTypes().add(ButtonType.YES);
        alertConf.getButtonTypes().remove(ButtonType.CANCEL);
        alertConf.getButtonTypes().remove(ButtonType.OK);

        Optional<ButtonType> antwoord = alertConf.showAndWait();
/**
 * als het antwoord ja is sla ik de Volleybalteam en Speler te gelijk op
 */
        if (antwoord.get().equals(ButtonType.YES)) {
            MainApplication.getVolleybalteamDAO().save();
            MainApplication.getSpelerDAO().save();
            alertInfo.setTitle(" opgeslagen");
            alertInfo.setHeaderText(" je hebt het team opgeslagen");
        } else {
            alertInfo.setTitle("niet opgeslagen");
        }


    }

    private void afsluitenMenu() {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        Alert alertConf = new Alert(Alert.AlertType.CONFIRMATION);

        alertConf.setTitle("Afsluiten");
        alertConf.setHeaderText(" Opslaan?");
        alertConf.setContentText("Moet de data nog eenmalig opgeslagen worden? ");
        alertConf.getButtonTypes().add(ButtonType.NO);
        alertConf.getButtonTypes().add(ButtonType.YES);
        alertConf.getButtonTypes().remove(ButtonType.CANCEL);
        alertConf.getButtonTypes().remove(ButtonType.OK);
        Optional<ButtonType> antwoord = alertConf.showAndWait();
        if (antwoord.get().equals(ButtonType.YES)) {
            opslaanMenu();
            alertInfo.setTitle("Opgeslagen data");
            Platform.exit();
        } else {
            alertInfo.setTitle("je data is niet opgeslagen");
            Platform.exit();
        }
    }


    /**
     * zorgt voor de listview
     */
    private void listView() {
        volleybalteamObservableList = FXCollections.observableArrayList(volleybalteamDAO.getAll());
        volleybalteamView.getvolleybalteamListView().setItems(volleybalteamObservableList);}

    private void soorteerNaamA_Z() {
        FXCollections.sort(volleybalteamView.getvolleybalteamListView().getItems(), new VolleybalteamComparator());

    }

    private void soorteerNaamZ_A() {
        FXCollections.sort(volleybalteamView.getvolleybalteamListView().getItems(), new VolleybalteamComparator().reversed());

    }




    public View getView() {
        return volleybalteamView;
    }
}
