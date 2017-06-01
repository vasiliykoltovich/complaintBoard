package com.epam.web.controllers;

import com.epam.exceptions.CustomException;
import epam.beans.RoleType;
import epam.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@SessionAttributes("currentUser")
public class UserController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getUser/{login}")
    public ModelAndView getUser(@PathVariable("login") String name) {

        User user = userServiceImpl.get(name);
        List<String> roles = Stream.of(RoleType.values()).map(RoleType::name).collect(Collectors.toList());

        List<RoleType> enumValues = new ArrayList<RoleType>(EnumSet.allOf(RoleType.class));

        ModelAndView view = new ModelAndView("pages/userpage");

        view.addObject("roles", RoleType.values());
        view.addObject("types", roles);
        view.addObject("rolet", enumValues);
        view.addObject("user", user);

        return view;
    }

    @GetMapping("/manageUsers")
    public ModelAndView showAllUsers(@ModelAttribute("user") User user) {
        List<User> usersList = userServiceImpl.getAll();

        ModelAndView view = new ModelAndView("pages/users");
        view.addObject("users", usersList);

        return view;
    }

    @GetMapping("/newUserForm")
    public ModelAndView showNewUserForm(@ModelAttribute("user") User user) {

        ModelAndView view = new ModelAndView("pages/createUser");
        view.addObject("roles", Arrays.stream(RoleType.values()).filter(roleType -> roleType.toString() != "ANONYMOUS").collect(Collectors.toList()));
        view.addObject("organizations", organizationServiceImpl.getAll());
        return view;
    }

    @PutMapping("/updateUser/{name}")
    public String updateUser(@Valid @ModelAttribute() User user, @PathVariable("name") String name, BindingResult bindingResult) {

        User savedUser = userServiceImpl.get(name);
        savedUser.setLogin(user.getLogin());
        userServiceImpl.update(savedUser);

        logger.debug("/user with name  " + name + " was updated ");
        return "redirect:/manageUsers";
    }

    //    @DeleteMapping("/deleteUser/{name}")
    //    public String deleteUser(@PathVariable("name") String name) {
    //        userServiceImpl.delete(name);
    //        logger.debug("user with name "+name+" was deleted ");
    //        return "redirect:/manageUsers";
    //    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        logger.debug("user with id " + id + " was deleted ");
        System.out.println("user with id " + id + " was deleted ");
        return "redirect:/manageUsers";
    }

    @PostMapping("/postUser")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            //            notifyService.addErrorMessage("Please fill the form correctly!");
            return "pages/postUser";
        }

        System.out.println("New User " + user.getOrganization());

        if (null == user.getOrganization().getId() && user.getRoleType().toString().equals("ADMIN")) {
            user.setOrganization(null);
        }

        userServiceImpl.create(user);
        logger.debug("/New user " + user + " was posted ");
        return "redirect:/manageUsers";
    }


}
