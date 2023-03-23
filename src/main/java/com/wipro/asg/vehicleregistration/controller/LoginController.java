package com.wipro.asg.vehicleregistration.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String processLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        String errorMessage = null;
        if (error != null) {
            LOGGER.error("Unauthorized: Invalid Credentials");
            errorMessage = "Invalid Input";
        }
        model.addAttribute("error", errorMessage);
        return "login";
    }
}
