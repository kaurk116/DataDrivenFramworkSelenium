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

    @BeforeClass
            public void SetUpDriver(){
        WebDriver driver = new EdgeDriver();
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--guest");
        driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
    }
    WebDriver driver;

@Test(dataProvider = "loginData")
    public void testDataDriven(String email, String password, String expectedResult) {
        driver.get("https://app.vwo.com");
        WebElement Elementemail = driver.findElement(By.id("login-username"));
        Elementemail.sendKeys(email);
        WebElement Elementpassword = driver.findElement(By.id("login-password"));
        Elementpassword.sendKeys(password);

        driver.findElement(By.id("js-login-btn")).click();

        driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);

        if (expectedResult.equalsIgnoreCase("Valid")) {
            String text = driver.findElement(By.cssSelector("data-qa=\"lufexuloga")).getText();
            Assert.assertEquals(text, "karamjeet kaur");
        }
        if (expectedResult.equalsIgnoreCase("Invalid")) {
            WebElement errorMessage = driver.findElement(By.id("js-notification-box-msg"));
            String actualResult = errorMessage.getText();
            //driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(1000));
            webDriverWait.until(ExpectedConditions.visibilityOf(errorMessage));
            Assert.assertEquals(actualResult, "dfefefe");
        }


    }
@DataProvider(name ="loginData")
    public Object[] tesData() {
       return new Object[][]{
                {"admin@admin.com", "1234", "Invalid"},
                {"admin@admin.com", "1234", "Invalid"},

        };

    }
    @AfterClass
    public  void close(){
        driver.quit();
    }

}