package com.herokuapp.theinternet.controls;

import com.herokuapp.theinternet.lib.BaseState;
import com.herokuapp.theinternet.lib.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Browser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DragAndDrop extends BaseState {
    @Test
    public void dragAndDrop(){

        homePage.navigateTo("Drag and Drop");
        WebElement dragAndDropTitle = driver.findElement(By.xpath("//*[@id='content']/div/h3"));
        Assert.assertEquals(dragAndDropTitle.getText(),
                "Drag and Drop","Navigated to Drag and Drop  page failed");

        WebElement colA = driver.findElement(By.id("column-a"));
        WebElement colB = driver.findElement(By.id("column-b"));
        Actions actions = new Actions(driver);
        System.out.println("Before moving : " + colA.getText() + ","+ colB.getText());
        Util.sleep(2);
        //actions.dragAndDrop(colA,colB).build().perform();
        //Get this from stack overflow
        //https://stackoverflow.com/questions/39436870/why-drag-and-drop-is-not-working-in-selenium-webdriver
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                + "var dropEvent = createEvent('drop');\n"
                + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                + "var dragEndEvent = createEvent('dragend');\n"
                + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                + "simulateHTML5DragAndDrop(source,destination);",
                colA, colB);
        Util.sleep(2);
        System.out.println("After moving : " + colA.getText() + ","+ colB.getText());
    }
    @Test
    public void dragAndDrop1() throws InterruptedException {
        WebDriver driver1 = new FirefoxDriver();
        driver1.manage().window().maximize();
        //driver1.manage().wait(2000);
        driver1.get("https://jqueryui.com/droppable/");
        log.info("test https://jqueryui.com/droppable/ ");
        WebElement frame = driver1.findElement(By.className("demo-frame"));
        driver1.switchTo().frame(frame);
        Util.sleep(1);
        WebElement dragger = driver1.findElement(By.id("draggable"));
        WebElement droppable = driver1.findElement(By.id("droppable"));
        Actions dragAndDrop = new Actions(driver1);
        Util.sleep(2);
        dragAndDrop.dragAndDrop(dragger,droppable).build().perform();
        log.info("Dropped: " + droppable.getText());
        driver1.switchTo().defaultContent();
        //driver1.findElement(By.linkText("Droppable")).click();
        Util.sleep(1);
        driver1.get("https://jqueryui.com/draggable/");
        frame = driver1.findElement(By.className("demo-frame"));
        driver1.switchTo().frame(frame);
        WebElement draggable = driver1.findElement(By.id("draggable"));
        Util.sleep(1);
        //System.out.println("draggable: " + draggable.isDisplayed());
        Util.sleep(1);
        dragAndDrop.dragAndDropBy(draggable,50,50).build().perform();
        driver1.switchTo().defaultContent();
        driver1.quit();
        driver.quit();
    }
}
