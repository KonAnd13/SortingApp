package service.sorting;

import java.util.List;

public class Sorter<T> {
    private SortStrategy<T> sortStrategy;

    public void setSortStrategy(SortStrategy<T> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort(List<T> elements) {
        if (sortStrategy == null) {
            System.out.println("Не выбрана стратегия сортировки!");
            return;
        }
        sortStrategy.sort(elements);
    }
}
