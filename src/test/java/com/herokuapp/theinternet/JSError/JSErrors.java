package com.herokuapp.theinternet.JSError;

import com.herokuapp.theinternet.lib.BaseState;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class JSErrors extends BaseState {

    @Test
    public void JSErrTest(){
        homePage.navigateTo("JavaScript onload event error");
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogs = logs.getAll();
        for (LogEntry log:logs){
            System.out.println("Level: " + log.getLevel());
            System.out.println("Message: " + log.getMessage() );
        }
    }
}
