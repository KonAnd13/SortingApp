package util;

import domain.Car;
import service.sorting.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        cars.add(new Car("Toyota Camry", 180, 2018));
        cars.add(new Car("BMW 3 Series", 255, 2020));
        cars.add(new Car("Toyota Camry", 150, 2018));
        cars.add(new Car("Audi A4", 190, 2020));
        cars.add(new Car("Volkswagen Passat", 150, 2015));
        cars.add(new Car("Kia Rio", 120, 2022));
        cars.add(new Car("BMW 3 Series", 184, 2020));
        cars.add(new Car("Honda Civic", 158, 2019));
        cars.add(new Car("Audi A4", 190, 2020));
        cars.add(new Car("Ford Focus", 125, 2017));

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
        System.out.println("\nCпособы сортировки:");
        System.out.println("1. Мощность, год, модель");
        System.out.println("2. Год, мощность, модель");
        System.out.print("Выберите опцию: ");

        int choice = getUserChoice();

        switch (choice) {
            case 1:
                sorter.setSortStrategy(new PowerYearModelSortStrategy());
                break;
            case 2:
                sorter.setSortStrategy(new YearPowerModelSortStrategy());
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
                return;
        }

        sorter.sort(cars);

        System.out.println("Данные отсортированы");
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