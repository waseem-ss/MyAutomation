package com.herokuapp.theinternet.lib;

import com.herokuapp.theinternet.pageobjects.LoginPage;
import com.herokuapp.theinternet.pageobjects.SecureAreaPage;

import java.util.Base64;

public class Util {

    public static void sleep(Integer sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static String password(){
        //U3VwZXJTZWNyZXRQYXNzd29yZCE=
        //String originalInput = "SuperSecretPassword!";
        String encodedString = "U3VwZXJTZWNyZXRQYXNzd29yZCE=";//Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println("encodedString: " + encodedString);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println("decodedString: " + decodedString);
        return decodedString;
    }



}
