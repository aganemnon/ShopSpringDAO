package ru.nc.ibublig.catalogsb.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nc.ibublig.catalogsb.dao.AppRoleDAO;
import ru.nc.ibublig.catalogsb.dao.AppUserDAO;
import ru.nc.ibublig.catalogsb.model.AppUser;

import java.util.Map;

@Controller
@RequestMapping("/userlist")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserListController {

    @Autowired
    AppUserDAO appUserDAO;
    @Autowired
    AppRoleDAO roleDAO;

    @GetMapping
    public String userList(Map<String,Object> model){
        model.put("users", appUserDAO.listUserAccount());
        return "admin/userlist";
    }
    @GetMapping("{userId}")
    public String editUser(@PathVariable Long userId, Map<String,Object> model){
        model.put("user", appUserDAO.findUserById(userId));
        model.put("role", roleDAO.getRoleNames(userId));

        return "admin/edituser";
    }
    @PostMapping
    public String saveUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam Long userId,
            Map<String,Object> model){
        if (password == ""){
            appUserDAO.updateUser(userId,username);
        } else {
            appUserDAO.updateUser(userId,username,password);
        }
        if (role.equals("ROLE_ADMIN")){
            roleDAO.updateRole(userId,AppUser.ROLE_ADMIN);
        } else {
            roleDAO.updateRole(userId, AppUser.ROLE_USER);
        }
        model.put("users", appUserDAO.listUserAccount());
        return "redirect:/userlist";
    }

}
