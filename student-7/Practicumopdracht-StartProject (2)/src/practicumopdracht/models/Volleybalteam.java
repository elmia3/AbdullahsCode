package practicumopdracht.models;
/**
 * @author Abdullah Elmi
 */
public class Volleybalteam {
    private String naam;
    private int aantalTrofeeën;

    public Volleybalteam(String naam, int aantalTrofeeën) {
        this.naam = naam;
        this.aantalTrofeeën = aantalTrofeeën;
    }



    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getAantalTrofeeën() {
        return aantalTrofeeën;
    }

    public void setAantalTrofeeën(int aantalTrofeeën) {
        this.aantalTrofeeën = aantalTrofeeën;
    }
    public String toString() {
        return String.format("Team: %s \n Aantal Trofeeën: %d", naam, aantalTrofeeën);
    }
}