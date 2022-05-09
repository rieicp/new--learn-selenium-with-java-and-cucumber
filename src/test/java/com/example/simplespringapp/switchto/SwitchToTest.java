package com.example.simplespringapp.switchto;

import com.example.simplespringapp.common.CommonUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class SwitchToTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = CommonUtility.init();
    }

    @Test
    public void switchAlertPopupTest() {
        // implicit wait
        driver.get("https://www.facebook.com");
        assertTrue(driver.getTitle().length() > 0);

        // 模仿alert-popup
        ((JavascriptExecutor) driver).executeScript("alert('OK?')");
        // switch到alert并确认
        driver.switchTo().alert().accept();
//        driver.switchTo().alert().dismiss(); // cancel
    }

    @Test
    public void switchWindows() {
        driver.get("http://demo.automationtesting.in/Windows.html");

        //String handleValue=driver.getWindowHandle();
        //System.out.println(handleValue); //CDwindow-45FEE686DED3D844347AB3AC2E7F5E41

        driver.findElement(By.xpath("//*[@id=\"Tabbed\"]/a/button")).click();

        Set<String> handlevalues = driver.getWindowHandles();
        for(String h:handlevalues)
        {
            //System.out.println(h);
            String title = driver.switchTo().window(h).getTitle();
            //System.out.println(title);
            if(title.equals("Selenium") ) {
                WebElement about = driver.findElement(By.partialLinkText("About"));//about menu
                assertNotNull(about);
            }
        }
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
