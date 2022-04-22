package com.example.simplespringapp;

import com.example.simplespringapp.common.CommonUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SimpleSpringAppApplicationTests {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = CommonUtility.init();
    }

    @Test
    public void getSearchPage() {
        this.driver.get("https://www.google.com");
        WebElement element = this.driver.findElement(By.name("q"));
        assertNotNull(element);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
