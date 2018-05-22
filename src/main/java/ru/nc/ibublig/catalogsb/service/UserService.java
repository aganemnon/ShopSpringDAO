package ru.nc.ibublig.catalogsb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nc.ibublig.catalogsb.dao.UserDAOImpl;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserDAOImpl userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.get(1L);
    }
}
