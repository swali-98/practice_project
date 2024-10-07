package com.patient.system.entity;

import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class Patient {

    private Long PatientId;

    private String firstName;

    private String lastName;

    private String email;
    private String contactNo;

    private Address address;

    private Set<Insurance> insurances=new HashSet<>();
}
