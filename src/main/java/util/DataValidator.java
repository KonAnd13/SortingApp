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
    public static boolean validateCar(){
        boolean allValid = true;

        if (!isValidModel(this.model)) {
            allValid = false;
        }
        if (!isValidPower(this.power)) {
            allValid = false;
        }
        if (!isValidYear(this.year)) {
            allValid = false;
        }
        return allValid;
    }
}