package com.epam.web.controllers;

import com.epam.exceptions.CustomException;
import epam.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthentificationController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(AuthentificationController.class);

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "redirect:/loginError";
        }

        if (!userServiceImpl.exists(user)) {
            return "redirect:/loginError/" + user.getLogin();

        } else {
            user = userServiceImpl.get(user.getLogin());
            session.setAttribute("currentUser", user);
        }

        logger.debug("User " + user.getLogin() + " logged to application");

        return "redirect:/";

    }

    @GetMapping("/logout")
    public String logout(ModelMap model, HttpSession session) {
        session.removeAttribute("currentUser");
        session.invalidate();
        logger.debug("Logout..");
        return "forward:/";

    }

    @GetMapping("/loginError/{login}")
    public ModelAndView error403(@ModelAttribute("user") User user, @PathVariable String login) {
        ModelAndView error = new ModelAndView();
        error.addObject("error", "Login " + login + " not found in database");
        error.setViewName("pages/errorPage");
        return error;
    }


}
