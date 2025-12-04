package service.sorting;

import domain.Car;
import java.util.List;

import static java.util.Collections.swap;

public class YearPowerModelSortStrategy implements SortStrategy<Car> {
    @Override
    public void sort(List<Car> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                Car car1 = list.get(j);
                Car car2 = list.get(j+1);

                if (car1.getYear() != car2.getYear()) {
                    if(car1.getYear() > car2.getYear()){
                        swap(list,j);
                    }
                    continue;

                }
                if (car1.getPower() !=car2.getPower()){
                    if(car1.getPower() > car2.getPower()){
                        swap(list,j);
                    }
                    continue;

                }
                if (car1.getModel() !=car2.getModel()){
                    if(car1.getModel() > car2.getModel()){
                        swap(list,j);
                    }
                    continue;

            }
        }
    }

    private void swap(List<Car> list, int index){
        Car temp = list.get(index);
        list.set(index, list.get(list.size() + 1));
        list.set(index + 1, temp);
    }
}