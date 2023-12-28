package comparators;

import practicumopdracht.models.Speler;

import java.util.Comparator;
/**
 * @author Abdullah Elmi
 */
public class SpelerNaamComparator implements Comparator<Speler> {
    @Override
    public int compare(Speler o1, Speler o2) {
        return o1.getNaam().compareTo(o2.getNaam());
    }
}
