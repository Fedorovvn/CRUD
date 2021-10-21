package web.servise;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarServise {

    public List<Car> getCars(List<Car> cars, int count) {

        List<Car> carsResult = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            carsResult.add(cars.get(i));
        }

        return carsResult;
    }


}
