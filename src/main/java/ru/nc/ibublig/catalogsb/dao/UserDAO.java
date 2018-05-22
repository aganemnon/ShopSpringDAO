package ru.nc.ibublig.catalogsb.dao;

import ru.nc.ibublig.catalogsb.model.User;

import java.util.List;

public interface UserDAO{

    void saveUser(User user);

    void delete(Long userID);

    User get(Long userID);

    List<User> list();

    User findByUsername(String username);
}
