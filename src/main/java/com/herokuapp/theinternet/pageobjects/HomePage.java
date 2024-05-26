package com.herokuapp.theinternet.pageobjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class HomePage  {

    protected WebDriver driver;
    protected Logger log;
    protected String homePage = "https://the-internet.herokuapp.com/";
    protected String loginPageLinkText = "Form Authentication";
    protected String checkboxesLinktext = "Checkboxes";
    protected String dropDowmLinkText = "Dropdown";
    protected String framesLinkText = "Frames";
    protected String infiniteScroll = "Infinite Scroll";







    public HomePage(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
        this.log.info("Opening the Home Page");
        this.driver.get(homePage);
    }
    public void navigateTo(String linkText){
        log.info("Clicking on "+ linkText);
        driver.findElement(By.linkText(linkText)).click();
    }
    public void navigateToLoginPage(){
        log.info("Navigating to Login Page");
        navigateTo(loginPageLinkText);
    }
    public void navigateCheckBoxes(){
        log.info("Navigating to Checkboxes");
        navigateTo(checkboxesLinktext);
    }
    public void navigateDropDown(){
        log.info("Navigating to DropDown");
        navigateTo(dropDowmLinkText);
    }

}
