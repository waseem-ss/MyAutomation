package com.herokuapp.theinternet.controls;

import com.herokuapp.theinternet.lib.BaseState;
import com.herokuapp.theinternet.lib.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestControls extends BaseState {
    @Test
    public void checkBoxTest() {
        homePage.navigateCheckBoxes();
        log.info("Clicking the CheckBoxes");
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        log.info("No. of CheckBoxes: " + checkBoxes.size());

        log.info("Un Checking all the CheckBoxes");
        for (WebElement checkbox : checkBoxes)
            if (checkbox.isSelected()) checkbox.click();
        log.info("Verify all the CheckBoxes are Un checked");
        Boolean allUnChecked = true;
        for (WebElement checkbox : checkBoxes)
            if (checkbox.isSelected()) allUnChecked = false;
        Assert.assertTrue(allUnChecked, "Failed all uncheck test");
        Util.sleep(2);

        log.info("Checking all the CheckBoxes");
        for (WebElement checkbox : checkBoxes)
            if (!checkbox.isSelected()) checkbox.click();
        log.info("Verify all the CheckBoxes are checked");
        Boolean allChecked = true;
        for (WebElement checkbox : checkBoxes)
            if (!checkbox.isSelected()) allChecked = false;
        Assert.assertTrue(allChecked, "Failed all check test");
        Util.sleep(2);
    }

    @Test
    public void dropDownTest() {
        homePage.navigateDropDown();
        Select dropDown = new Select(driver.findElement(By.xpath("//select[@id='dropdown']")));
        //log.info("AllOptions : " + dropDown.getOptions());
        log.info(" ---Printing all options--- " );
        List<WebElement> allOptions = dropDown.getOptions();
        for(WebElement option: allOptions)
            log.info("Options : "+ option.getText());
        dropDown.selectByValue("2");
        Util.sleep(2);
        log.info("Selected value: " + dropDown.getFirstSelectedOption().getText());
        dropDown.selectByValue("1");
        Util.sleep(2);
        log.info("Selected value: " + dropDown.getFirstSelectedOption().getText());
    }

    @Test
    public void iFramesTest(){
        homePage.navigateTo("Frames");
        Assert.assertTrue(driver.findElement(By.linkText("iFrame")).isDisplayed(),
                "Navigation to iFrames page failed");


    }
    @Test
    public void scrollJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    @Test
    public void hover(){
        homePage.navigateTo("Hovers");
        List<WebElement> images = driver.findElements(By.className("figure"));
        int size = images.size();
        String userLocator = "/users/";//"//a[@href=\"/users/1\"]";
        for (int i=0 ; i< size; i++){
            images = driver.findElements(By.className("figure"));
            Actions hover = new Actions(driver);
            int cnt = i + 1;
            log.info("Hover on the Image for User :" + cnt);
            String xpath = "//a[@href=" + "\"" + userLocator + cnt + "\"" + "]";
            log.info("Xpath: " + xpath);
            hover.moveToElement(images.get(i)).build().perform();
            Util.sleep(1);
            driver.findElement(By.xpath(xpath)).click();
            log.info("Page Url: " + driver.getCurrentUrl());
            Util.sleep(1);
            driver.get("https://the-internet.herokuapp.com/hovers");
            Util.sleep(2);
            log.info("Page Url: " + driver.getCurrentUrl());
        }
    }
    @Test
    public void slider (){
        homePage.navigateTo("Horizontal Slider");
        WebElement header = driver.findElement(By.xpath("//div[@class=\"example\"]/h3"));
        log.info(header.getText());
        WebElement slider = driver.findElement(By.xpath("//input[@type=\"range\"]"));
        log.info(slider.isDisplayed());
        //operating the slider
        /*Actions slide = new Actions(driver);
        log.info("width X:"+slider.getSize().getWidth() + "and Height Y:" + slider.getSize().getHeight());
        log.info("x-offset:" + (int)(slider.getSize().getWidth()*0.005));
        slide.dragAndDropBy(slider,1,0).build().perform();
        Util.sleep(3);*/
        WebElement sliderValue = driver.findElement(By.id("range"));
        log.info(sliderValue.getText());
        String value = "3.5";
        for (int i=1; i<=10;i++) {
            slider.sendKeys(Keys.ENTER);
            slider.sendKeys(Keys.ARROW_RIGHT);
            log.info("value: "+ sliderValue.getText());
            if (sliderValue.getText().equals(value)) break;
            Util.sleep(2);
        }
    }

    @Test
    public void enterKeys(){
        homePage.navigateTo("Key Presses");
        System.out.println("Test the key press");
        Actions pressKeys = new Actions(driver);
        pressKeys.sendKeys(Keys.ENTER).build().perform();
        log.info(driver.findElement(By.id("result")).getText());
        pressKeys.sendKeys("A").build().perform();
        log.info(driver.findElement(By.id("result")).getText());
    }
}
