package data;

import practicumopdracht.models.Speler;
import practicumopdracht.models.Volleybalteam;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Abdullah Elmi
 */
public abstract class SpelerDAO implements DAO<Speler> {
    protected List<Speler> spelers = new ArrayList<>();

    /**
     * @return basketbalspelers
     */
    public List<Speler> getAll() {
        return spelers;
    }

    public Speler getByID(int id) {
        if (id < 0 || id >= spelers.size()) {
            return null;
        } else {
            return spelers.get(id);
        }
    }

    /**
     * zorgt ervoor dat het wordt toegevoegt en aangepast, als het in de listview zit krijg je die sout anders wordt het erbij gedaan
     *
     * @param object
     */
    @Override
    public void addOrUpdate(Speler object) {
        if (spelers.contains(object)) {
            System.out.println("Al aanwezig");
        } else {
            spelers.add(object);
        }

    }

    /**
     * verwijdert het object
     *
     * @param object
     */

    @Override
    public void remove(Speler object) {
        spelers.remove(object);
    }

    /**
     * Dit is een methode die een lijst van Basketbalspelers teruggeeft die bij een specifiek Basketbalteam horen.
     *
     * @param object
     * @return
     */

    public List<Speler> getAllFor(Volleybalteam object) {
        ArrayList<Speler> SpelerList = new ArrayList<>();

        for (Speler speler : spelers) {
            if (speler.getHoortBij() == object) {
                SpelerList.add(speler);
            }
        }
        return SpelerList;
    }

    @Override
    public boolean load() {
        return true;
    }

    @Override
    public boolean save() {
        return true;
    }
}
