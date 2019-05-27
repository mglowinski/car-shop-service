package com.xing.gccars.service;

import com.xing.gccars.exception.UserNotFoundException;
import com.xing.gccars.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User saveUser(User user);

    User getUserByEmail(String email);

    User getUserById(Long id) throws UserNotFoundException;

    void deleteUserById(Long id) throws UserNotFoundException;
}
