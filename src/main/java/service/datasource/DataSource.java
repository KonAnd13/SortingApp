package service.datasource;
import domain.Car;
import java.util.List;

public interface DataSource {
    List<Car> loadCars();
}