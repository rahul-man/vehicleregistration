package com.wipro.asg.vehicleregistration.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Constants {

    PENDING("Pending"),
    APPROVED("Approved"),
    DECLINED("Rejected"),
    ALPHABETS("^[a-zA-Z]*$"),
    ALPHANUMERIC("^[a-zA-Z0-9]+$"),
    CHENNAI("TN-11"),
    HYDERABAD("AP-42"),
    DELHI("DL-03"),
    PUNE("MH-54"),
    BANGALORE("KA-15");

    private final String code;

}
