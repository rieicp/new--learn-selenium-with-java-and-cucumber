package com.example.simplespringapp.loggin;

import com.example.simplespringapp.common.CommonUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.example.simplespringapp.common.CommonUtility.wait4PageLoad;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = CommonUtility.init();
    }

    @Test
    public void loginSonarQube(){
        // come to url
        driver.get("http://localhost:9001");
        // wait for page loading
        wait4PageLoad(driver);
        // find username input
        WebElement usernameElement = driver.findElement(By.name("login"));
        assertNotNull(usernameElement);
        // enter username
        usernameElement.sendKeys("admin");
        // find pwd input
        WebElement pwdElement = driver.findElement(By.id("password"));
        assertNotNull(pwdElement);
        // enter pwd
        pwdElement.sendKeys("123456");
        // find login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        assertNotNull(pwdElement);
        // click button to login
        loginButton.click();
        // wait for page loading
        wait4PageLoad(driver);
        // assert title
        assertEquals("SonarQube", driver.getTitle());
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
