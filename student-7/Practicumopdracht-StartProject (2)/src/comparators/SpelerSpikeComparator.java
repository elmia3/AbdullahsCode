package comparators;

import practicumopdracht.models.Speler;

import java.util.Comparator;
/**
 * @author Abdullah Elmi
 */
public class SpelerSpikeComparator implements Comparator<Speler> {

    @Override
    public int compare(Speler o1, Speler o2) {
        return Integer.compare(o1.getSpike(), o2.getSpike());
    }
}
