package com.epam.web.controllers;

import epam.beans.Feedback;
import epam.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.implementation.FeedbackServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;
import services.contract.GenericCRUDService;

@Controller
@SessionAttributes("currentUser")
public class HomeController extends AbstractController{
    private static final Logger logger = LoggerFactory.getLogger(HomeController .class);
    private User user;

    @GetMapping({"/","/home","/dash"})
    public ModelAndView home(HttpSession session) {
        user = (User) session.getAttribute("currentUser");
        if (user == null) {
            user = userServiceImpl.get("anonymous");
            session.setAttribute("currentUser", user);
        }

        List<Feedback> feedbackList = feedbackServiceImpl.getAll();
        List<Feedback> feedbackListLast5 = feedbackServiceImpl.getLatest5();
        ModelAndView dashboardPage = new ModelAndView();
        dashboardPage.addObject("user", user);
        dashboardPage.addObject("feedbacks", feedbackList);
        dashboardPage.addObject("feedbacksLast5", feedbackListLast5);
        dashboardPage.setViewName("pages/dashboard");

        return dashboardPage;

    }

}
