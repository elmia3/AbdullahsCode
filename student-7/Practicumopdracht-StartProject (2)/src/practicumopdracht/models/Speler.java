package practicumopdracht.models;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * @author Abdullah Elmi
 */
public class Speler implements Serializable {
    private transient Volleybalteam hoortBij;
    private String naam;
    private double salaris;
    private String positie;
    private double lengte;
    private LocalDate geboortedatum;
    private int spike;
    private boolean heeftBlessure;

    public Speler(Volleybalteam hoortBij, String naam, double salaris, String positie, double lengte, LocalDate geboortedatum, int spike, boolean heeftBlessure) {
        this.hoortBij = hoortBij;
        this.naam = naam;
        this.salaris = salaris;
        this.positie = positie;
        this.lengte = lengte;
        this.geboortedatum = geboortedatum;
        this.spike = spike;
        this.heeftBlessure = heeftBlessure;
    }

    public Speler(String naamVeld, double salarisVeld, String positieVeld, double lengteVeld, LocalDate geboortedatumVeld, int spikeVeld, boolean heeftBlessureVeld) {
        this.naam = naamVeld;
        this.salaris = salarisVeld;
        this.positie = positieVeld;
        this.lengte = lengteVeld;
        this.geboortedatum = geboortedatumVeld;
        this.spike = spikeVeld;
        this.heeftBlessure = heeftBlessureVeld;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getSalaris() {
        return salaris;
    }

    public void setSalaris(double salaris) {
        this.salaris = salaris;
    }

    public String getPositie() {
        return positie;
    }

    public void setPositie(String positie) {
        this.positie = positie;
    }

    public double getLengte() {
        return lengte;
    }

    public void setLengte(double lengte) {
        this.lengte = lengte;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public int getSpike() {
        return spike;
    }

    public void setSpike(int spike) {
        this.spike = spike;
    }

    public boolean isHeeftBlessure() {
        return heeftBlessure;
    }

    public void setHeeftBlessure(boolean heeftBlessure) {
        this.heeftBlessure = heeftBlessure;
    }

    public Volleybalteam getHoortBij() {
        return hoortBij;
    }

    public void setHoortBij(Volleybalteam hoortBij) {
        this.hoortBij = hoortBij;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append(String.format("""
               Naam Speler: %s\s
               Geboortedatum: %s\s
               Lengte: %.2f M\s
               Salaris: €/$ %.2f\s
               Positie: %s\s
               Spike: %d CM""", naam, geboortedatum, lengte, salaris, positie, spike));
        if (heeftBlessure) {
            stringBuilder.append(String.format("\nHeeft %s een blessure: JA", naam));
        } else {
            stringBuilder.append(String.format("\nHeeft %s een blessure: NEE", naam));
        }
        return stringBuilder.toString();
    }
}