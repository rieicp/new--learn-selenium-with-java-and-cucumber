package com.example.simplespringapp.wait;

import com.example.simplespringapp.common.CommonUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class WaitTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = CommonUtility.init();
    }

    @Test
    public void test() {
        // implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com");
        assertTrue(driver.getTitle().length() > 0);
        // explicit wait
        WebDriverWait mywait = new WebDriverWait(driver,10000);
        WebElement email = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        assertNotNull(email);
        // fluent wait
        Wait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(2000))
                .ignoring(NoSuchElementException.class);
//        WebElement password = (WebElement) fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")));
        // 优雅：使用 Function<>()接口, WebDriver.findElement()方法
        WebElement password= (WebElement) fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("pass"));
            }
        });
        assertNotNull(password);

        List<WebElement> links=driver.findElements(By.tagName("a"));
        assertTrue(links.size() >= 1);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
