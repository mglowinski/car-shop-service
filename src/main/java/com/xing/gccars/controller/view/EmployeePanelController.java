package com.xing.gccars.controller.view;

import com.xing.gccars.model.BorrowedDate;
import com.xing.gccars.model.Car;
import com.xing.gccars.service.BookCarService;
import com.xing.gccars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EmployeePanelController {

    private final BookCarService bookCarService;
    private final CarService carService;

    @Autowired
    public EmployeePanelController(BookCarService bookCarService,
                                   CarService carService) {
        this.bookCarService = bookCarService;
        this.carService = carService;
    }

    @GetMapping("/employees/panel")
    public ModelAndView getEmployeePanel() {
        ModelAndView modelAndView = new ModelAndView();

        List<BorrowedDate> reservations = bookCarService.getBorrowedDates();
        List<Car> cars = carService.getCars();

        modelAndView.addObject("reservations", reservations);
        modelAndView.addObject("cars", cars);
        modelAndView.setViewName("employee_panel");
        return modelAndView;
    }

}
