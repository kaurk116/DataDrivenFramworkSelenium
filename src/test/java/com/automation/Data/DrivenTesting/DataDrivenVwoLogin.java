package com.automation.Data.DrivenTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DataDrivenVwoLogin {
    WebDriver driver;
    @BeforeClass
            public void SetUpDriver(){
         driver = new EdgeDriver();
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--guest");
        driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
    }

@Test(dataProvider = "loginData")
    public void testDataDriven(String email, String password, String expectedResult) {
        driver.get("https://app.vwo.com");
        driver.manage().timeouts().pageLoadTimeout(3000,TimeUnit.SECONDS);
        WebElement Elementemail = driver.findElement(By.id("login-username"));
        Elementemail.sendKeys(email);
        WebElement Elementpassword = driver.findElement(By.id("login-password"));
        Elementpassword.sendKeys(password);

        driver.findElement(By.id("js-login-btn")).click();

        driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);

        if (expectedResult.equalsIgnoreCase("Valid")) {
            driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
            String Actualtext = driver.findElement(By.xpath("//span[@data-qa=\"lufexuloga\"]")).getText();
                    String expectedText="karamjeet Kaur";
            Assert.assertEquals(Actualtext,expectedText);
        }
       /* if (expectedResult.equalsIgnoreCase("Invalid")) {
           // driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(1000));
            webDriverWait.until(ExpectedConditions.textToBe(By.id("js-notification-box-msg"),"Your email, password, IP address or location did not match"));
            String actualResult = driver.findElement(By.id("js-notification-box-msg")).getText();
            String expectedResult1 ="Your email, password, IP address or location did not match";
            driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
            Assert.assertEquals(actualResult, expectedResult1);
        }*/

    }
@DataProvider(name ="loginData")
    public Object[] tesData() {
       return new Object[][]{
                {"kaurk161@gmail.com", "karamjeet1990", "Valid"},
                //{"admin@admin.com", "1234", "Invalid"},

        };

    }
    @AfterClass
    public  void close(){
        driver.quit();
    }

}