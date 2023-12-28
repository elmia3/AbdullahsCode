package practicumopdracht.controllers;


import comparators.SpelerNaamComparator;
import comparators.SpelerSpikeComparator;
import data.DAO;
import data.SpelerDAO;
import data.VolleybalteamDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Speler;
import practicumopdracht.models.Volleybalteam;
import views.SpelerView;
import views.View;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Abdullah Elmi
 */

public class SpelerController extends Controller {
    private SpelerView spelerView;
    private SpelerDAO spelerDAO;
    private VolleybalteamDAO volleybalteamDAO;
    private ObservableList<Speler> spelerObservableList;
    private ObservableList<Volleybalteam> volleybalteamObservableList;


    public SpelerController(Volleybalteam volleybalteam) {
        spelerView = new SpelerView();
        spelerDAO = MainApplication.getSpelerDAO();
        volleybalteamDAO = MainApplication.getVolleybalteamDAO();
        spelerView.getSchakelen().setOnAction(actionEvent -> paginaVeranderen());
        comboBoxVeld(volleybalteam);
        spelerView.getNieuw().setOnAction(actionEvent -> nieuwAlert());
        spelerView.getVerwijderen().setOnAction(actionEvent -> verwijderKnop());
        spelerView.getOpslaan().setOnAction(actionEvent -> opslaanFout());
        spelerView.getVolleybalteamComboBox().setOnAction(actionEvent -> comboboxListview());
        spelerView.getSpikeHoogLaag().setOnAction(actionEvent -> beoordeling_hoogNaarLaag());
        spelerView.getSpikeLaagHoog().setOnAction(actionEvent -> beoordeling_laagNaarHoog());
        spelerView.getSpelerNaamA_Z().setOnAction(actionEvent -> naamSoorteringA_Z());
        spelerView.getSpelerNaamZ_A().setOnAction(actionEvent -> naamSoorteringZ_A());
        input();
        naamSoorteringA_Z();
        lijstTonen(volleybalteam);

    }

    /**
     * zorgt ervoor dat de pagina schakelt
     */
    public void paginaVeranderen() {
        MainApplication.switchController(new VolleybalteamController());
    }

    /**
     * laat het gekozen basketbalteam zien
     *
     * @param volleybalteam
     */
    private void comboBoxVeld(Volleybalteam volleybalteam) {
        volleybalteamObservableList = FXCollections.observableArrayList(volleybalteamDAO.getAll());
        spelerView.getVolleybalteamComboBox().setItems(volleybalteamObservableList);
        spelerView.getVolleybalteamComboBox().setValue(volleybalteam);
    }

    private void comboboxListview(){
        Volleybalteam geselecteerdeVolleybalteam = (Volleybalteam) spelerView.getVolleybalteamComboBox().getValue();
        lijstTonen(geselecteerdeVolleybalteam);
        veldenLegen();
    }

    /**
     * zorgt ervoor dat de inputvelden worden geleegd
     */
    public void veldenLegen() {
        spelerView.getNaam().clear();
        spelerView.getGeboortedatum().getEditor().clear();
        spelerView.getLengte().clear();
        spelerView.getSalaris().clear();
        spelerView.getSpike().clear();
        spelerView.getPositie().clear();
        spelerView.getHeeftBlessure().setSelected(false);
    }

