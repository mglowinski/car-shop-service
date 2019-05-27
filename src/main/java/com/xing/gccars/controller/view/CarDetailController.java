package com.xing.gccars.controller.view;

import com.xing.gccars.exception.CarNotFoundException;
import com.xing.gccars.model.Car;
import com.xing.gccars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CarDetailController {

    private final CarService carService;

    @Autowired
    public CarDetailController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/{carId}")
    public ModelAndView carDetail(@PathVariable Long carId) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Car carById = carService.getCarById(carId);
            modelAndView.addObject("carById", carById);
            modelAndView.setViewName("carDetail");
        } catch (CarNotFoundException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

}
