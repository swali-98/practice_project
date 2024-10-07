package com.patient.system.service;

import com.patient.system.dtos.ResponseDto;
import com.patient.system.util.EncryptionUtil;
import com.patient.system.util.SecretKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebHookService {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public ResponseDto executeWebhook(String payLoadData){


        try {
            String passPhrase="myPassPhrase";
            String salt= SecretKeyUtil.generateSalt();
            SecretKey secretKey= SecretKeyUtil.getKeyFromPassphrase(passPhrase,salt);
            String encryptedData= EncryptionUtil.encrypt(payLoadData,secretKey);
            Map<String,String> requestBody=new HashMap<>();
            requestBody.put("encryptData",encryptedData);
            requestBody.put("salt",salt);
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String,String>> request=new HttpEntity<>(requestBody,headers);
            ResponseDto responseDto= restTemplate.postForObject("http://localhost:9090/webhook", request, ResponseDto.class);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
