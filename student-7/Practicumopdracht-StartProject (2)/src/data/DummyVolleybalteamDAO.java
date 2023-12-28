package data;

import practicumopdracht.models.Volleybalteam;
/**
 * @author Abdullah Elmi
 */
public class DummyVolleybalteamDAO extends VolleybalteamDAO{

    @Override
    public boolean save() {
        return true;
    }

    @Override
    public boolean load() {
        volleybalteams.add(new Volleybalteam("Los Angeles Lakers", 17));
        volleybalteams.add(new Volleybalteam("Golden State Warriors", 7));
        volleybalteams.add(new Volleybalteam("New York Knicks", 2));
        return true;
    }
}
