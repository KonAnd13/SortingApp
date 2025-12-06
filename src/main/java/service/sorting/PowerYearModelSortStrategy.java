package service.sorting;

import java.util.List;
import domain.Car;


public class PowerYearModelSortStrategy implements SortStrategy<Car> {
    @Override
    public void sort(List<Car> cars) {
        int n = cars.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (compare(cars.get(j), cars.get(j + 1)) > 0) {
                    Car temp = cars.get(j);
                    cars.set(j, cars.get(j + 1));
                    cars.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    private int compare(Car car1, Car car2) {
        int powerCompare = Integer.compare(car1.getPower(), car2.getPower());
        if (powerCompare != 0) {
            return powerCompare;
        }

        int yearCompare = Integer.compare(car1.getYear(), car2.getYear());
        if (yearCompare != 0) {
            return yearCompare;
        }

        int modelCompare = car1.getModel().compareTo(car2.getModel());
        return modelCompare;
    }
}