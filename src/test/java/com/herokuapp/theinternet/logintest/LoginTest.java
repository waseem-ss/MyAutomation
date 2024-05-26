package com.herokuapp.theinternet.logintest;

import com.herokuapp.theinternet.lib.BaseState;
import com.herokuapp.theinternet.lib.Util;
import com.herokuapp.theinternet.pageobjects.HomePage;
import com.herokuapp.theinternet.pageobjects.LoginPage;
import com.herokuapp.theinternet.pageobjects.SecureAreaPage;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Base64;

import static java.lang.Thread.sleep;

public class LoginTest extends BaseState {

    protected String loginUsername = "tomsmith";
    protected String password = Util.password();

    @Parameters({"browser"})
    @Test(priority = 1)
    public void positiveLoginTest(@Optional("Firefox") String browser) {
        log.info("This is a login test");
        //HomePage homePage = new HomePage(driver,log);
        LoginPage loginPage = new LoginPage(driver,log);
        homePage.navigateToLoginPage();
        //LoginPage loginPage = new LoginPage(driver, log);
        Assert.assertTrue(loginPage.isPageLoaded(),"Login page loaded successfully");
        loginPage.login(loginUsername,password);
        Util.sleep(2);
        SecureAreaPage secureAreaPage = new SecureAreaPage(driver,log);
        Assert.assertTrue(secureAreaPage.isPageLoaded(),"Login successful");
        Util.sleep(2);
    }
    @Parameters({"browser"})
    @Test(dependsOnMethods = { "positiveLoginTest" })
    public void logoutTest(@Optional("Chrome") String browser){
        log.info("This is a logout test");
        homePage.navigateToLoginPage();
        LoginPage loginPage = new LoginPage(driver, log);
        Assert.assertTrue(loginPage.isPageLoaded(),"Login page loaded successfully");
        loginPage.login(loginUsername,password);
        SecureAreaPage secureAreaPage = new SecureAreaPage(driver,log);
        Assert.assertTrue(secureAreaPage.isPageLoaded(),"Login successful");
        Util.sleep(2);
        secureAreaPage.logout();
        Util.sleep(2);
        Assert.assertTrue(loginPage.isPageLoaded(),"Logout successful");
    }
}
