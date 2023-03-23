package com.wipro.asg.vehicleregistration.model;

import com.wipro.asg.vehicleregistration.validator.annotations.AlphabetsField;
import com.wipro.asg.vehicleregistration.validator.annotations.AlphanumericField;
import com.wipro.asg.vehicleregistration.validator.annotations.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private String requestId;

    @AlphabetsField(message = "Name should be alphabets of max 25 characters")
    private String name;

    @NotBlank(message = "Address can't be empty")
    @Size(max = 100, message = "Address should be of {max} characters")
    private String address;

    @Min(value = 18, message = "Age should be minimum 18")
    @Max(value = 50, message = "Age should be maximum 50")
    private String age;

    @Gender (message = "Gender should be alphabets of max 8 characters")
    private String gender;

    private String rtoOffice;

    @AlphanumericField(min = 6, max = 10, message = "Insurance should be alphanumeric between {min} and {max} characters")
    private String insuranceNo;

    @AlphanumericField(min = 8, max = 15, message = "License should be alphanumeric between {min} and {max} characters")
    private String license;

    @AlphanumericField(max = 25, message = "Model should be alphanumeric of max {max} characters")
    private String model;

    @AlphanumericField(max = 25, message = "Make should be alphanumeric of max {max} characters")
    private String make;

    private String vehicleType;

    @AlphanumericField(min = 8, max = 10, message = "Engine No should be alphanumeric between {min} and {max} characters")
    private String engineNo;

    private String plateNo;

    private String status;
}
