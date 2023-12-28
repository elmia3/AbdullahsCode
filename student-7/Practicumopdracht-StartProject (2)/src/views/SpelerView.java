package views;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Speler;

import java.time.LocalDate;
/**
 * @author Abdullah Elmi
 */
public class SpelerView extends View {
    private TextField naam;

    private DatePicker geboortedatum;
    private TextField salaris;
    private TextField positie;
    private TextField lengte;
    private TextField spike;
    private CheckBox heeftBlessure;

    //Hier komen de buttons en mijn labels te staan
    private Button nieuw;
    private Button opslaan;
    private Button verwijderen;
    private Button schakelen;
    private Label naamLabel;
    private Label geboortedatumLabel;
    private Label salarisLabel;
    private Label positieLabel;
    private Label lengteLabel;
    private Label spikeLabel;
    private Label heeftBlessureLabel;
    private Label ComboBoxLabel;

    private ListView<Speler> SpelerListView;
    private ComboBox VolleybalteamComboBox;

    private HBox radioHbox;

    private RadioButton spikeHoogLaag;
    private RadioButton spikeLaagHoog;
    private RadioButton spelerNaamA_Z;
    private RadioButton spelerNaamZ_A;

    private HBox SpelerHbox;
    private VBox SpelerVbox;
    private GridPane spelerGridPane;


    protected Parent initializeview() {
        naam = new TextField();
        geboortedatum = new DatePicker();
        salaris = new TextField();
        positie = new TextField();
        lengte = new TextField();
        spike = new TextField();
        heeftBlessure = new CheckBox();


        nieuw = new Button("Nieuw");
        opslaan = new Button("Opslaan");
        verwijderen = new Button("Verwijderen");
        schakelen = new Button("Schakelen");
        naamLabel = new Label("Naam");
        geboortedatumLabel = new Label("Geboortedatum:");
        positieLabel = new Label("Positie");
        lengteLabel = new Label("Lengte");
        spikeLabel = new Label("Spike");
        salarisLabel = new Label("Salaris");
        heeftBlessureLabel = new Label("Heeft een blessure");
        ComboBoxLabel = new Label("Volleybalteam");

        spelerGridPane = new GridPane();


        SpelerListView = new ListView<>();
        VolleybalteamComboBox = new ComboBox<>();
        radioHbox = new HBox();
        spikeHoogLaag = new RadioButton("Spike Hoog-Laag");
        spikeLaagHoog = new RadioButton("Spike Laag-Hoog");
        spelerNaamA_Z = new RadioButton("Naam A-Z");
        spelerNaamZ_A = new RadioButton("Naam Z-A");
        SpelerHbox = new HBox();
        SpelerVbox = new VBox();


        radioHbox.getChildren().addAll(spikeHoogLaag, spikeLaagHoog, spelerNaamA_Z, spelerNaamZ_A);
        spelerGridPane.add(ComboBoxLabel, 0, 1);
        spelerGridPane.add(VolleybalteamComboBox, 1, 1);

        spelerGridPane.add(naamLabel, 0, 2);
        spelerGridPane.add(naam, 1, 2);

        spelerGridPane.add(geboortedatumLabel, 0, 3);
        spelerGridPane.add(geboortedatum, 1, 3);

        spelerGridPane.add(salarisLabel, 0, 4);
        spelerGridPane.add(salaris, 1, 4);

        spelerGridPane.add(positieLabel, 0, 5);
        spelerGridPane.add(positie, 1, 5);

        spelerGridPane.add(lengteLabel, 0, 6);
        spelerGridPane.add(lengte, 1, 6);

        spelerGridPane.add(spikeLabel, 0, 7);
        spelerGridPane.add(spike, 1, 7);

        spelerGridPane.add(heeftBlessureLabel, 0, 8);
        spelerGridPane.add(heeftBlessure, 1, 8);

        spelerGridPane.add(opslaan, 1, 9);
        spelerGridPane.add(radioHbox,1,10);
        spelerGridPane.add(SpelerListView, 1, 11);
        opslaan.setPrefWidth(400);
        nieuw.setPrefWidth(140);
        schakelen.setPrefWidth(140);
        verwijderen.setPrefWidth(140);

        SpelerHbox.getChildren().addAll(nieuw, verwijderen, schakelen);
        SpelerHbox.setSpacing(5);
        SpelerHbox.setAlignment(Pos.CENTER);
        spelerGridPane.add(SpelerHbox, 1, 12);


        SpelerVbox.getChildren().addAll(spelerGridPane);
        return SpelerVbox;

    }

