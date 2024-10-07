package com.billing.project.entity;

import com.patient.system.entity.Patient;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class BIllingAccount {

    private Long accountNo;

    private BigDecimal transactions;

    private Patient patient;
}
