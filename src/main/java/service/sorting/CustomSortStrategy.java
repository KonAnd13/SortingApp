package service.sorting;

import java.util.ArrayList;
import java.util.List;

import domain.Car;

public class CustomSortStrategy implements SortStrategy<Car> {
    @Override
    public void sort(List<Car> cars) {
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getYear() % 2 == 0) {
                indexes.add(i);
            }
        }

        if (indexes.isEmpty()) {
            return;
        }

        int n = indexes.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                int trueJ1 = indexes.get(j);
                int trueJ2 = indexes.get(j + 1);
                if (cars.get(trueJ1).getYear() > cars.get(trueJ2).getYear()) {
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
}