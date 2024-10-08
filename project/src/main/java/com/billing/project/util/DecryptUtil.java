package com.billing.project.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class DecryptUtil {
    public static String decrypt(String data, SecretKey secretKey)throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
