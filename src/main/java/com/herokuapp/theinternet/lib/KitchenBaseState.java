package com.herokuapp.theinternet.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;


public class KitchenBaseState {
    protected WebDriver driver ;
    protected Logger log;
    protected String home = "https://kitchen.applitools.com/";

    @BeforeMethod(alwaysRun = true)
    public void setup(ITestContext ctx){
        log = LogManager.getLogger(ctx.getCurrentXmlTest().getName());
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(home);


    }
}
