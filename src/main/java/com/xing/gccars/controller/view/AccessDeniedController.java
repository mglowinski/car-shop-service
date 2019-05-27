package com.xing.gccars.controller.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AccessDeniedController {

    @GetMapping("/access-denied")
    public ModelAndView getEmployeePanel() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("access_denied");
        return modelAndView;
    }

}
