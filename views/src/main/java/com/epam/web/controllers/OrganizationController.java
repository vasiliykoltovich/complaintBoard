package com.epam.web.controllers;

import com.epam.exceptions.CustomException;
import epam.beans.Feedback;
import epam.beans.Organization;
import epam.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("currentUser")
public class OrganizationController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationController .class);

    @GetMapping("/getOrganizationName")
    public ModelAndView getOrganization(@RequestParam(value = "name") String name) {

        Organization organization = organizationServiceImpl.get(name);
        return new ModelAndView("pages/userpage", "organization", organization);
    }

    @GetMapping("/getOrganizations")
    public ModelAndView getOrganizations(@ModelAttribute("user") User user) {

        List<Organization> organizationList = organizationServiceImpl.getAll();
        return new ModelAndView("pages/organizationsList", "organizations", organizationList);
    }

    @GetMapping("/organizationInfo/{name}")
    public ModelAndView showOrganizationInfo(@PathVariable("name") String name, @ModelAttribute() Organization org, @ModelAttribute("user") User user) {

        Organization organization = organizationServiceImpl.get(name);

        List<Feedback> feedbacks = feedbackServiceImpl.getAsListByOrganization(name);

        ModelAndView modelAndView = new ModelAndView("pages/organizationInfo");
        modelAndView.addObject("organization", organization);
        modelAndView.addObject("feedbacks", feedbacks);
        logger.debug("/organization information for "+name);
        return modelAndView;
    }

    @GetMapping("/manageOrganizations")
    public ModelAndView showOrganizations(@ModelAttribute("user") User user) {
        List<Organization> organizationList = organizationServiceImpl.getAll();

        Map<String, Double> averageRate = countAverageRateForOrganization(organizationList);
        Map<String, Integer> countFeedbacks = countFeedbacksForOrganization(organizationList);

        System.out.println(averageRate);
        System.out.println(countFeedbacks);

        ModelAndView orgList=new ModelAndView();

        orgList.addObject("organizations", organizationList);
        orgList.addObject("averageRates",averageRate);
        orgList.addObject("count",countFeedbacks);
        orgList.setViewName("pages/organizationsList");
        return orgList;
    }

    @GetMapping("/manageOrganization/{id}")
    public ModelAndView manageOrganization(@ModelAttribute("user") User user,@PathVariable("id") Long id) throws CustomException {
            ModelAndView modelAndView = new ModelAndView();
            Organization organizationUser = organizationServiceImpl.get(id);

            List<Feedback> feedbacks = feedbackServiceImpl.getAsListByOrganization(organizationUser.getName());

            modelAndView.addObject("organization", organizationUser);
            modelAndView.addObject("feedbacks", feedbacks);
            modelAndView.setViewName("pages/organizationInfo");
            logger.debug("/organization information for " + organizationUser.getName());
            return modelAndView;
    }

    @GetMapping("/newOrganizationForm")
    public ModelAndView showNewOrganizationForm(@ModelAttribute("user") User user, @ModelAttribute() Organization organization) {
        ModelAndView view = new ModelAndView("pages/organizationForm");
        return view;

    }

    @PostMapping("/addOrganization")
    public String addOrganization(@Valid @ModelAttribute() Organization organization, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "pages/addOrganization";
        }

        organizationServiceImpl.create(organization);
        logger.debug("/New organization "+organization+" posted ");
        return "redirect:/manageOrganizations";

    }

    @PutMapping("/updateOrganization/{name}")
    public String updateOrganization(@Valid @ModelAttribute() Organization organization, @PathVariable("name") String name, BindingResult bindingResult, HttpSession session) {

        Organization savedOrganization=organizationServiceImpl.get(name);
        savedOrganization.setName(organization.getName());
        organizationServiceImpl.update(savedOrganization);
        logger.debug("/organization with name "+name+" was updated to  "+savedOrganization.getName());
        return "redirect:/manageOrganizations";
    }



    @DeleteMapping("/deleteOrganization/{id}")
    public String deleteOrganization( @ModelAttribute() Organization organization,@PathVariable("id") Long id,  BindingResult bindingResult, HttpSession session) {
        organizationServiceImpl.delete(id);
        logger.debug("Organization with id "+id+" was deleted ");
        return "redirect:/manageOrganizations";
    }

    /**
     *  method return map of organization name as key and average rate of all feedbacks as value
     * @param organizations
     * @return Map
     */
    private Map<String, Double> countAverageRateForOrganization(List<Organization> organizations) {

        Map<String, Double> average = new HashMap<>();
        for (Organization org : organizations) {
            Double avg = feedbackServiceImpl.getAsListByOrganization(org.getName()).stream().mapToDouble((feed) -> feed.getRate()).average().orElse(0);
            average.put(org.getName(), avg);
        }
        return average;
    }

    /**
     * method return map of organization name as key and amount of feedbacks as value
     * @param organizations
     * @return Map
     */
    private Map<String, Integer> countFeedbacksForOrganization(List<Organization> organizations) {
        Map<String, Integer> amount = new HashMap<>();
        for (Organization org : organizations) {
            Integer count = feedbackServiceImpl.getAsListByOrganization(org.getName()).size();
            amount.put(org.getName(), count);
        }
        return amount;

    }


}
