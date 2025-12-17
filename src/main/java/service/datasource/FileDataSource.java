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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDataSource implements DataSource {

    private static final String NO_FILE_MSG = "Файл не найден.";
    private static final String INCORRECT_FORMAT_MSG = "\nНеверный формат данных.";
    private static final String DATA_SPLTR = ";";

    private final String fileName;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
    }

    /** Загружает список машин из файла. Если некорректная структура файла или файла нет, возвращается null. */
    @Override
    public List<Car> loadCars() {
        System.out.println("\nЗагрузка данных...");

        List<Car> cars;
        AtomicInteger lineNumber = new AtomicInteger(0);

        try (Stream<String> fileContent = Files.lines(Paths.get(fileName))) {
            cars = fileContent
                    .map(line -> {
                        lineNumber.incrementAndGet();
                        try {
                            return stringToCar(line);
                        } catch (ValidationException e) {
                            throw new ValidationException("Ошибка в строке " + lineNumber.get());
                        }
                    })
                    .collect(Collectors.toList());
        } catch (ValidationException e) {
            System.out.println(INCORRECT_FORMAT_MSG + " " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(NO_FILE_MSG);
            return null;
        }

        return cars;
    }

    private Car stringToCar(String carString) throws ValidationException {
        String[] carData = carString.split(DATA_SPLTR);

        if (carData.length != 3) {
            throw new ValidationException("Неверное количество данных.");
        }

        String model = carData[0].strip();
        int power = Integer.parseInt(carData[1].strip());
        int year = Integer.parseInt(carData[2].strip());

        new DataValidator(model, power, year).validateCar();

        return new CarBuilder()
                .model(model)
                .power(power)
                .year(year)
                .build();
    }
}
