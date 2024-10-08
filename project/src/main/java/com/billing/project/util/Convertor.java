package com.billing.project.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.system.dtos.Datadto;
import com.patient.system.util.SecretKeyUtil;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.util.Map;

public class Convertor {

    public static Datadto convert(Map<String,String> encryptedBody) throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        String passPhrase="myPassPhrase";
        String encryptedData=encryptedBody.get("encryptData");
        String salt=encryptedBody.get("salt");
        SecretKey secretKey= SecretKeyUtil.getKeyFromPassphrase(passPhrase,salt);
        String decryptedData= DecryptUtil.decrypt(encryptedData,secretKey);
        Datadto datadto=objectMapper.readValue(decryptedData,Datadto.class);

        return datadto;
    }
}
