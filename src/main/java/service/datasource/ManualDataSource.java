package service.datasource;

import domain.Car;
import domain.CarBuilder;
import util.DataValidator;
import util.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualDataSource implements DataSource {

    private static final String INPUT_MODEL_MSG =
            "Название модели: ";
    private static final String INPUT_POWER_MSG =
            "Мощность двигателя: ";
    private static final String INPUT_YEAR_MSG =
            "Год выпуска: ";
    private static final String SUCCESSFUL_INPUT_MSG =
            "Автомобиль добавлен.";
    private static final String INCORRECT_FORMAT_MSG =
            "Неверный формат данных.";


    private static final String CONTINUE_INPUT_MSG =
            "Продолжить ввод? (y/n): ";
    private static final String CONTINUE_INPUT_POS = "y";
    private static final String CONTINUE_INPUT_NEG = "n";

    private final Scanner in = new Scanner(System.in);

    @Override
    public List<Car> loadCars() {
        List<Car> cars = new ArrayList<>();

        do {
            try {
                System.out.print(INPUT_MODEL_MSG);
                String model = in.nextLine();

                System.out.print(INPUT_POWER_MSG);
                String stringPower = in.nextLine();

                System.out.print(INPUT_YEAR_MSG);
                String stringYear = in.nextLine();

                int power = Integer.parseInt(stringPower);
                int year = Integer.parseInt(stringYear);

                new DataValidator(model, power, year).validateCar();
                cars.add(new CarBuilder()
                        .model(model)
                        .power(power)
                        .year(year)
                        .build());

                System.out.println(SUCCESSFUL_INPUT_MSG);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(INCORRECT_FORMAT_MSG);
            }
        } while(continueInput());

        return cars;
    }

    private boolean continueInput() {
        while (true) {
            System.out.print(CONTINUE_INPUT_MSG);
            switch (in.nextLine()) {
                case CONTINUE_INPUT_POS: return true;
                case CONTINUE_INPUT_NEG: return false;
            }
        }
    }
}
