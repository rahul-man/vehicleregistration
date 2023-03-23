package com.wipro.asg.vehicleregistration.controller;

import com.wipro.asg.vehicleregistration.model.Customer;
import com.wipro.asg.vehicleregistration.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.wipro.asg.vehicleregistration.model.constants.Constants.APPROVED;
import static com.wipro.asg.vehicleregistration.model.constants.Constants.DECLINED;

@Slf4j
@Controller
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String getAdminHome(Model model) {
        model.addAttribute("customer", new Customer());
        return "search";
    }

    @RequestMapping(value = "/adminHome", method = RequestMethod.POST)
    public String searchRequest(@RequestParam("status") String status, @RequestParam("rtoOffice") String rtoOffice,
                                RedirectAttributes redirectAttributes) {
        log.info("Fetching Customer by Status[{}], RTO[{}]", status, rtoOffice);
        List<Customer> customers = adminService.fetchRequest(status, rtoOffice);
        if (customers.isEmpty()) {
            log.info("No records found for the customer with Status[{}], RTO[{}]", status, rtoOffice);
            redirectAttributes.addFlashAttribute("msg", "No records found");
        }
        redirectAttributes.addFlashAttribute("customers", customers);
        redirectAttributes.addFlashAttribute("status", status);
        redirectAttributes.addFlashAttribute("rtoOffice", rtoOffice);
        return "redirect:/adminHome";
    }

    @RequestMapping(value = "/reqValidate", method = RequestMethod.GET)
    public String getAdminHome(@RequestParam("requestId") String requestId, Model model) {
        Customer customer = adminService.getCustomerByRequestId(requestId);
        String status = customer.getStatus();
        if (status.equals(APPROVED.getCode()) || status.equals(DECLINED.getCode())) {
            model.addAttribute("customer", customer);
            String plateNo = customer.getPlateNo();
            model.addAttribute("plate", plateNo);
            return "viewrequest";
        } else {
            model.addAttribute("customer", customer);
            return "viewrequest";
        }
    }

    @RequestMapping(value = "/reqValidate", method = RequestMethod.POST, params = "save")
    public String adminApproval(@ModelAttribute Customer customer, Model model) {
        Customer approvedCustomer = adminService.saveAdminApproval(customer);
        String plateNo = approvedCustomer.getPlateNo();
        model.addAttribute("plate", plateNo);
        model.addAttribute("msg", "Request Processed Successfully");
        return "viewrequest";
    }

    @RequestMapping(value = "/reqValidate", method = RequestMethod.POST, params = "cancel")
    public String adminRejection(@ModelAttribute Customer customer, Model model) {
        Customer rejectedCustomer = adminService.rejectAdminApproval(customer);
        String plateNo = rejectedCustomer.getPlateNo();
        model.addAttribute("plate", plateNo);
        model.addAttribute("msg", "Request Processed Successfully");
        return "viewrequest";
    }
}
