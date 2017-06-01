package com.epam.web.controllers;

import epam.beans.Feedback;
import epam.beans.Organization;
import epam.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import services.contract.GenericCRUDService;

public abstract class AbstractController {

    @Autowired
    protected GenericCRUDService<User, Long> userServiceImpl;
    @Autowired
    protected GenericCRUDService<Organization, Long> organizationServiceImpl;
    @Autowired
    protected GenericCRUDService<Feedback, Long> feedbackServiceImpl;

    /**
     * Parent method to handle exceptions from all controllers
     *
     * @param ex Exception
     *
     * @return ModelAndView
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView errorHandle(Exception ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", ex);
        model.setViewName("pages/exceptionPage");
        return model;
    }

}
