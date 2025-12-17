package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Car;
import service.datasource.DataSource;
import service.datasource.FileDataSource;
import service.datasource.ManualDataSource;
import service.datasource.RandomDataSource;
import service.sorting.CustomSortStrategy;
import service.sorting.PowerYearModelSortStrategy;
import service.sorting.Sorter;
import service.sorting.YearPowerModelSortStrategy;

public class CarSortingApp {
    private List<Car> cars;
    private Scanner scanner;
    private Sorter sorter;
    private boolean dataLoaded = false;

    public CarSortingApp() {
        this.cars = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.sorter = new Sorter();
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
        System.out.print("\nВыберите опцию: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

        private void loadData() {
            System.out.println("\n=== Выбор источника данных ===");
            System.out.println("1. Случайная генерация");
            System.out.println("2. Ручной ввод");
            System.out.println("3. Загрузка из файла");
            System.out.print("Выберите источник (1-3): ");

            int sourceChoice = getUserChoice();
            DataSource dataSource = null;

            try {
                switch (sourceChoice) {
                    case 1:
                        dataSource = new RandomDataSource();
                        break;
                    case 2:
                        dataSource = new ManualDataSource();
                        break;
                    case 3:
                        dataSource = new FileDataSource("src/main/resources/carsValid.txt");
                        break;
                    default:
                        System.out.println("Неверный выбор источника данных. Возврат в меню.");
                        return;
                }

                if (dataSource == null) {
                    System.out.println("Не удалось инициализировать источник данных.");
                    return;
                }

                List<Car> loadedCars = dataSource.loadCars();

                if (loadedCars != null && !loadedCars.isEmpty()) {
                    this.cars = loadedCars;
                    dataLoaded = true;
                    System.out.println("Успешно загружено " + cars.size() + " автомобилей.");
                } else if (loadedCars != null && loadedCars.isEmpty()) {
                    System.out.println("Данные загружены, но список пуст.");
                    this.cars = loadedCars;
                    dataLoaded = true;
                } else {
                    System.out.println("Не удалось загрузить данные!");
                    dataLoaded = false;
                }

            } catch (Exception e) {
                System.err.println("Произошла непредвиденная ошибка при загрузке данных: " + e.getMessage());
                dataLoaded = false;
            }
        }

    private void sortData() {
        if (!dataLoaded) {
            System.out.println("\nОшибка: Данные не загружены. Сначала загрузите данные.");
            return;
        }
        System.out.println("\nCпособы сортировки:");
        System.out.println("1. Мощность -> год -> модель");
        System.out.println("2. Год -> мощность -> модель");
        System.out.println("3. По году производства (четные)");
        System.out.print("\nВыберите опцию: ");

        int choice = getUserChoice();

        switch (choice) {
            case 1:
                sorter.setSortStrategy(new PowerYearModelSortStrategy());
                break;
            case 2:
                sorter.setSortStrategy(new YearPowerModelSortStrategy());
                break;
            case 3:
                sorter.setSortStrategy(new CustomSortStrategy());
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
                return;
        }

        sorter.sort(cars);
        System.out.println("\nДанные отсортированы");
    }

    private void showData() {
        if (!dataLoaded) {
            System.out.println("\nОшибка: Данные не загружены. Сначала загрузите данные.");
            return;
        }

        // Используем метод displayCarsSimple() для показа данных
        displayCarsSimple();
    }

    private void displayCarsSimple() {
        if (cars == null || cars.isEmpty()) {
            System.out.println("\n[INFO] Список автомобилей пуст.");
            return;
        }

        System.out.println("\n=== СПИСОК АВТОМОБИЛЕЙ ===");
        System.out.printf("Всего автомобилей: %d\n\n", cars.size());

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.printf("%3d. %-25s %6d г.  %4d л.с.\n",
                    i + 1,
                    car.getModel(),
                    car.getYear(),
                    car.getPower());
        }
    }

    // Дополнительные методы для разных форматов вывода (опционально)

    private void displayCarsTable() {
        if (cars == null || cars.isEmpty()) {
            System.out.println("\n[INFO] Список автомобилей пуст.");
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

    private void displayCarsWithBorders() {
        if (cars == null || cars.isEmpty()) {
            System.out.println("\n[INFO] Список автомобилей пуст.");
            return;
        }

        System.out.println("\n=== СПИСОК АВТОМОБИЛЕЙ ===");
        System.out.printf("Всего автомобилей: %d\n\n", cars.size());

        System.out.println("┌─────┬──────────────────────────┬────────────┬────────────┐");
        System.out.println("│ №   │ Модель                   │ Год выпуска│ Мощность   │");
        System.out.println("├─────┼──────────────────────────┼────────────┼────────────┤");

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            String model = car.getModel();
            // Обрезаем длинные названия моделей
            if (model.length() > 25) {
                model = model.substring(0, 22) + "...";
            }

            System.out.printf("│ %-3d │ %-25s │ %-10d │ %-10d │\n",
                    i + 1, model, car.getYear(), car.getPower());
        }

        System.out.println("└─────┴──────────────────────────┴────────────┴────────────┘");
    }

    public static void main(String[] args) {
        CarSortingApp app = new CarSortingApp();
        app.run();
    }
}