package data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Speler;
import practicumopdracht.models.Volleybalteam;

import java.io.*;
/**
 * @author Abdullah Elmi
 */
public class ObjectSpelerDAO extends SpelerDAO{
    private static String FILENAME = "speler.obj";

    /**
     * @return
     */

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try (
                FileOutputStream fileOs = new FileOutputStream(file);
                ObjectOutputStream objectOs = new ObjectOutputStream(fileOs);
        ) {
            objectOs.writeInt(spelers.size());
            for (Speler speler : spelers) {
                objectOs.writeObject(speler);
                objectOs.writeInt(MainApplication.getVolleybalteamDAO().getIdFor(speler.getHoortBij()));
                int teamIndex = MainApplication.getVolleybalteamDAO().getAll().indexOf(speler.getHoortBij());
                objectOs.writeInt(teamIndex);
            }
            return true;
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
                ObjectInputStream objectIs = new ObjectInputStream(fileIs);
        ) {
            //leest de aantal basketbalspelers wat dus een ID heeft
            int aantal = objectIs.readInt();
            for (int i = 0; i < aantal; i++) {
                Speler speler = (Speler) objectIs.readObject();
                Volleybalteam volleybalteam = MainApplication.getVolleybalteamDAO().getByID(objectIs.readInt());
                speler.setHoortBij(volleybalteam);
                spelers.add(speler);
            }
            return true;
        } catch (FileNotFoundException e) {
            System.err.print("Bestand is niet gevonden!");

        } catch (IOException e) {
            System.err.print("Oei! er is iets misgegaan!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
