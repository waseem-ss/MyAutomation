package com.aplitools.kitchen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tables {


    @Test(priority = 1)
    public void testTables() {
        try {
            System.out.println("Practicing the Table");
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
            driver.get("https://kitchen.applitools.com/");
            Thread.sleep(Duration.ofMillis(2000));
            WebElement tableLink = driver.findElement(By.linkText("Table"));
            tableLink.click();
            Thread.sleep(Duration.ofSeconds(2));
            WebElement table = driver.findElement(By.id("fruits-vegetables"));
            System.out.println(table.isDisplayed());
            Assert.assertTrue(table.isDisplayed(),
                    "The table is not displayed");
            //Print the Tables Contents
            List<WebElement> tableHeaders = driver.findElements(By.xpath("//table[@id=\"fruits-vegetables\"]//th"));
            int numCols = tableHeaders.size();
            System.out.println("Num of Col " + numCols);
            List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id=\"fruits-vegetables\"]//tbody//tr"));
            int rowCount = tableRows.size();
            System.out.println("Rows cnt: " + rowCount);
            List<String> colNames = new ArrayList<>();
            for(WebElement header:tableHeaders){
                System.out.println("Column " + header.getText());
                colNames.add(header.getText());
            }
            //Row 1 is header
            System.out.println(colNames);
            for(int i = 1 ; i <= rowCount; i ++){
                WebElement row = driver.
                        findElement(By.xpath("//table[@id=\"fruits-vegetables\"]//tbody//tr["+i+"]"));
                System.out.println("Row : " + i +" " + row.getText());
                /*for (int j = 1 ;j <= numCols; j ++){
                    String cell = driver.
                            findElement(By.xpath("//table[@id=\"fruits-vegetables\"]//tbody//tr[" + i + "]//td["+ j + "]")).getText();
                    System.out.println("Cell value for "+ j + cell );
                }*/
            }
            driver.quit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getStackTrace());
        }
    }
}
