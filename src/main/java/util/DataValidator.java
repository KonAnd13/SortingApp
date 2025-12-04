package util;

public class DataValidator {
    String model;
    int power;
    int year;

    public DataValidator(String model, int power, int year) {
        this.model = model;
        this.power = power;
        this.year = year;
    }
    public static boolean isValidModel(String model){
        if (model == null || model.trim().isEmpty()) {
            return false;
        }
        if (!model.trim().matches("[a-zA-Z0-9\\s-]+")) {
            return false;
        }
        if (model.length() > 50) {
            return false;
        }
        return true;
    }
    public static boolean isValidPower(int power){
        if (power < 1 || power > 2000) {
            return false;
        }
        return true;
    }
    public static boolean isValidYear(int year){
        final int CURRENT_YEAR = java.time.Year.now().getValue();
        final int MIN_YEAR = 1886;

        if (year < MIN_YEAR || year > CURRENT_YEAR) {
            return false;
        }
        return true;
    }
    public static void validateCar() throws ValidationException {
        if (!isValidModel(this.model)) {
            throw new ValidationException("Ошибка валидации модели: '" + this.model + "'");
        }
        if (!isValidPower(this.power)) {
            throw new ValidationException("Ошибка валидации мощности: '" + this.power + "' (ожидается от 1 до 2000)");
        }
        if (!isValidYear(this.year)) {
            final int CURRENT_YEAR = java.time.Year.now().getValue();
            final int MIN_YEAR = 1886;
            throw new ValidationException("Ошибка валидации года выпуска: '" + this.year + "' (ожидается от " + MIN_YEAR + " до " + CURRENT_YEAR + ")");
        }
    }
}