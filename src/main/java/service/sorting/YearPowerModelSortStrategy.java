package service.sorting;

import domain.car;
import java.util.List;

public class YearPowerModelSortStrategy implements SortStrategy<Car> {
    @Override
    public void sort(List<Car> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getModel().compareTo(list.get(j + 1).getModel()) > 0) {
                    Car temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}