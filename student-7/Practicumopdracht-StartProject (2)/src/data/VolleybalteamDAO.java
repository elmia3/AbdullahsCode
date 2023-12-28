package data;
import practicumopdracht.models.Volleybalteam;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Abdullah Elmi
 */
public abstract class VolleybalteamDAO implements DAO<Volleybalteam>{
    protected List<Volleybalteam> volleybalteams;


    public VolleybalteamDAO() {
        volleybalteams = new ArrayList<>();
    }

    /**
     * @return volleybalteams
     */

    public List<Volleybalteam> getAll() {
        return volleybalteams;
    }
    // geeft een index voor elke basketbalteam
    public Volleybalteam getByID(int id) {
        if (id >= 0 && id < volleybalteams.size()) {
            return volleybalteams.get(id);
        }
        return null;
    }


    public int getIdFor(Volleybalteam object) {
        for (int i = 0; i < volleybalteams.size(); i++) {
            if (volleybalteams.get(i).equals(object)) {
                return volleybalteams.indexOf(object);
            }
        }
        return -1;
    }

    /**
     * zorgt ervoor dat het wordt toegevoegt en aangepast, als het in de listview zit krijg je die sout anders wordt het erbij gedaan
     *
     * @param object
     */

    @Override
    public void addOrUpdate(Volleybalteam object) {
        if (volleybalteams.contains(object)) {
            System.out.println("al aanwezig");
        } else {
            volleybalteams.add(object);
        }
    }

    /**
     * verwijdert het object
     *
     * @param object
     */
    @Override
    public void remove(Volleybalteam object){volleybalteams.remove(object);}

    @Override
    public boolean load() {
        volleybalteams.add(new Volleybalteam(volleybalteams.get(0).getNaam(),volleybalteams.get(0).getAantalTrofeeën() ));
        return true;
    }

    @Override
    public boolean save() {
        return true;
    }
}
