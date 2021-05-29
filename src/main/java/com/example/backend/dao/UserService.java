package com.example.backend.dao;

import com.example.backend.entity.User;

import java.util.List;

public interface UserService
{
    List<User> findUser();
    User findUserById(long id);
    User insertUser(User user);
    User updateUserById(User user);
    void deleteUserById(long id);
}
