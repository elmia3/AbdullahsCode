package data;

import practicumopdracht.models.Volleybalteam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * @author Abdullah Elmi
 */
public class TextVolleybalteamDAO extends VolleybalteamDAO {

    private static final String FILENAME = "volleybalteam.txt";


    @Override
    public boolean save() {
        File file = new File(FILENAME);
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
            printWriter.println(volleybalteams.size());
            for (Volleybalteam volleybalteam : volleybalteams) {
                printWriter.println(volleybalteam.getNaam());
                printWriter.println(volleybalteam.getAantalTrofeeën());
            }
            printWriter.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Bestand is niet gevonden!");
            return false;
        } catch (Exception exception) {
            System.err.println("Oei! Er is iets misgegaan!");
            exception.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try {
            Scanner scanner = new Scanner(file);
            int aantal = scanner.nextInt();
            scanner.nextLine();

            volleybalteams.clear();

            for (int i = 0; i < aantal; i++) {
                String naam = scanner.nextLine();
                String aantalTrofeeën = scanner.nextLine();
                int integer = Integer.parseInt(aantalTrofeeën);

                volleybalteams.add(new Volleybalteam(naam, integer));
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Bestand is niet gevonden!");
            e.printStackTrace();
            return false;
        }
    }
}