    /**
     * een functie voor het 'nieuw' knop
     */
    public void nieuwAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nieuw");
        alert.setHeaderText("Je maakt nu een nieuwe speler");
        alert.setResizable(false);
        alert.setContentText("Kies Ok of Cancel deze alert.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            veldenLegen();
        }
    }

    /**
     * een functie voor het 'verwijder' knop
     */
    public void verwijderKnop() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Speler volleybalspeler = spelerView.getSpelerListView().getSelectionModel().getSelectedItem();
        alert.setTitle("Verwijderen");
        alert.setHeaderText("Wordt verwijderd");
        alert.setResizable(false);
        alert.setContentText("Klik op Okay of Cancel deze alert");
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().remove(ButtonType.CANCEL);
        Optional<ButtonType> verwijderSpeler = alert.showAndWait();
        if (verwijderSpeler.get().equals(ButtonType.YES)) {
            spelerDAO.remove(volleybalspeler);
            spelerView.getSpelerListView().getItems().remove(volleybalspeler);
            veldenLegen();
        }
    }

    /**
     * validatie voor het opslaan knop
     */
    public void opslaanFout() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Foutmelding");
        String naamVeld = spelerView.getNaam().getText();
        String salarisVeld = spelerView.getSalaris().getText();
        String lengteVeld = spelerView.getLengte().getText();
        LocalDate geboortedatumVeld = spelerView.getGeboortedatum().getValue();
        String positieVeld = spelerView.getPositie().getText();
        String spikeVeld = spelerView.getSpike().getText();
        boolean heeftBlessureVeld = spelerView.getHeeftBlessure().isSelected();

        StringBuilder stringBuilder = new StringBuilder();
        if (naamVeld.isBlank()) {
            stringBuilder.append("-Naam is verplicht!");
            spelerView.getNaam().setStyle("-fx-border-color: red");
        }
        if (geboortedatumVeld == null || geboortedatumVeld.getYear() >= 2100) {
            stringBuilder.append("\n-Datum is ongeldig of niet ingevuld!");
            spelerView.getGeboortedatum().setStyle("-fx-border-color: red");
        }
        if (salarisVeld.isBlank()) {
            stringBuilder.append("\n-Salaris is verplicht!");
            spelerView.getSalaris().setStyle("-fx-border-color: red");
        } else {
            try {
                double salaris = Double.parseDouble(salarisVeld);
                if (salaris < 0) {
                    stringBuilder.append("\n-Salaris is ongeldig!");
                    spelerView.getSalaris().setStyle("-fx-border-color: red");
                }
            } catch (NumberFormatException e) {
                stringBuilder.append("\n-Salaris is ongeldig!");
                spelerView.getSalaris().setStyle("-fx-border-color: red");
            }
        }

        if (lengteVeld.isBlank()) {
            stringBuilder.append("\n-Lengte is verplicht!");
            spelerView.getLengte().setStyle("-fx-border-color: red");
        } else {
            try {
                double lengte = Double.parseDouble(lengteVeld);
                if (lengte < 1.50 || lengte > 2.46) {
                    stringBuilder.append("\n-Lengte is ongeldig!");
                    spelerView.getLengte().setStyle("-fx-border-color: red");
                }
            } catch (NumberFormatException e) {
                stringBuilder.append("\n-Lengte is ongeldig!");
                spelerView.getLengte().setStyle("-fx-border-color: red");
            }
        }

        if (spikeVeld.isBlank()) {
            stringBuilder.append("\n-Spike is verplicht!");
            spelerView.getSpike().setStyle("-fx-border-color: red");
        } else {
            try {
                int spike = Integer.parseInt(spikeVeld);
                if (spike < 175) {
                    stringBuilder.append("\n-Spike is ongeldig!");
                    spelerView.getSpike().setStyle("-fx-border-color: red");
                }
            } catch (NumberFormatException e) {
                stringBuilder.append("\n-Spike is ongeldig!");
                spelerView.getSpike().setStyle("-fx-border-color: red");
            }
        }
        if (positieVeld.isBlank()) {
            stringBuilder.append("\n-Positie is verplicht!");
            spelerView.getPositie().setStyle("-fx-border-color: red");
        }
        if (stringBuilder.isEmpty()) {
            Volleybalteam volleybalteam = (Volleybalteam) spelerView.getVolleybalteamComboBox().getSelectionModel().getSelectedItem();
            Speler volleybalspeler = spelerView.getSpelerListView().getSelectionModel().getSelectedItem();
            if (volleybalspeler == null) {
                volleybalspeler = new Speler(volleybalteam,naamVeld, Double.parseDouble(salarisVeld), positieVeld, Double.parseDouble(lengteVeld), geboortedatumVeld, Integer.parseInt(spikeVeld), heeftBlessureVeld);
                spelerView.getSpelerListView().getItems().add(volleybalspeler);
            } else {

                volleybalspeler.setNaam(naamVeld);
                volleybalspeler.setGeboortedatum(geboortedatumVeld);
                volleybalspeler.setSalaris(Double.parseDouble(salarisVeld));
                volleybalspeler.setLengte(Double.parseDouble(lengteVeld));
                volleybalspeler.setHeeftBlessure(heeftBlessureVeld);
                volleybalspeler.setSpike(Integer.parseInt(spikeVeld));
                volleybalspeler.setPositie(positieVeld);
                spelerView.getSpelerListView().refresh();
            }
            spelerDAO.addOrUpdate(volleybalspeler);


        } else {

            alert.setHeaderText(stringBuilder.toString());
            alert.setContentText("Kies Ok of Cancel deze alert");
            alert.showAndWait();

        }

    }

    private void lijstTonen(Volleybalteam volleybalteam) {
        spelerObservableList = FXCollections.observableArrayList(MainApplication.getSpelerDAO().getAllFor(volleybalteam));
        spelerView.getSpelerListView().setItems(spelerObservableList);
    }

    /**
     * het invoeren in de listview
     */
    private void input() {
        spelerView.getSpelerListView().getSelectionModel().selectedItemProperty().addListener(((observableValue, oudSpeler, t1) -> {
            if (t1 == null) {
            } else {
                spelerView.getNaam().setText(t1.getNaam());
                spelerView.getGeboortedatum().setValue(t1.getGeboortedatum());
                spelerView.getSalaris().setText(String.valueOf(t1.getSalaris()));
                spelerView.getPositie().setText(t1.getPositie());
                spelerView.getLengte().setText(String.valueOf(t1.getLengte()));
                spelerView.getSpike().setText(String.valueOf(t1.getSpike()));
                spelerView.getHeeftBlessure().setSelected(t1.isHeeftBlessure());
            }
        }));
    }
    private void beoordeling_hoogNaarLaag(){
        FXCollections.sort(spelerView.getSpelerListView().getItems(),new SpelerSpikeComparator().reversed());
        spelerView.getSpelerNaamA_Z().setSelected(false);
        spelerView.getSpelerNaamZ_A().setSelected(false);
        spelerView.getSpikeLaagHoog().setSelected(false);
    }
    private void beoordeling_laagNaarHoog(){
        FXCollections.sort(spelerView.getSpelerListView().getItems(),new SpelerSpikeComparator());
        spelerView.getSpelerNaamA_Z().setSelected(false);
        spelerView.getSpelerNaamZ_A().setSelected(false);
        spelerView.getSpikeHoogLaag().setSelected(false);
    }

    private void naamSoorteringA_Z(){
        FXCollections.sort(spelerView.getSpelerListView().getItems(),new SpelerNaamComparator());
        spelerView.getSpelerNaamZ_A().setSelected(false);
        spelerView.getSpikeHoogLaag().setSelected(false);
        spelerView.getSpikeLaagHoog().setSelected(false);
    }
    private void naamSoorteringZ_A(){
        FXCollections.sort(spelerView.getSpelerListView().getItems(),new SpelerNaamComparator().reversed());
        spelerView.getSpelerNaamA_Z().setSelected(false);
        spelerView.getSpikeHoogLaag().setSelected(false);
        spelerView.getSpikeLaagHoog().setSelected(false);
    }


    @Override
    public View getView() {
        return spelerView;
    }
}
