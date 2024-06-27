package com.herokuapp.theinternet.tables;

import com.herokuapp.theinternet.lib.BaseState;
import org.testng.annotations.Test;

public class TestTables extends BaseState {
    @Test
    public void testTables (){
        homePage.navigateTo("Sortable Data Tables");

    }
}
