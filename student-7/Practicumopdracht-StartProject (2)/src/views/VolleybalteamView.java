package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import practicumopdracht.models.Volleybalteam;
/**
 * @author Abdullah Elmi
 */
public class VolleybalteamView extends View {

    private TextField naam;
    private TextField aantalTrofeeën;

    private Button nieuw;
    private Button opslaan;
    private Button verwijderen;
    private Button schakelen;

    private Label naamLabel;
    private Label aantalTrofeeënLabel;

    private HBox volleybalteamHbox;
    private VBox volleybalteamVbox;
    private VBox topVbox;
    private GridPane volleybalteamGridPane;
    private BorderPane borderPane;
    private MenuBar menuBar;
    private MenuItem laadMenuItem;
    private MenuItem opslaanMenuItem;
    private MenuItem afsluitenMenuItem;
    private Menu bestandMenu;
    private Menu sorteerMenu;
// Mijn Radio Buttons
    private MenuItem aSorteren;
    private MenuItem zSorteren;
    private ListView<Volleybalteam> volleybalteamListView;

    @Override
    protected Parent initializeview() {
        naam = new TextField();
        aantalTrofeeën = new TextField();

        nieuw = new Button("Nieuw");
        opslaan = new Button("Opslaan");
        verwijderen = new Button("Verwijderen");
        schakelen = new Button("Schakelen");
        naamLabel = new Label("Teamnaam");
        aantalTrofeeënLabel = new Label("Aantal Trofeeën");
        volleybalteamListView = new ListView<>();

        volleybalteamListView.setPrefWidth(640);
        volleybalteamListView.setPrefHeight(200);

        volleybalteamHbox = new HBox(10);
        volleybalteamHbox.setAlignment(Pos.CENTER);
        volleybalteamHbox.getChildren().addAll(nieuw, verwijderen, schakelen);

        volleybalteamGridPane = new GridPane();
        volleybalteamGridPane.setAlignment(Pos.CENTER);
        volleybalteamGridPane.setHgap(5);
        volleybalteamGridPane.setVgap(5);
        volleybalteamGridPane.setPadding(new Insets(10));

        volleybalteamGridPane.add(naamLabel, 0, 0);
        volleybalteamGridPane.add(naam, 1, 0);
        volleybalteamGridPane.add(aantalTrofeeënLabel, 0, 1);
        volleybalteamGridPane.add(aantalTrofeeën, 1, 1);
        volleybalteamGridPane.add(opslaan, 1, 2);
        volleybalteamGridPane.add(volleybalteamListView, 1, 3);
        volleybalteamGridPane.add(volleybalteamHbox, 1, 4);

        topVbox = new VBox(10);
        topVbox.getChildren().add(new BorderPane(menuBar));

        volleybalteamVbox = new VBox(10);
        volleybalteamVbox.setPadding(new Insets(20));
        volleybalteamVbox.getChildren().addAll(topVbox, volleybalteamGridPane);

        borderPane = new BorderPane();
        menuBar = new MenuBar();
        bestandMenu = new Menu("Bestand");
        laadMenuItem = new MenuItem("Laad");
        opslaanMenuItem = new MenuItem("Opslaan");
        afsluitenMenuItem = new MenuItem("Afsluiten");
        sorteerMenu = new Menu("Sorteren");
        aSorteren = new MenuItem("A-Z");
        zSorteren = new MenuItem("Z-A");
        sorteerMenu.getItems().addAll(aSorteren, zSorteren);
        bestandMenu.getItems().addAll(laadMenuItem, opslaanMenuItem, afsluitenMenuItem);
        menuBar.getMenus().addAll(bestandMenu, sorteerMenu);
        borderPane.setTop(menuBar);
        topVbox.getChildren().addAll(borderPane);

        return volleybalteamVbox;
    }

    public TextField getNaam() {
        return naam;
    }

    public TextField getAantalTrofeeën() {
        return aantalTrofeeën;
    }

    public Button getNieuw() {
        return nieuw;
    }

    public Button getOpslaan() {
        return opslaan;
    }

    public Button getVerwijderen() {
        return verwijderen;
    }

    public Button getSchakelen() {
        return schakelen;
    }

    public MenuItem getLaadMenuItem() {
        return laadMenuItem;
    }

    public MenuItem getOpslaanMenuItem() {
        return opslaanMenuItem;
    }

    public MenuItem getAfsluitenMenuItem() {
        return afsluitenMenuItem;
    }

    public ListView<Volleybalteam> getvolleybalteamListView() {
        return volleybalteamListView;
    }

    public Label getNaamLabel() {
        return naamLabel;
    }

    public Menu getBestandMenu() {
        return bestandMenu;
    }

    public Menu getSorteerMenu() {
        return sorteerMenu;
    }

    public MenuItem getaSorteren() {
        return aSorteren;
    }

    public MenuItem getzSorteren() {
        return zSorteren;
    }
}
