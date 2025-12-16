package service.datasource;

import domain.Car;
import domain.CarBuilder;
import util.DataValidator;
import util.ValidationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileDataSource implements DataSource {

    private static final String NO_FILE_MSG =
            "Файл не найден.";
    private static final String INCORRECT_FORMAT_MSG =
            "Неверный формат данных.";

    private static final String DATA_SPLTR = ";";

    private String fileName;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
    }

    /** Загружает список машин из файла. Если файла нет, возвращается null. */
    @Override
    public List<Car> loadCars() {
        List<Car> cars;

        try (Stream<String> fileContent = Files.lines(Paths.get(fileName))) {
            cars = fileContent
                    .map(this::stringToCar)
                    .filter(Objects::nonNull)
                    .toList();
        } catch (IOException e) {
            System.out.println(NO_FILE_MSG);
            return null;
        }

        return cars;
    }

    private Car stringToCar(String carString) {
        try {
            String[] carData = carString.split(DATA_SPLTR);

            String model = carData[0].strip();
            int power = Integer.parseInt(carData[1].strip());
            int year = Integer.parseInt(carData[2].strip());

            new DataValidator(model, power, year).validateCar();
            return new CarBuilder()
                    .model(model)
                    .power(power)
                    .year(year)
                    .build();
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (RuntimeException e) {
            System.out.println(INCORRECT_FORMAT_MSG);
            return null;
        }
    }
}
