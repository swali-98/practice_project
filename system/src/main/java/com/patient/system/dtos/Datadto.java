package com.patient.system.dtos;

import com.patient.system.entity.Patient;
import lombok.Data;

@Data
public class Datadto {

    private String event;

    private Patient patient;
}
