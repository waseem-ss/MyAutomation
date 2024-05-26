package com.herokuapp.theinternet.logintest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.DriverManager;

public class SwitchTabs {

    public void switchTabs(){
        String url = "https://kitchen.applitools.com/";
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.quit();
    }

    public void main(String [] args){
        switchTabs();
    }
}
