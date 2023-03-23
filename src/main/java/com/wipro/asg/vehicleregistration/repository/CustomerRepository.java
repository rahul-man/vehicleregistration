package com.wipro.asg.vehicleregistration.repository;

import com.wipro.asg.vehicleregistration.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByRequestId(String id);

    List<Customer> findByStatusAndRtoOffice(String status, String rtoOffice);

}
