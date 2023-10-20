package com.Ivan.webApp_boot.web.service;

import com.Ivan.webApp_boot.web.entity.User;

import java.util.List;

public interface UserService {

    void add(User user);

    void removeUser(int id);

    List<User> getAllUsers();

    void updateUser(User editedUser, int id);

    User getUserById(int id);



}
