package service.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Car;

public class YearPowerModelSortStrategyTest {

    private static final String SORT_BY_YEAR_TEST_NAME =
            "Проверка сортировки по году выпуска автомобиля.";
    private static final String SORT_BY_POWER_TEST_NAME =
            "Проверка сортировки по мощности при равном годе выпуска.";
    private static final String SORT_BY_MODEL_TEST_NAME =
            "Проверка сортировки по модели при равном годе и мощности.";
    private static final String COMPLEX_SORT_TEST_NAME =
            "Проверка комплексной сортировки (год → мощность → модель).";
    private static final String EMPTY_LIST_TEST_NAME =
            "Проверка сортировки пустого списка.";
    private static final String SINGLE_ELEMENT_TEST_NAME =
            "Проверка сортировки списка с одним элементом.";
    private static final String ALREADY_SORTED_TEST_NAME =
            "Проверка сортировки уже отсортированного списка.";
    private static final String REVERSE_SORTED_TEST_NAME =
            "Проверка сортировки списка в обратном порядке.";
    private static final String SORT_WITH_DUPLICATE_TITLE_NAME =
            "Проверка сортировки с дубликатами.";
    private static final String SORTED_SIZE_TEST_NAME =
            "Проверка сохранения размера списка после сортировки.";

    private YearPowerModelSortStrategy sortStrategy = new YearPowerModelSortStrategy();

    @Test
    @DisplayName(SORT_BY_YEAR_TEST_NAME)
    public void sortByYearTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Toyota Camry", 150, 2022),
            new Car("Honda Civic", 120, 2019),
            new Car("Ford Focus", 180, 2020),
            new Car("BMW X5", 130, 2021)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("Honda Civic", 120, 2019),
            new Car("Ford Focus", 180, 2020),
            new Car("BMW X5", 130, 2021),
            new Car("Toyota Camry", 150, 2022)
        ));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(SORT_BY_POWER_TEST_NAME)
    public void sortByPowerTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Toyota Camry", 180, 2020),
            new Car("Honda Accord", 120, 2020),
            new Car("Nissan Altima", 150, 2020)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("Honda Accord", 120, 2020),
            new Car("Nissan Altima", 150, 2020),
            new Car("Toyota Camry", 180, 2020)
        ));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(SORT_BY_MODEL_TEST_NAME)
    public void sortByModelTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Zonda", 200, 2020),
            new Car("Aventador", 200, 2020),
            new Car("Murcielago", 200, 2020)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("Aventador", 200, 2020),
            new Car("Murcielago", 200, 2020),
            new Car("Zonda", 200, 2020)
        ));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(COMPLEX_SORT_TEST_NAME)
    public void complexSortTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Toyota Camry", 150, 2020),
            new Car("Honda Civic", 180, 2019),
            new Car("Toyota Corolla", 120, 2020),
            new Car("BMW X3", 150, 2019),
            new Car("Audi A4", 120, 2020),
            new Car("Mercedes C", 200, 2018)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("Mercedes C", 200, 2018),
            new Car("BMW X3", 150, 2019),
            new Car("Honda Civic", 180, 2019),
            new Car("Audi A4", 120, 2020),
            new Car("Toyota Corolla", 120, 2020),
            new Car("Toyota Camry", 150, 2020)
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
    @DisplayName(SINGLE_ELEMENT_TEST_NAME)
    public void singleElementTest() {
        Car car = new Car("Tesla Model 3", 283, 2023);
        List<Car> singleElementList = new ArrayList<>(Arrays.asList(car));
        
        List<Car> expected = new ArrayList<>(Arrays.asList(car));

        sortStrategy.sort(singleElementList);

        Assertions.assertEquals(expected, singleElementList);
        Assertions.assertEquals(1, singleElementList.size());
        Assertions.assertEquals(car, singleElementList.get(0));
    }

    @Test
    @DisplayName(ALREADY_SORTED_TEST_NAME)
    public void alreadySortedTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Audi A4", 100, 2018),
            new Car("BMW X3", 120, 2019),
            new Car("Chevrolet Camaro", 120, 2019),
            new Car("Dodge Charger", 150, 2020)
        ));

        List<Car> expected = new ArrayList<>(cars);

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(REVERSE_SORTED_TEST_NAME)
    public void reverseSortedTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Dodge Charger", 150, 2022),
            new Car("Chevrolet Camaro", 180, 2021),
            new Car("BMW X3", 120, 2020),
            new Car("Audi A4", 100, 2019)
        ));

        List<Car> expected = new ArrayList<>(Arrays.asList(
            new Car("Audi A4", 100, 2019),
            new Car("BMW X3", 120, 2020),
            new Car("Chevrolet Camaro", 180, 2021),
            new Car("Dodge Charger", 150, 2022)
        ));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
    }

    @Test
    @DisplayName(SORT_WITH_DUPLICATE_TITLE_NAME)
    public void duplicatesTest() {
        Car car1 = new Car("Toyota Camry", 150, 2020);
        Car car2 = new Car("Toyota Camry", 150, 2020);
        
        List<Car> cars = new ArrayList<>(Arrays.asList(car1, car2));
        List<Car> expected = new ArrayList<>(Arrays.asList(car1, car2));

        sortStrategy.sort(cars);

        Assertions.assertEquals(expected, cars);
        Assertions.assertEquals(2, cars.size());
    }

    @Test
    @DisplayName(SORTED_SIZE_TEST_NAME)
    public void sizePreservationTest() {
        List<Car> cars = new ArrayList<>(Arrays.asList(
            new Car("Audi A4", 200, 2018),
            new Car("BMW X3", 150, 2022),
            new Car("Chevrolet Camaro", 180, 2020),
            new Car("Dodge Charger", 120, 2019)
        ));

        int originalSize = cars.size();

        sortStrategy.sort(cars);

        Assertions.assertEquals(originalSize, cars.size());
    }
}