package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Autowired

    public AdminController(UserService userService, RoleService roleService,
                           UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }


    @GetMapping("")
    public String showAllUsers(ModelMap model, Principal principal) {
        model.addAttribute("admin", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("people", userService.getAllUser());
        model.addAttribute("person", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "admin";
    }

    @GetMapping("/userInfo")
    public String userInfo(ModelMap model) {
        model.addAttribute("person", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "userInfo";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("person") User user, Map<String, Object> model) {
        User userFromDb = userRepository.findUserByName(user.getUsername());
        if(userFromDb != null) {
            model.put("message", "User exists!");
            return "admin";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PutMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("person", userService.findUserById(id));
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        return "userInfo";
    }

}