package service.sorting;

import java.util.Comparator;
import java.util.List;
import domain.car;


public class PowerYearModelSortStrategy implements SortStrategy<Car> {
    public void sort(List<Car> cars) {
        Comparator carsComparator = Comparator.comparingInt(Car::getPower)
                .thenComparingInt(Car::getYear)
                .thenComparing(Car::getModel);

        int n = cars.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (carsComparator.compare(cars.get(j), cars.get(j + 1)) > 0) {
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
}