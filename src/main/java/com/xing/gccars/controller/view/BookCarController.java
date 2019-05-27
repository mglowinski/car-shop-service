package com.xing.gccars.controller.view;

import com.xing.gccars.exception.CarNotFoundException;
import com.xing.gccars.model.BorrowedDate;
import com.xing.gccars.model.Car;
import com.xing.gccars.model.User;
import com.xing.gccars.service.BookCarService;
import com.xing.gccars.service.CarService;
import com.xing.gccars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@RestController
@SessionAttributes({"car", "borrowedDate"})
public class BookCarController {

    private final CarService carService;
    private final BookCarService bookCarService;
    private final UserService userService;

    @Autowired
    public BookCarController(CarService carService,
                             BookCarService bookCarService,
                             UserService userService) {
        this.carService = carService;
        this.bookCarService = bookCarService;
        this.userService = userService;
    }

    @GetMapping("/cars/{carId}/books")
    public ModelAndView checkDates(@PathVariable Long carId,
                                   @RequestParam(value = "start_date", defaultValue = "1800-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
                                   @RequestParam(value = "end_date", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Car car = carService.getCarById(carId);

            boolean isCarAvailable =
                    bookCarService.checkAvailabilityCarById(startDate, endDate, carId);

            modelAndView.addObject("carById", car);
            modelAndView.addObject("availableCarById", isCarAvailable);
            modelAndView.setViewName("bookCar");
        } catch (CarNotFoundException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PostMapping("/cars/{carId}/books")
    public ModelAndView bookCar(@PathVariable Long carId,
                                @RequestParam(value = "start_date", defaultValue = "1800-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
                                @RequestParam(value = "end_date", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try {
            Car car = carService.getCarById(carId);
            User user = userService.getUserByEmail(auth.getName());

            BorrowedDate borrowedDate = BorrowedDate.builder()
                    .car(car)
                    .user(user)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();

            modelAndView.addObject("car", car);
            modelAndView.addObject("borrowedDate", borrowedDate);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("bookCarSummary");
        } catch (CarNotFoundException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

}
