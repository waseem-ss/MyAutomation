package com.aplitools.kitchen;

import com.herokuapp.theinternet.lib.KitchenBaseState;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.DriverManager;
import java.util.Set;

public class SwitchTabs extends KitchenBaseState {

    @Test
    public void switchTabs(){
        SoftAssert softAssert =new SoftAssert();

        try {
            WebElement links = driver.findElement(By.linkText("Links"));
            links.click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("The Kitchen - Table")).click();
            driver.findElement(By.linkText("Eater.com")).click();

            Set<String> tabs = driver.getWindowHandles();//Get handle to all the tabs
            System.out.println("tabs :" + tabs);
            for(String tab:tabs){
                System.out.println("Switching to tab : " + tab);
                driver.switchTo().window(tab);
                Thread.sleep(2000);
                if (driver.
                        getCurrentUrl().equals("https://kitchen.applitools.com/ingredients/table"))
                    softAssert.assertTrue(driver.
                                    findElement(By.id("fruits-vegetables")).isDisplayed(),
                        "The element is not displayed");
                if (driver.getCurrentUrl().equals("https://www.eater.com"))
                    softAssert.assertTrue(driver.getCurrentUrl().equals("https://www.eater.com"),
                            "The navigation to the eater.com failed");
                Thread.sleep(2000);
                //driver.switchTo().window("tabs[0]");
                //"//*[@id=\"fruits-vegetables\"]"
            }
        }catch (Exception e) {
            e.getStackTrace();
        }
        softAssert.assertAll();
        System.out.println("Closing the browser");
        driver.quit();
    }
}
