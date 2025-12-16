package service.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Car;

public class CustomSortStrategyTest {

    private static final String SORT_EVEN_YEARS_TEST_NAME =
            "Проверка сортировки автомобилей с четными годами выпуска.";
    private static final String IGNORE_ODD_YEARS_TEST_NAME =
            "Проверка, что автомобили с нечетными годами не сортируются.";
    private static final String MIXED_YEARS_SORT_TEST_NAME =
            "Проверка сортировки списка со смешанными годами выпуска.";
    private static final String ONLY_EVEN_YEARS_TEST_NAME =
            "Проверка сортировки списка только с четными годами выпуска.";
    private static final String EMPTY_LIST_TEST_NAME =
            "Проверка сортировки пустого списка.";
    private static final String SINGLE_ELEMENT_EVEN_TEST_NAME =
            "Проверка сортировки списка с одним элементом (четный год).";
    private static final String SINGLE_ELEMENT_ODD_TEST_NAME =
            "Проверка сортировки списка с одним элементом (нечетный год).";
    private static final String PRESERVE_ODD_POSITIONS_TEST_NAME =
            "Проверка сохранения позиций автомобилей с нечетными годами.";
    private static final String EQUAL_EVEN_YEARS_TEST_NAME =
            "Проверка сортировки при одинаковых четных годах.";
    private static final String COMPLEX_MIXED_TEST_NAME =
            "Проверка комплексной сортировки со смешанными годами.";
    private static final String ALREADY_SORTED_EVEN_YEARS_TEST_NAME =
            "Проверка обработки уже отсортированных четных годов.";
    private static final String REVERSE_SORTED_EVEN_YEARS_TEST_NAME =
            "Проверка сортировки обратно отсортированных четных годов.";

    private CustomSortStrategy sortStrategy = new CustomSortStrategy();

    @Test
    @DisplayName(SORT_EVEN_YEARS_TEST_NAME)
    public void sortEvenYearsTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Toyota Camry", 150, 2020),
            new Car("Honda Civic", 120, 2022),
            new Car("Ford Focus", 180, 2018),
            new Car("BMW X5", 130, 2016)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("BMW X5", 130, 2016),
            new Car("Ford Focus", 180, 2018),
            new Car("Toyota Camry", 150, 2020),
            new Car("Honda Civic", 120, 2022)
        ));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(IGNORE_ODD_YEARS_TEST_NAME)
    public void ignoreOddYearsTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Toyota Camry", 150, 2021),
            new Car("Honda Civic", 120, 2019),
            new Car("Ford Focus", 180, 2023),
            new Car("BMW X5", 130, 2017)
        ));

        List<Car> expected = new ArrayList<>(cars);

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(MIXED_YEARS_SORT_TEST_NAME)
    public void mixedYearsSortTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Car1", 100, 2020),
            new Car("Car2", 200, 2019),
            new Car("Car3", 150, 2018),
            new Car("Car4", 180, 2021),
            new Car("Car5", 120, 2022)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("Car3", 150, 2018),
            new Car("Car2", 200, 2019),
            new Car("Car1", 100, 2020),
            new Car("Car4", 180, 2021),
            new Car("Car5", 120, 2022)
        ));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(EMPTY_LIST_TEST_NAME)
    public void emptyListTest() {
        List<Car> emptyList = new ArrayList<>();

        Assertions.assertDoesNotThrow(() -> sortStrategy.sort(emptyList));
        Assertions.assertTrue(emptyList.isEmpty());
        Assertions.assertEquals(0, emptyList.size());
    }

    @Test
    @DisplayName(SINGLE_ELEMENT_EVEN_TEST_NAME)
    public void singleElementEvenTest() {
        Car car = new Car("Tesla", 283, 2022);
        List<Car> cars = new ArrayList<>(Arrays.asList(car));

        sortStrategy.sort(cars);

        Assertions.assertEquals(1, cars.size());
        Assertions.assertEquals(car, cars.get(0));
    }

    @Test
    @DisplayName(SINGLE_ELEMENT_ODD_TEST_NAME)
    public void singleElementOddTest() {
        Car car = new Car("Tesla", 283, 2023);
        List<Car> cars = new ArrayList<>(Arrays.asList(car));

        sortStrategy.sort(cars);

        Assertions.assertEquals(1, cars.size());
        Assertions.assertEquals(car, cars.get(0));
    }

    @Test
    @DisplayName(EQUAL_EVEN_YEARS_TEST_NAME)
    public void equalEvenYearsTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("CarA", 150, 2020),
            new Car("CarB", 120, 2020),
            new Car("CarC", 180, 2020),
            new Car("OddCar", 200, 2021)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("CarB", 120, 2020),
            new Car("CarA", 150, 2020),
            new Car("CarC", 180, 2020),
            new Car("OddCar", 200, 2021)
        ));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(COMPLEX_MIXED_TEST_NAME)
    public void complexMixedTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Car1", 100, 2023),
            new Car("Car2", 200, 2016),
            new Car("Car3", 150, 2020),
            new Car("Car4", 180, 2019),
            new Car("Car5", 120, 2022),
            new Car("Car6", 130, 2018)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("Car1", 100, 2023),
            new Car("Car2", 200, 2016),
            new Car("Car6", 130, 2018),
            new Car("Car4", 180, 2019),
            new Car("Car3", 150, 2020),
            new Car("Car5", 120, 2022)
        ));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(ALREADY_SORTED_EVEN_YEARS_TEST_NAME)
    public void alreadySortedEvenYearsTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Car1", 100, 2016),
            new Car("Car2", 200, 2017),
            new Car("Car3", 150, 2018),
            new Car("Car4", 180, 2020),
            new Car("Car5", 120, 2021)
        ));

        List<Car> expected = new ArrayList<>(cars);

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(REVERSE_SORTED_EVEN_YEARS_TEST_NAME)
    public void reverseSortedEvenYearsTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Newest", 100, 2022),
            new Car("Odd1", 200, 2021),
            new Car("Middle", 150, 2020),
            new Car("Odd2", 180, 2019),
            new Car("Oldest", 120, 2018)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("Oldest", 120, 2018),
            new Car("Odd1", 200, 2021),  
            new Car("Middle", 150, 2020),  
            new Car("Odd2", 180, 2019),    
            new Car("Newest", 100, 2022)
        ));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }
}