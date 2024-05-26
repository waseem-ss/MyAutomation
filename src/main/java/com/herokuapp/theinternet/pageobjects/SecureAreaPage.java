package com.herokuapp.theinternet.pageobjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecureAreaPage {
    protected String pageUrl = "https://the-internet.herokuapp.com/secure";
    //protected String loginPageUrl = "https://the-internet.herokuapp.com/login"
    protected By logoutLocator = By.linkText("Logout");
    WebDriver driver;
    Logger log;

    public SecureAreaPage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    public Boolean isPageLoaded (){
        log.info("check the page loaded");
        return driver.getCurrentUrl().equals(this.pageUrl);
    }

    public LoginPage logout() {
        log.info("logging out");
        driver.findElement(logoutLocator).click();
        return new LoginPage(driver,log);
    }
    public Boolean isLogoutSuccessful(){
        LoginPage loginPage =new LoginPage(driver,log);
        return driver.getCurrentUrl().equals(loginPage.pageUrl);
    }
}
