package com.cp.service;

import com.cp.pojo.User;

import java.util.List;

/**
 * cp 2019-09-10  15:42
 */
public interface UserService {
    void register(User user);
    User checkUsername(String username);

    User login(String username, String password);

    List<User> findAllUser();

    void deleteUserById(int parseInt);

    List<User> findUserByUsernameAndGender(String username, String gender);
}
