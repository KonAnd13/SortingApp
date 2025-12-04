package datasource;

public interface DataSource {
    List<Car> loadCars(int count);
}