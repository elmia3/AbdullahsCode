package data;

import practicumopdracht.models.Volleybalteam;

import java.io.*;
import java.time.LocalDate;
/**
 * @author Abdullah Elmi
 */
public class BinaryVolleybalteamDAO extends VolleybalteamDAO{
    private static String FILENAME = "volleybalteam.dat";

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (
                FileOutputStream fileOs = new FileOutputStream(file);
                DataOutputStream dataOs = new DataOutputStream(fileOs)
        ) {
            dataOs.writeInt(volleybalteams.size());
            for (Volleybalteam volleybalteam : volleybalteams) {
                dataOs.writeUTF(volleybalteam.getNaam());
                dataOs.writeUTF(String.valueOf(volleybalteam.getAantalTrofeeën()));
            }
        } catch (FileNotFoundException e) {
            System.err.print("Bestand is niet gevonden!");
        } catch (IOException e) {
            System.err.print("Oei! er is iets misgegaan!");
        }
        return false;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        try (
                FileInputStream fileIs = new FileInputStream(file);
                DataInputStream dataIs = new DataInputStream(fileIs)
        ) {
            int aantal = dataIs.readInt();
            for (int i = 0; i < aantal; i++) {
                String naam = dataIs.readUTF();
                String aantalTrofeeën = dataIs.readUTF();
                Integer integer = Integer.parseInt(aantalTrofeeën);

                volleybalteams.add(new Volleybalteam(naam, integer));

            }
        } catch (FileNotFoundException e) {
            System.err.print("Bestand is niet gevonden!");
            ;
        } catch (IOException e) {
            System.err.print("Oei! er is iets misgegaan!");
            e.printStackTrace();
        }
        return true;
    }
}
