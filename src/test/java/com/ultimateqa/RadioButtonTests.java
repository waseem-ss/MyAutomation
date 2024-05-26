package com.ultimateqa;

import com.herokuapp.theinternet.lib.Util;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v121.overlay.model.LineStyle;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class RadioButtonTests {
    protected String url = "https://ultimateqa.com/simple-html-elements-for-automation/";

   protected WebDriver driver ;

   @BeforeMethod(alwaysRun = true)
   public void setup (){
       FirefoxOptions firefoxOptions = new FirefoxOptions();
       firefoxOptions.addArguments("--window-size=maximized");
       firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
       driver = new FirefoxDriver(firefoxOptions);
       driver.manage().window().maximize();
       driver.get(url);
   }

   @AfterMethod(alwaysRun = true)
   public void tearDown(){
       driver.quit();
   }

   @Parameters({"value"})
   @Test
   public void radioButtonTest(String value){
       List<WebElement> radioList = driver.findElements(By.xpath("//input[@type='radio']"));
       for(WebElement radio: radioList ){
           System.out.println("Is selected " + radio.isSelected());
           if(!radio.isSelected() && radio.getAttribute("value").equals(value))
           {
               System.out.println("Selecting radio: " + radio.getAttribute("value"));
               radio.click();
               Util.sleep(5);

           }
       }
   }

}
