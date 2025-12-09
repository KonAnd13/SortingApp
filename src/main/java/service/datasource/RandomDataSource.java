package service.datasource;

import domain.Car;
import domain.CarBuilder;
import util.CarModels;
import util.DataValidator;
import util.ValidationException;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomDataSource implements DataSource {

    private final Random random = new Random();
    private static final int MIN_POWER = 1;
    private static final int MAX_POWER = 2000;
    private static final int MIN_YEAR = 1886;
    private static final int CURRENT_YEAR = Year.now().getValue();

    @Override
    public List<Car> loadCars() {
        int count = requestCarCountFromUser();

        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Car car = generateValidCar();
            DataValidator validator = new DataValidator(car.getModel(), car.getPower(), car.getYear());
            try {
                validator.validateCar();
                cars.add(car);
            } catch (ValidationException e) {
                System.err.println("Ошибка валидации сгенерированной машины: " + e.getMessage());
                i--;
            }
        }
        return cars;
    }

    private int requestCarCountFromUser() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("Сколько автомобилей сгенерировать? Введите целое число: ");
            if (scanner.hasNextInt()) {
                count = scanner.nextInt();
                if (count > 0) {
                    valid = true;
                } else {
                    System.out.println("Пожалуйста, введите положительное число.");
                }
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
                scanner.next();
            }
        }
        return count;
    }

    private Car generateValidCar() {
        String model = CarModels.MODELS.get(random.nextInt(CarModels.MODELS.size() - 1));
        int power = MIN_POWER + random.nextInt(MAX_POWER - MIN_POWER + 1);
        int year = MIN_YEAR + random.nextInt(CURRENT_YEAR - MIN_YEAR + 1);

        return new CarBuilder()
                .model(model)
                .power(power)
                .year(year)
                .build();
    }
}