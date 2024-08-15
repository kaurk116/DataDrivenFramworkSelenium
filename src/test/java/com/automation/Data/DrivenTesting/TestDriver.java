package com.automation.Data.DrivenTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class TestDriver {
    @Test
    public void driver(){
        WebDriver driver =new EdgeDriver();
        driver.get("https://google.com");
        driver.quit();
    }
}
