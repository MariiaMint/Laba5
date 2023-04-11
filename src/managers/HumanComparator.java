package managers;

import beginningClasses.HumanBeing;

import java.util.Comparator;

public class HumanComparator implements Comparator<HumanBeing> {
    Comparator<HumanBeing> realHeroComparator = Comparator.comparing(HumanBeing::isRealHero);
    Comparator<HumanBeing> impactSpeedComparator = Comparator.comparing(
            HumanBeing::getImpactSpeed,
            Comparator.nullsFirst(Comparator.naturalOrder())
    );
    HumansCarComparator humansCarComparator = new HumansCarComparator();
    public int compare(HumanBeing o1, HumanBeing o2){
    return realHeroComparator
            .thenComparing(impactSpeedComparator)
            .thenComparing(humansCarComparator)
            .compare(o2,o1);
    }
}
