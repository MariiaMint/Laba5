package managers;

import beginningClasses.HumanBeing;

import java.util.Comparator;

public class IdComparator implements Comparator<HumanBeing> {
    public int compare(HumanBeing obj1, HumanBeing obj2){
        return obj2.getId()-obj1.getId();
    }
}
