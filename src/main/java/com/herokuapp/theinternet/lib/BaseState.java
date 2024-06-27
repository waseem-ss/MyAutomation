package com.herokuapp.theinternet.lib;

import com.herokuapp.theinternet.pageobjects.HomePage;
import com.herokuapp.theinternet.pageobjects.LoginPage;
import com.herokuapp.theinternet.pageobjects.SecureAreaPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseState {

    protected Logger log ;

    protected WebDriver driver;
    protected HomePage homePage;
    protected String homePageUrl = "https://the-internet.herokuapp.com/";


    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    @Before
    public void setup(String browser,ITestContext ctx){
        log = LogManager.getLogger(ctx.getCurrentXmlTest().getName());
        log.info("Initializing the pre-setup");
        log.info("Initializing the Browser " + browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                //driver.get(url);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--window-size=maximized");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                driver = new FirefoxDriver(firefoxOptions);
                log.info("applying implicit wait");
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                driver.manage().window().maximize();
                break;

             default:
                log.info("no browser provided opting for FF");
                 FirefoxOptions firefoxOptions1 = new FirefoxOptions();
                 firefoxOptions1.addArguments("--window-size=maximized");
                 firefoxOptions1.setPageLoadStrategy(PageLoadStrategy.EAGER);
                 driver = new FirefoxDriver(firefoxOptions1);
                 break;
        }
        //opening the Home Page
        log.info("Opening the Homepage");
        homePage = new HomePage(driver,log);
        driver.get(homePageUrl);
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestContext ctx){
        log = LogManager.getLogger(ctx.getCurrentXmlTest().getName());
        log.info("Quit the browser");
        driver.quit();
    }
}
