package service.sorting;

import java.util.ArrayList;
import java.util.List;

import domain.Car;

public class CustomSortStrategy implements SortStrategy<Car> {
    @Override
    public void sort(List<Car> cars) {
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < cars.size(); i++)
            if (cars.get(i).getYear() % 2 == 0)
                indexes.add(i);

        if (indexes.isEmpty())
            return;

        int n = indexes.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                int trueJ1 = indexes.get(j);
                int trueJ2 = indexes.get(j+1);
                if (compare(cars.get(trueJ1), cars.get(trueJ2)) > 0) {
                    Car temp = cars.get(trueJ1);
                    cars.set(trueJ1, cars.get(trueJ2));
                    cars.set(trueJ2, temp);
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