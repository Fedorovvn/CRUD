package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import web.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.servise.CarServise;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String printWelcome(@RequestParam(value = "count", required = false) Integer count, ModelMap model) {

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Mercedes", "GLC", 2018));
        cars.add(new Car("Skoda", "Rapid", 2019));
        cars.add(new Car("Dodge", "Challenger", 1997));
        cars.add(new Car("Mazda", "CX5", 2001));
        cars.add(new Car("Renault", "Logan", 2012));

        if (count != null && count < 5 && count > 0) {
            CarServise carServise = new CarServise();
            cars = carServise.getCars(cars, count);
        }

        model.addAttribute("cars", cars);
        return "cars";
    }
}
