package com.lambdaschool.zoos.service;

import com.lambdaschool.zoos.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findUserByName(String name);
    void delete(long id);
    User save(User user);
    User update(User user, long id);
}