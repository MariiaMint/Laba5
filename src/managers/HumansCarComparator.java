package managers;

import beginningClasses.Car;
import beginningClasses.HumanBeing;

import java.util.Comparator;

public class HumansCarComparator implements Comparator<HumanBeing>{
    private Comparator<HumanBeing> carNameComparator;
    private Comparator<HumanBeing> carCoolComparator;

    public HumansCarComparator() {
        // Компаратор для сравнения по имени машины
        this.carNameComparator = Comparator.nullsLast(
                Comparator.comparing(
                        human -> human.getCar().getName(),
                    Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER)
                )
        );

        // Компаратор для сравнения по свойству carCool
        this.carCoolComparator = Comparator.comparing(
                human -> human.getCar().isCool()
        );
    }

    @Override
    public int compare(HumanBeing human1, HumanBeing human2) {
        int nameComparison = carNameComparator.compare(human1, human2);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return carCoolComparator.compare(human1, human2);
    }
}
