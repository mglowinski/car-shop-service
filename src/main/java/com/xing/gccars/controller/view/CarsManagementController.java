package com.xing.gccars.controller.view;

import com.xing.gccars.exception.CarNotFoundException;
import com.xing.gccars.model.Car;
import com.xing.gccars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CarsManagementController {

    private final CarService carService;

    @Autowired
    public CarsManagementController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/edit/{carId}")
    public ModelAndView editCar(@PathVariable Long carId) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Car carById = carService.getCarById(carId);
            modelAndView.addObject("car", carById);
            modelAndView.setViewName("editCar");
        } catch (CarNotFoundException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @GetMapping("/cars/add")
    public ModelAndView addCar() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("car", new Car());
        modelAndView.setViewName("addCar");
        return modelAndView;
    }

    @GetMapping("/cars/delete/{carId}")
    public ModelAndView deleteStudent(@PathVariable("carId") Long carId) {
        try {
            carService.deleteCarById(carId);
        } catch (CarNotFoundException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/employees/panel");
    }

    @PostMapping("/cars/save")
    public ModelAndView save(Car car) {
        carService.saveCar(car);
        return new ModelAndView("redirect:/cars");
    }

}
