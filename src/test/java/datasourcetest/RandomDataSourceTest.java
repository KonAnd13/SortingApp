package datasourcetest;

import domain.Car;
import service.datasource.RandomDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DataValidator;
import util.CarModels;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomDataSourceTest {

    private RandomDataSource dataSource;
    private final InputStream originalSystemIn = System.in;
    private ByteArrayInputStream simulatedInput;

    @BeforeEach
    void setUp() {
        dataSource = new RandomDataSource();
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    private void provideInput(String data) {
        simulatedInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(simulatedInput);
    }

    @Test
    @DisplayName("Проверка генерации заданного валидного количества автомобилей")
    void testLoadCarsWithValidCount() {
        int expectedCount = 50;
        provideInput(String.valueOf(expectedCount) + "\n");

        List<Car> cars;
        cars = dataSource.loadCars();

        assertNotNull(cars, "Список автомобилей не должен быть null");
        assertEquals(expectedCount, cars.size(), "Размер списка должен соответствовать запрошенному количеству");
    }

    @Test
    @DisplayName("Проверка валидности сгенерированных данных")
    void testGeneratedCarDataValidity() {
        int countToGenerate = 10;
        provideInput(String.valueOf(countToGenerate) + "\n");

        List<Car> cars;
        cars = dataSource.loadCars();

        assertNotNull(cars);
        assertEquals(countToGenerate, cars.size());

        for (Car car : cars) {
            DataValidator validator = new DataValidator(car.getModel(), car.getPower(), car.getYear());

            assertDoesNotThrow(() -> {
                validator.validateCar();
            }, "Сгенерированный автомобиль должен проходить валидацию: " + car.toString());

            assertTrue(CarModels.MODELS.contains(car.getModel()), "Модель должна содержаться в CarModels.MODELS");
        }
    }

    @Test
    @DisplayName("Проверка обработки некорректного ввода (текст вместо числа), ожидаем повторный запрос")
    void testRequestCarCountWithInvalidInput() {
        String input = "abc\n-5\n15\n";
        provideInput(input);

        List<Car> cars;
        cars = dataSource.loadCars();

        assertNotNull(cars);
        assertEquals(15, cars.size(), "Метод должен был повторно запросить ввод и сгенерировать 15 машин");
    }
}
