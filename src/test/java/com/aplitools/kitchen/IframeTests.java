package com.aplitools.kitchen;

import com.herokuapp.theinternet.lib.KitchenBaseState;
import com.herokuapp.theinternet.lib.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class IframeTests extends KitchenBaseState {

    @Test
    public void iframeTest() {

        driver.findElement(By.linkText("iFrame")).click();
        Util.sleep(1);
        Assert.assertTrue(driver.getCurrentUrl().equals("https://kitchen.applitools.com/ingredients/iframe"),
                "Navigation failed to iFrames page");
        log.info("Switching to the iFrame: the-kitchen-table");
        //driver.switchTo().frame("the-kitchen-table");
        Util.sleep(1);
        //System.out.println("The table in the Frame is found displayed " + driver.
               //findElement(By.id("fruits-vegetables")).isDisplayed());

        Util.sleep(1);
        Assert.assertTrue(driver.switchTo().frame("the-kitchen-table").
                        findElement(By.id("fruits-vegetables")).isDisplayed(),
                "The table is not displayed");
        log.info(driver.findElement(By.id("fruits-vegetables")).isDisplayed());
       Assert.assertTrue(driver.findElement(By.id("fruits-vegetables")).isDisplayed(),
               "Table is not displayed");//*[@id="fruits-vegetables"]

        driver.switchTo().defaultContent();

        log.info("Switching to the iFrame: youtube-table-cypress");
        Util.sleep(1);
        Assert.assertTrue(driver.switchTo().frame("youtube-table-cypress").
                        findElement(By.id("player")).isDisplayed(),
                "The Youtube played in not displayed ");
        log.info(driver.findElement(By.id("movie_player")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("movie_player")).isDisplayed(),
                "The Movie Player is not displayed");
        driver.switchTo().defaultContent();
        driver.quit();
    }


}
