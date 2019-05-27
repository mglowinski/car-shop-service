package com.xing.gccars.controller.view;

import com.xing.gccars.model.BorrowedDate;
import com.xing.gccars.model.User;
import com.xing.gccars.service.BookCarService;
import com.xing.gccars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserProfileController {

    private final BookCarService bookCarService;
    private final UserService userService;

    @Autowired
    public UserProfileController(BookCarService bookCarService,
                                 UserService userService) {
        this.bookCarService = bookCarService;
        this.userService = userService;
    }

    @GetMapping("/users/profile")
    public ModelAndView getUserReservations() {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName());
        List<BorrowedDate> reservations = bookCarService.getBorrowedDatesByUserId(user.getId());

        modelAndView.addObject("reservations", reservations);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user_profile");
        return modelAndView;
    }

}
