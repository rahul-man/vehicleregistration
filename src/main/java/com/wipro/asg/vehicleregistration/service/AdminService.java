package com.wipro.asg.vehicleregistration.service;

import com.wipro.asg.vehicleregistration.model.Customer;
import com.wipro.asg.vehicleregistration.repository.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.wipro.asg.vehicleregistration.model.constants.Constants.*;

@Service
public class AdminService {

    private final CustomerRepository customerRepository;

    @Autowired
    public AdminService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> fetchRequest(String status, String rtoOffice) {
        return customerRepository.findByStatusAndRtoOffice(status, rtoOffice);
    }

    public Customer getCustomerByRequestId(String requestId) {
        return customerRepository.findByRequestId(requestId);
    }

    public Customer saveAdminApproval(Customer customer) {
        Customer fetchedCustomer = customerRepository.findByRequestId(customer.getRequestId());
        String plateNo = fetchedCustomer.getPlateNo().replace("-", "");
        String rtoOffice = fetchedCustomer.getRtoOffice();
        String format = "%s-%s";
        if ("Chennai".equals(rtoOffice)) {
            plateNo = String.format(format, CHENNAI.getCode(), generatePlateNo());
        } else if ("Hyderabad".equals(rtoOffice)) {
            plateNo = String.format(format, HYDERABAD.getCode(), generatePlateNo());
        } else if ("Pune".equals(rtoOffice)) {
            plateNo = String.format(format, PUNE.getCode(), generatePlateNo());
        } else if ("Delhi".equals(rtoOffice)) {
            plateNo = String.format(format, DELHI.getCode(), generatePlateNo());
        } else if ("Bangalore".equals(rtoOffice)) {
            plateNo = String.format(format, BANGALORE.getCode(), generatePlateNo());
        }
        if (StringUtils.isEmpty(plateNo)) {
            fetchedCustomer.setPlateNo(plateNo);
            customerRepository.save(fetchedCustomer);
        } else {
            fetchedCustomer.setPlateNo(plateNo);
            fetchedCustomer.setStatus(APPROVED.getCode());
            customerRepository.save(fetchedCustomer);
        }
        return fetchedCustomer;
    }

    public Customer rejectAdminApproval(Customer customer) {
        Customer fetchedCustomer = customerRepository.findByRequestId(customer.getRequestId());
        fetchedCustomer.setPlateNo("-");
        fetchedCustomer.setStatus(DECLINED.getCode());
        customerRepository.save(fetchedCustomer);
        return fetchedCustomer;
    }

    private String generatePlateNo() {
        String newPlateNo;
        String lastPlateNo = getLastPlateNo();
        if (lastPlateNo == null) {
            newPlateNo = "1000";
        } else {
            int plateNo = Integer.parseInt(lastPlateNo) + 1;
            newPlateNo = String.format("%04d", plateNo);

        }
        return newPlateNo;
    }

    private String getLastPlateNo() {
        String plate = null;
        List<Customer> customers = customerRepository.findAll();
        List<Customer> approvedCustomers = customers.stream()
                .filter(status -> status.getStatus().equals(APPROVED.getCode()))
                .collect(Collectors.toList());
        if (!approvedCustomers.isEmpty()) {
            int listSize = approvedCustomers.size();
            plate = approvedCustomers.get(listSize - 1).getPlateNo().substring(6);
        }
        return plate;
    }
}