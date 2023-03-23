package com.wipro.asg.vehicleregistration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationUserAuthority {

    APPROVE_REQUEST("approve_request"),
    DECLINE_REQUEST("decline_request"),
    SEARCH_REQUEST("search_request"),
    CREATE_REQUEST("create_request"),
    VIEW_REQUEST("view_request");

    private final String permissions;
}
