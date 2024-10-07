package com.billing.project.controller;

import com.billing.project.dto.PatientDto;
import com.billing.project.service.BillingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.system.dtos.Datadto;
import com.patient.system.dtos.ResponseDto;
import com.patient.system.entity.Patient;
import com.patient.system.util.EncryptionUtil;
import com.patient.system.util.SecretKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.math.BigDecimal;
import java.util.Map;

@RestController
public class BillingController {

    @Autowired
    private BillingService billingService;


    @PostMapping("/webhook")
    public ResponseEntity<ResponseDto> entryPoint(@RequestBody Map<String,String> request){

        ObjectMapper objectMapper=new ObjectMapper();
        try {
            String passPhrase="myPassPhrase";
            String encryptedData=request.get("encryptData");
            String salt=request.get("salt");
            SecretKey secretKey= SecretKeyUtil.getKeyFromPassphrase(passPhrase,salt);
            String decryptedData= EncryptionUtil.decrypt(encryptedData,secretKey);
            Datadto datadto=objectMapper.readValue(decryptedData, Datadto.class);
            if(datadto.getEvent().equals("create")){
               billingService.createNewBillingAccount(datadto);
            }
            else if(datadto.getEvent().equals("delete")){
              return new ResponseEntity<>(billingService.deleteBillingAccount(datadto), HttpStatus.OK);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/billing")
    public ResponseEntity<?> updateBillingAccount(@RequestBody BigDecimal transaction){
        billingService.updateBillingAccount(transaction);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
