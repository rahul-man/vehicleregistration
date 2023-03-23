package com.wipro.asg.vehicleregistration.controller;

import com.wipro.asg.vehicleregistration.model.Customer;
import com.wipro.asg.vehicleregistration.model.Response;
import com.wipro.asg.vehicleregistration.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/newRegistration", method = RequestMethod.GET)
    public String getRegisterView(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @RequestMapping(value = "/newRegistration", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> registerCustomer(@Valid @RequestBody Customer customer, Errors errors) {

        Response response = new Response();

        if (errors.hasErrors()) {
            response.setStatus("error");
            LOGGER.error("Error making request: Bad Request{}", customer);
            return ResponseEntity.badRequest().body(response);
        }
        customerService.registerCustomer(customer);
        LOGGER.info("Successfully registered the customer");
        response.setStatus("success");
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/myRequests", method = RequestMethod.GET)
    public String showRequest(Model model) {
        model.addAttribute("msg", "No vehicles registered yet.");
        model.addAttribute("requests", customerService.listAllUsers());
        return "request";
    }
}
