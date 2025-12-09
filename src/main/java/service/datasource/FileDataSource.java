package service.datasource;

import datasource.DataSource;
import domain.Car;
import domain.CarBuilder;
import util.DataValidator;
import util.ValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataSource implements DataSource {

    private static final String NO_FILE_MSG = "Файл не найден.";
    private static final String FAILED_READ_MSG = "Данных не прочитано: %s";

    private static final String CAR_SPLTR = "\n";
    private static final String DATA_SPLTR = ";";

    private String fileName;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
    }

    /** Загружает список машин из файла. Если файла нет, возвращается null. */
    @Override
    public List<Car> loadCars() {
        StringBuilder fileContent = new StringBuilder();
        try (FileReader reader = new FileReader(this.fileName)) {
            int c;
            while ((c = reader.read()) != -1) {
                fileContent.append((char)c);
            }
        } catch (IOException e) {
            System.out.println(NO_FILE_MSG);
            return null;
        }

        String[] carsString = fileContent.toString().split(CAR_SPLTR);
        List<Car> cars = new ArrayList<>(carsString.length);
        int fails = 0;
        for (String carString : carsString) {
            try {
                cars.add(stringToCar(carString));
            } catch(Exception e) {
                fails++;
            }
        }

        System.out.println(FAILED_READ_MSG.formatted(fails));
        return cars;
    }

    private Car stringToCar(String carString)
            throws ValidationException, ArrayIndexOutOfBoundsException,
            NumberFormatException {
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
    }
}
