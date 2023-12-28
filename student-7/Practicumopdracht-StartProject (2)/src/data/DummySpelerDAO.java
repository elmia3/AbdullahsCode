package data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Speler;

import java.time.LocalDate;
/**
 * @author Abdullah Elmi
 */
public class DummySpelerDAO extends SpelerDAO {
    @Override
    public boolean save() {
        return true;
    }

    @Override
    public boolean load() {
        Speler speler = new Speler(MainApplication.getVolleybalteamDAO().getByID(0),"poop", 5000, "Libero", 1.99, LocalDate.of(1946, 6, 6), 380, true);
        spelers.add(speler);
        return true;
    }
}
