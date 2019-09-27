package com.cp.mapper;

import com.cp.pojo.User;

import java.util.List;

/**
 * cp 2019-09-10  15:30
 */
public interface UserMapper {
    List<User> findAll();
    List<User> findUserByUsernameAndGender(String username, String gender);
    User findById(Integer id);
    User findByUserNameAndPassword(String username, String password);
    User findByUserName(String username);
    void add(User user);
    void update(User user);
    void deleteById(Integer id);
}
