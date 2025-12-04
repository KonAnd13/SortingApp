package domain;

public class CarBuilder {

    private String model;
    private int power;
    private int year;

    public CarBuilder model(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder power(int power) {
        this.power = power;
        return this;
    }

    public CarBuilder year(int year) {
        this.year = year;
        return this;
    }

    public Car build() {
        return new Car(this.model, this.power, this.year);
    }
}
