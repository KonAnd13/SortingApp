package domain;

import java.util.Objects;

public class Car {

    private static final String CAR_STRING_FORMAT = "%s;%s;%s";

    private String model;
    private int power;
    private int year;

    public Car(String model, int power, int year) {
        this.model = model;
        this.power = power;
        this.year = year;
    }

    /** Метод для записи данных в файл. */
    @Override
    public String toString() {
        return CAR_STRING_FORMAT.formatted(this.model, this.power, this.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.model, this.power, this.year);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Car)) {
            return false;
        } else {
            return (Objects.equals(this.model, ((Car) obj).model))
                    && (this.power == ((Car)obj).power)
                    && (this.year == ((Car)obj).year);
        }
    }

    public String getModel() {
        return this.model;
    }

    public int getPower() {
        return this.power;
    }

    public int getYear() {
        return this.year;
    }
}
