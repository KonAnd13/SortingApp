package datasourcetest;

import domain.Car;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.datasource.ManualDataSource;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ManualDataSourceTest {

    private static final String BAD_INPUT_TEST_NAME =
            "Проверка работы с вводом некорректных данных.";
    private static final String GOOD_INPUT_TEST_NAME =
            "Проверка работы с вводом корректных данных.";
    private static final String MULTIPLE_INPUT_TEST_NAME =
            "Проверка работы с множественным вводом данных.";

    @AfterEach
    public void tearDown() {
        System.setIn(System.in);
    }

    @ParameterizedTest
    @DisplayName(BAD_INPUT_TEST_NAME)
    @CsvSource({
            "Toyota Camry, abc, 2000",
            "abc, 100, 2000",
            "Toyota Camry, 100, "
    })
    public void badInputTest(String model, String power, String year) {
        String input = "%s\n%s\n%s\n%s\n".formatted(model, power, year, "n");
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        List<Car> cars = new ManualDataSource().loadCars();
        System.out.println(cars);
        Assertions.assertEquals(0, cars.size());
    }

    @ParameterizedTest
    @DisplayName(GOOD_INPUT_TEST_NAME)
    @CsvSource({
            "Toyota Camry, 100, 2000",
            "Volvo V60, 102, 2001",
            "GMC Sierra, 90, 2003"
    })
    public void goodInputTest(String model, String power, String year) {
        String input = "%s\n%s\n%s\n%s\n".formatted(model, power, year, "n");
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        List<Car> cars = new ManualDataSource().loadCars();
        System.out.println(cars);
        Assertions.assertEquals(1, cars.size());
    }

    @Test
    @DisplayName(MULTIPLE_INPUT_TEST_NAME)
    public void multipleInputTest() {
        String model1 = "Toyota Camry";
        String model2 = "Volvo V60";
        String model3 = "GMC Sierra";

        String power1 = "100";
        String power2 = "abc";
        String power3 = "102";

        String year1 = "2000";
        String year2 = "2001";
        String year3 = "2003";

        String input = "%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n"
                .formatted(model1, power1, year1, "y",
                        model2, power2, year2, "y",
                        model3, power3, year3, "n");
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        List<Car> cars = new ManualDataSource().loadCars();
        Assertions.assertEquals(2, cars.size());
    }
}
