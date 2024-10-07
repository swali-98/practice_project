package com.billing.project.service;

import com.billing.project.BillingprojectApplication;
import com.billing.project.entity.BIllingAccount;
import com.billing.project.repository.BillingRepository;
import com.patient.system.dtos.Datadto;
import com.patient.system.dtos.ResponseDto;
import com.patient.system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    public void createNewBillingAccount(Datadto datadto){
        BIllingAccount bIllingAccount=new BIllingAccount();
        bIllingAccount.setTransactions(BigDecimal.valueOf(0));
        bIllingAccount.setPatient(datadto.getPatient());
        billingRepository.save(bIllingAccount);
    }

    public ResponseDto deleteBillingAccount(Datadto datadto) {
        BIllingAccount bIllingAccount=billingRepository.findByPatientId(datadto.getPatient().getPatientId());
        ResponseDto responseDto=new ResponseDto();
        if((bIllingAccount.getTransactions().compareTo(BigDecimal.valueOf(0))<0)){
            responseDto.setStatus(false);
            return responseDto;
        }
        billingRepository.deleteById(datadto.getPatient().getPatientId());
        responseDto.setStatus(true);
        return responseDto;
    }

    public void updateBillingAccount(BigDecimal transaction) {

        billingRepository.update(transaction);
    }
}
