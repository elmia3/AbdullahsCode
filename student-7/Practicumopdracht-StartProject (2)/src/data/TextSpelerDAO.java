package data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Speler;
import practicumopdracht.models.Volleybalteam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * @author Abdullah Elmi
 */
public class TextSpelerDAO extends SpelerDAO{
    private static String FILENAME = "speler.txt";

    @Override
    public boolean save() {
        File file = new File(FILENAME);

        // zorgt ervoor dat een stuk tekst verandert naar binair
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
            printWriter.println(spelers.size());
            for (Speler speler : spelers) {
                printWriter.println(MainApplication.getVolleybalteamDAO().getIdFor(speler.getHoortBij()));
                printWriter.println(speler.getNaam());
                printWriter.println(speler.getLengte());
                printWriter.println(speler.getSalaris());
                printWriter.println(speler.isHeeftBlessure());
                printWriter.println(speler.getPositie());
                printWriter.println(speler.getGeboortedatum());
                printWriter.println(speler.getSpike());

            }
        } catch (FileNotFoundException e) {
            System.out.println("Bestand Niet Gevonden!");
        } catch (Exception exception) {
            System.err.print("Oei! er is iets misgegaan!");

            exception.printStackTrace();
        } finally {
            printWriter.close();
        }
        load();
        return true;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);

        try {
            Scanner scanner = new Scanner(file);
            int aantal = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < aantal; i++) {
                String hoortBij = scanner.nextLine();
                String naam = scanner.nextLine();
                String lengte = scanner.nextLine();
                String salaris = scanner.nextLine();
                String heeftblessure = scanner.nextLine();
                String positie = scanner.nextLine();
                String geboortedatum = scanner.nextLine();
                String spike = scanner.nextLine();

                int object = Integer.parseInt(hoortBij);
                Volleybalteam spelerInTeam = MainApplication.getVolleybalteamDAO().getByID(object);
                LocalDate localDate = LocalDate.parse(String.valueOf(geboortedatum));

                boolean heeftBlessureVeld = Boolean.parseBoolean(heeftblessure);
                int spikeVeld = Integer.parseInt(spike);
                double lengteVeld = Double.parseDouble(lengte);
                double salarisVeld = Double.parseDouble(salaris);
                spelers.add(new Speler(spelerInTeam, naam, salarisVeld,positie,lengteVeld, localDate,spikeVeld,heeftBlessureVeld));
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            {
                System.err.print("Bestand is niet gevonden!");
                e.printStackTrace();
            }
        }
        return false;
    }
}
