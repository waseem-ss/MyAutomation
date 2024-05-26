package com.herokuapp.theinternet.pageobjects;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class LoginPage  {
    protected By usernameInputLocator = By.xpath("//*[@id='username']");
    protected By passwordInputLocator = By.xpath("//*[@id='password']");
    protected By loginButtonLocator = By.xpath("//*[@id='login']/button");
    protected String pageUrl = "https://the-internet.herokuapp.com/login";

    WebDriver driver;
    Logger log;

    public LoginPage (WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
    }

    public void enterUsername(String username){
        log.info("Entering User name "+ username);
        driver.findElement(usernameInputLocator).sendKeys(username);
    }
    public void enterPassword(String password){
        log.info("Entering Password");
        driver.findElement(passwordInputLocator).sendKeys(password);
    }
    public void clickLogin(){
        log.info("Clicking Login");
        driver.findElement(loginButtonLocator).click();
    }

    public Boolean isPageLoaded(){
        String actualURL = driver.getCurrentUrl().toLowerCase();
        return actualURL.equals(pageUrl);
    }
    public Boolean isLoginSuccessful(){
        SecureAreaPage secureAreaPage = new SecureAreaPage(driver,log);
        return secureAreaPage.isPageLoaded();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
