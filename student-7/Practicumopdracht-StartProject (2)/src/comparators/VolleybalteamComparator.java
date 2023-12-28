package comparators;

import practicumopdracht.models.Volleybalteam;

import java.util.Comparator;
/**
 * @author Abdullah Elmi
 */
public class VolleybalteamComparator implements Comparator<Volleybalteam> {
    @Override
    public int compare(Volleybalteam o1, Volleybalteam o2) {
        return o1.getNaam().compareTo(o2.getNaam());
    }
}
