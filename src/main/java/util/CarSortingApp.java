package service.sorting;

import domain.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarSortingApp {
    private List<Car> cars;
    private Scanner scanner;
    private boolean dataLoaded = false;

    public CarSortingApp() {
        this.cars = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    loadData();
                    break;
                case 2:
                    sortData();
                    break;
                case 3:
                    showData();
                    break;
                case 4:
                    System.out.println("Выход из программы...");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("=== Меню сортировки автомобилей ===");
        System.out.println("1. Загрузить данные автомобилей");
        System.out.println("2. Отсортировать данные");
        System.out.println("3. Показать данные");
        System.out.println("4. Выход");
        System.out.print("Выберите опцию: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void loadData() {
        System.out.println("\n=== Загрузка данных ===");

        cars.clear();

        cars.add(new Car("Toyota Camry", 2018, 180));
        cars.add(new Car("BMW 3 Series", 2020, 255));
        cars.add(new Car("Toyota Camry", 2018, 150));
        cars.add(new Car("Audi A4", 2020, 190));
        cars.add(new Car("Volkswagen Passat", 2015, 150));
        cars.add(new Car("Kia Rio", 2022, 120));
        cars.add(new Car("BMW 3 Series", 2020, 184));
        cars.add(new Car("Honda Civic", 2019, 158));
        cars.add(new Car("Audi A4", 2020, 190));
        cars.add(new Car("Ford Focus", 2017, 125));

        dataLoaded = true;
        System.out.println("Загружено " + cars.size() + " автомобилей.");
        System.out.println("Пример данных:");
        showDataPreview();
    }

    private void showDataPreview() {
        if (cars.isEmpty()) {
            System.out.println("Нет данных для отображения.");
            return;
        }

        System.out.println("Первые 3 записи:");
        for (int i = 0; i < Math.min(3, cars.size()); i++) {
            Car car = cars.get(i);
            System.out.printf("%d. %s, %d год, %d л.с.\n",
                    i + 1, car.getModel(), car.getYear(), car.getPower());
        }
    }

    private void sortData() {
        if (!dataLoaded) {
            System.out.println("\nОшибка: Данные не загружены. Сначала загрузите данные.");
            return;
        }

        System.out.println("\n=== Сортировка данных ===");
        System.out.println("Выполняется сортировка по году, мощности и модели...");

        SortStrategy<Car> strategy = new YearPowerModelSortStrategy();


        long startTime = System.currentTimeMillis();


        strategy.sort(cars);

        long endTime = System.currentTimeMillis();

        System.out.println("Сортировка завершена.");
        System.out.println("Время выполнения: " + (endTime - startTime) + " мс");
        System.out.println("Отсортировано " + cars.size() + " автомобилей.");
    }

    private void showData() {
        if (!dataLoaded) {
            System.out.println("\nОшибка: Данные не загружены. Сначала загрузите данные.");
            return;
        }

        if (cars.isEmpty()) {
            System.out.println("\nСписок автомобилей пуст.");
            return;
        }

        System.out.println("\n=== Список автомобилей ===");
        System.out.println("Всего автомобилей: " + cars.size());
        System.out.println();

        System.out.printf("%-5s %-20s %-8s %-10s\n",
                "№", "Модель", "Год", "Мощность");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.printf("%-5d %-20s %-8d %-10d\n",
                    i + 1,
                    car.getModel(),
                    car.getYear(),
                    car.getPower());
        }
    }


    public static void main(String[] args) {
        CarSortingApp app = new CarSortingApp();
        app.run();
    }
}