    public TextField getNaam() {
        return naam;
    }

    public void setNaam(TextField naam) {
        this.naam = naam;
    }

    public DatePicker getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(DatePicker geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public TextField getSalaris() {
        return salaris;
    }

    public void setSalaris(TextField salaris) {
        this.salaris = salaris;
    }

    public TextField getPositie() {
        return positie;
    }

    public void setPositie(TextField positie) {
        this.positie = positie;
    }

    public TextField getLengte() {
        return lengte;
    }

    public void setLengte(TextField lengte) {
        this.lengte = lengte;
    }

    public TextField getSpike() {
        return spike;
    }

    public void setSpike(TextField spike) {
        this.spike = spike;
    }

    public CheckBox getHeeftBlessure() {
        return heeftBlessure;
    }

    public void setHeeftBlessure(CheckBox heeftBlessure) {
        this.heeftBlessure = heeftBlessure;
    }

    public Button getNieuw() {
        return nieuw;
    }

    public void setNieuw(Button nieuw) {
        this.nieuw = nieuw;
    }

    public Button getOpslaan() {
        return opslaan;
    }

    public void setOpslaan(Button opslaan) {
        this.opslaan = opslaan;
    }

    public Button getVerwijderen() {
        return verwijderen;
    }

    public void setVerwijderen(Button verwijderen) {
        this.verwijderen = verwijderen;
    }

    public Button getSchakelen() {
        return schakelen;
    }

    public void setSchakelen(Button schakelen) {
        this.schakelen = schakelen;
    }

    public Label getNaamLabel() {
        return naamLabel;
    }

    public void setNaamLabel(Label naamLabel) {
        this.naamLabel = naamLabel;
    }

    public Label getSalarisLabel() {
        return salarisLabel;
    }

    public void setSalarisLabel(Label salarisLabel) {
        this.salarisLabel = salarisLabel;
    }

    public Label getLengteLabel() {
        return lengteLabel;
    }

    public void setLengteLabel(Label lengteLabel) {
        this.lengteLabel = lengteLabel;
    }

    public Label getHeeftBlessureLabel() {
        return heeftBlessureLabel;
    }

    public void setHeeftBlessureLabel(Label heeftBlessureLabel) {
        this.heeftBlessureLabel = heeftBlessureLabel;
    }

    public Label getComboBoxLabel() {
        return ComboBoxLabel;
    }

    public void setComboBoxLabel(Label comboBoxLabel) {
        ComboBoxLabel = comboBoxLabel;
    }

    public ListView<Speler> getSpelerListView() {
        return SpelerListView;
    }

    public void setSpelerListView(ListView<Speler> SpelerListView) {
        this.SpelerListView = SpelerListView;
    }

    public ComboBox getVolleybalteamComboBox() {
        return VolleybalteamComboBox;
    }

    public void setVolleybalteamComboBox(ComboBox VolleybalteamComboBox) {
        this.VolleybalteamComboBox = VolleybalteamComboBox;
    }

    public RadioButton getSpikeHoogLaag() {
        return spikeHoogLaag;
    }

    public void setSpikeHoogLaag(RadioButton spikeHoogLaag) {
        this.spikeHoogLaag = spikeHoogLaag;
    }

    public RadioButton getSpikeLaagHoog() {
        return spikeLaagHoog;
    }

    public void setSpikeLaagHoog(RadioButton spikeLaagHoog) {
        this.spikeLaagHoog = spikeLaagHoog;
    }

    public RadioButton getSpelerNaamA_Z() {
        return spelerNaamA_Z;
    }

    public void setSpelerNaamA_Z(RadioButton spelerNaamA_Z) {
        this.spelerNaamA_Z = spelerNaamA_Z;
    }

    public RadioButton getSpelerNaamZ_A() {
        return spelerNaamZ_A;
    }

    public void setSpelerNaamZ_A(RadioButton spelerNaamZ_A) {
        this.spelerNaamZ_A = spelerNaamZ_A;
    }
}
