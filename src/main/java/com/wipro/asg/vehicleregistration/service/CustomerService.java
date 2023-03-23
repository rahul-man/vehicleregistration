package com.wipro.asg.vehicleregistration.service;

import com.wipro.asg.vehicleregistration.model.Customer;
import com.wipro.asg.vehicleregistration.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.wipro.asg.vehicleregistration.model.constants.Constants.PENDING;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(Customer customer) {
        LOGGER.info("Trying to register the customer");
        String newRequestId;
        String lastRequestId = getLastRequestId();
        LOGGER.info("Generated the last requestId:{}", lastRequestId);
        if (lastRequestId == null) {
            newRequestId = "100000";
        } else {
            int lastRequestNo = Integer.parseInt(lastRequestId) + 1;
            newRequestId = String.format("%06d", lastRequestNo);
            LOGGER.info("Generated the new requestId:{}", newRequestId);

        }
        customer.setRequestId(newRequestId);
        customer.setPlateNo("-");
        customer.setStatus(PENDING.getCode());
        LOGGER.info("Saving the customer details:{}", customer);
        customerRepository.save(customer);
    }

    private String getLastRequestId() {
        String lastRequestNo = null;
        List<Customer> customers = customerRepository.findAll();
        if (!customers.isEmpty()) {
            int listSize = customers.size();
            lastRequestNo = customers.get(listSize - 1).getRequestId();
        }
        return lastRequestNo;
    }

    public List<Customer> listAllUsers() {
        List<Customer> customers = customerRepository.findAll();
        LOGGER.info("Fetching all customer:{}", customers);
        return customers.stream()
                .sorted(Comparator.comparing(Customer::getRequestId))
                .collect(Collectors.toList());
    }
}
