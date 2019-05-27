package com.xing.gccars.controller.view;

import com.xing.gccars.model.Car;
import com.xing.gccars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class IndexController {

    private final CarService carService;

    @Autowired
    public IndexController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping({"/cars", "/"})
    public ModelAndView index() {
        List<Car> cars = carService.getCars();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars");
        modelAndView.addObject("cars", cars);

        return modelAndView;
    }

}
