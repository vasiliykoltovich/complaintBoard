package com.epam.web.controllers;

import com.epam.exceptions.CustomException;
import epam.beans.Feedback;
import epam.beans.Organization;
import epam.beans.RoleType;
import epam.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import services.contract.GenericCRUDService;

@Controller
@SessionAttributes("currentUser")
public class FeedbackController  extends AbstractController{
    private static final Logger logger = LoggerFactory.getLogger(FeedbackController .class);

    @GetMapping("/getFeedback")
    public ModelAndView getFeedback(@RequestParam(value = "login") String name) {

        Feedback feedback = feedbackServiceImpl.get(name);
        logger.debug("/get feedback : "+feedback);
        return new ModelAndView("pages/userpage", "feedback", feedback);
    }

    @GetMapping("/newFeedback")
    public ModelAndView showFeedbackForm(@ModelAttribute() Feedback feedback,@ModelAttribute("user") User user) {
        List<Organization> organizationList = organizationServiceImpl.getAll();
        List<Number> rates = Arrays.asList(1, 2, 3, 4, 5);
        ModelAndView feedbackForm = new ModelAndView("pages/createFeedback");
        feedbackForm.addObject("organizations", organizationList);
        feedbackForm.addObject("rates", rates);

        logger.debug("/new feedback");
        return feedbackForm;
    }

    @GetMapping("/newFeedback/{name}")
    public ModelAndView relateFeedbackToOrganization(@ModelAttribute() Feedback feedback, @ModelAttribute("user") User user, @PathVariable String name ) {
        List<Number> rates = Arrays.asList(1, 2, 3, 4, 5);
        ModelAndView feedbackForm = new ModelAndView("pages/createFeedback");
        feedbackForm.addObject("organization", organizationServiceImpl.get(name));
        feedbackForm.addObject("rates", rates);
        logger.debug("/new feedback for "+name);
        return feedbackForm;
    }


    @GetMapping("/manageFeedbacks")
    public String showFeedbacks(@ModelAttribute("user") User user) {
        List<Feedback> feedbacks = feedbackServiceImpl.getAll();

        return "redirect:/";
    }

    @PostMapping("/postNewFeedback")
    public String postFeedback(@Valid @ModelAttribute() Feedback feedback,@ModelAttribute User user, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "pages/createFeedback";
        }

        user = (User) session.getAttribute("currentUser");

        if (user.getRoleType() == RoleType.ANONYMOUS) {
            user=userServiceImpl.get("anonymous");
            feedback.setAuthor(user);
        }
        feedback.setAuthor(user);
        feedbackServiceImpl.create(feedback);
        logger.debug("/New feedback "+feedback+" posted ");
        return "redirect:/";
    }


    @DeleteMapping("/deleteFeedback/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackServiceImpl.delete(id);
        logger.debug("feedback with id "+id+" was deleted ");
        return "redirect:/";
    }

}
