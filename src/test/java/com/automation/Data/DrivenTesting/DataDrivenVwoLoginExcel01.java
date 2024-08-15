package com.automation.Data.DrivenTesting;

import com.automation.utils.ExcelReader;
import com.automation.utils.ExcelReader01;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DataDrivenVwoLoginExcel01 {
    private WebDriver driver;
    private static final String BASE_URL = "https://app.vwo.com";
    private static final String EXPECTED_USERNAME = "karamjeet kaur";
    private static final String EXPECTED_ERROR_MESSAGE = "Your email, password, IP address or location did not match";
    private static final String EXCEL_FILE_PATH = "src/test/resources/VwoLoginData.xlsx";

    @BeforeClass
            public void SetUpDriver(){
        WebDriver driver = new EdgeDriver();
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--guest");
        driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
        //driver.get(BASE_URL);
    }


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
            Assert.assertEquals(text, EXPECTED_USERNAME);
        }
        if (expectedResult.equalsIgnoreCase("Invalid")) {
            WebElement errorMessage = driver.findElement(By.id("js-notification-box-msg"));
            String actualResult = errorMessage.getText();
            //driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(1000));
            webDriverWait.until(ExpectedConditions.visibilityOf(errorMessage));
            Assert.assertEquals(actualResult, EXPECTED_ERROR_MESSAGE);
        }


    }

    @DataProvider(name = "loginData")
    public Object[][] testDataExcel() throws IOException {
        String testDataFile = EXCEL_FILE_PATH;
        ExcelReader01 excelReader = new ExcelReader01();
        return excelReader.readExcel(testDataFile, "Sheet1");
    }

    @AfterClass
    public  void close(){
        driver.quit();
    }

}
