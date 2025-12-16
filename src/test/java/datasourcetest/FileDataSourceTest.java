package datasourcetest;

import domain.Car;
import org.junit.jupiter.api.*;
import service.datasource.FileDataSource;

import java.util.List;

public class FileDataSourceTest {

    private static final String NO_FILE_TEST_NAME =
            "Проверка работы с отсутствующим файлом.";
    private static final String NO_FILE_TEST_PATH =
            "nofile.txt";

    private static final String EMPTY_FILE_TEST_NAME =
            "Проверка работы с пустым файлом.";
    private static final String EMPTY_FILE_TEST_PATH =
            "src/test/resources/emptyfile.txt";

    private static final String BAD_FILE_TEST_NAME =
            "Проверка работы с файлом, содержащим некорректные данные.";
    private static final String BAD_FILE_TEST_PATH =
            "src/test/resources/badfile.txt";

    private static final String GOOD_FILE_TEST_NAME =
            "Проверка работы с файлом, содержащим корректные данные.";
    private static final String GOOD_FILE_TEST_PATH =
            "src/test/resources/goodfile.txt";

    @Test
    @DisplayName(NO_FILE_TEST_NAME)
    public void noFileTest() {
        List<Car> cars = new FileDataSource(NO_FILE_TEST_PATH).loadCars();
        Assertions.assertNull(cars);
    }

    @Test
    @DisplayName(EMPTY_FILE_TEST_NAME)
    public void emptyFileTest() {
        List<Car> cars = new FileDataSource(EMPTY_FILE_TEST_PATH).loadCars();
        Assertions.assertEquals(0, cars.size());
    }

    @Test
    @DisplayName(BAD_FILE_TEST_NAME)
    public void badFileTest() {
        List<Car> cars = new FileDataSource(BAD_FILE_TEST_PATH).loadCars();
        Assertions.assertEquals(1, cars.size());
    }

    @Test
    @DisplayName(GOOD_FILE_TEST_NAME)
    public void goodFileTest() {
        List<Car> cars = new FileDataSource(GOOD_FILE_TEST_PATH).loadCars();
        Assertions.assertEquals(3, cars.size());
    }
}
