package service.datasource;

import domain.Car;
import domain.CarBuilder;
import util.DataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualDataSource implements DataSource {

    private static final String INPUT_MODEL_MSG = "Название модели: ";
    private static final String INPUT_POWER_MSG = "Мощность двигателя: ";
    private static final String INPUT_YEAR_MSG = "Год выпуска: ";
    private static final String SUCCESSFUL_INPUT_MSG = "Автомобиль добавлен.";

    private static final String CONTINUE_INPUT_MSG = "Продолжить ввод? (y/n): ";
    private static final String CONTINUE_INPUT_POS = "y";
    private static final String CONTINUE_INPUT_NEG = "n";

    @Override
    public List<Car> loadCars() {
        List<Car> cars = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        do {
            try {
                System.out.print(INPUT_MODEL_MSG);
                String model = in.nextLine();

                System.out.print(INPUT_POWER_MSG);
                int power = Integer.parseInt(in.nextLine());

                System.out.print(INPUT_YEAR_MSG);
                int year = Integer.parseInt(in.nextLine());

                new DataValidator(model, power, year).validateCar();
                System.out.println(SUCCESSFUL_INPUT_MSG);

                cars.add(new CarBuilder()
                        .model(model)
                        .power(power)
                        .year(year)
                        .build());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while(continueInput());

        return cars;
    }

    private boolean continueInput() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print(CONTINUE_INPUT_MSG);
            switch (in.nextLine()) {
                case CONTINUE_INPUT_POS: return true;
                case CONTINUE_INPUT_NEG: return false;
            }
        }
    }
}
