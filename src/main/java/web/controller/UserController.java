package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.*;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ModelAndView getUsers() {
        List<User> usersList = userService.getUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usersList", usersList);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @GetMapping(value = "/admin")
    public ModelAndView showUsers() {
        List<User> usersList = userService.getUsers();
        List<Role> rolesList = userService.getAllRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usersList", usersList);
        modelAndView.addObject("rolesList", rolesList);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("show");
        return modelAndView;
    }

    @PostMapping(path = "/admin/adduser")
    public ModelAndView addUser(@ModelAttribute("user") User user,
                                @RequestParam("roles") String[] rolesName) {
        Set<Role> tmpRole = new HashSet<>();
        if(rolesName.length==0) {
            tmpRole.add(userService.getRoleByName("User"));
        } else {
            for (String role : rolesName) {
                tmpRole.add(userService.getRoleByName(role));
            }
        }

        user.setRoles(tmpRole);
        userService.addUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @GetMapping(value = "/admin/edituser/{id}")
    public ModelAndView showEditUser(@PathVariable("id") Long id)
    {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getById(id);
        System.out.println("We edited user: " + user);
        modelAndView.addObject("user", user);
        modelAndView.addObject("rolesList", userService.getAllRoles());
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @PostMapping(value = "/admin/edituser")
    public ModelAndView editUser(@ModelAttribute("user") User user,
                                 @RequestParam("roles") String[] rolesName) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("We merge edited user: " + user);
        System.out.println("With roles: " + rolesName);
        Set<Role> tmpRole = new HashSet<>();
        for(String role : rolesName) {
            tmpRole.add(userService.getRoleByName(role));
        }
        user.setRoles(tmpRole);
        modelAndView.setViewName("redirect:/admin");
        userService.editUser(user);
        return modelAndView;
    }

    @GetMapping(value = "/admin/deluser/{id}")
    public ModelAndView delUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.delUser(id);
        return modelAndView;
    }

}
