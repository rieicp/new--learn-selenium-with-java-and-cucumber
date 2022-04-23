package com.example.simplespringapp.xpath;

import com.example.simplespringapp.common.CommonUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.example.simplespringapp.common.CommonUtility.wait4PageLoad;
import static org.junit.jupiter.api.Assertions.*;

public class XpathTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = CommonUtility.init();
    }

    @Test
    public void xpathTest() {
        // come to url
        driver.get("https://www.facebook.com");
        // wait for page loading
        wait4PageLoad(driver);
        // find by linkText
        WebElement linkPwdElement = driver.findElement(By.linkText("Passwort vergessen?"));
        assertNotNull(linkPwdElement);
        // find by linkText
        WebElement linkSiteElement = driver.findElement(By.partialLinkText("Seite"));
        assertNotNull(linkSiteElement);
        // relative xpath
        WebElement emailElement = driver.findElement(By.xpath("//*[@id='email']"));
        assertNotNull(emailElement);
        // alternative
        emailElement = driver.findElement(By.xpath("//input[@id='email']"));
        assertNotNull(emailElement);
        // absolute xpath
        WebElement pwdElement = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[1]/div[2]/div/input"));
        assertNotNull(pwdElement);
        // multiple attributes AND
        WebElement aElement = driver.findElement(By.xpath("//a[@role='button' and @rel='async']"));
        assertNotNull(aElement);
        // multiple attributes OR
        List<WebElement> elements = driver.findElements(By.xpath("//input[@type='text' or @type='password']"));
        assertTrue(elements.size() > 1);
        // function text()
        WebElement dElement = driver.findElement(By.xpath("//*[text()='Deutsch']"));
        assertNotNull(dElement);
        // function contain(): id contains 'facebook'
        WebElement cElement = driver.findElement(By.xpath("//*[contains(@id, 'facebook')]"));
        assertNotNull(cElement);
        // function contain() + text(): text contains 'Facebook'
        WebElement fElement = driver.findElement(By.xpath("//*[contains(text(), 'Facebook')]"));
        assertNotNull(fElement);
        // function starts-with(): id starts with 'content'
        WebElement sElement = driver.findElement(By.xpath("//*[starts-with(@id, 'content')]"));
        assertNotNull(cElement);
        // following
        List<WebElement> followingInputPwdElements = driver.findElements(By.xpath("//input[@id='pass']//following::input"));
        assertTrue(followingInputPwdElements.size() >= 1);
        // certain index of following
        List<WebElement> followingInputPwdElement1 = driver.findElements(By.xpath("//input[@id='pass']//following::input[1]"));
        assertEquals(1, followingInputPwdElement1.size());
        // preceding
        List<WebElement> precedingInputPwdElements = driver.findElements(By.xpath("//input[@id='pass']//preceding::input"));
        assertTrue(precedingInputPwdElements.size() >= 1);
        // certain index of preceding
        List<WebElement> precedingInputPwdElement1 = driver.findElements(By.xpath("//input[@id='pass']//preceding::input[1]"));
        assertEquals(1, precedingInputPwdElement1.size());
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
