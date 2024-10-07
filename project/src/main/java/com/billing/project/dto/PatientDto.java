package com.billing.project.dto;

import com.patient.system.entity.Address;
import com.patient.system.entity.Insurance;

import java.util.HashSet;
import java.util.Set;

public class PatientDto {

    private Long PatientId;

    private String firstName;

    private String lastName;

    private String email;
    private String contactNo;

    private Address address;

    private Set<Insurance> insurances=new HashSet<>();
}
