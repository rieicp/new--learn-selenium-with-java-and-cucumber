package com.example.simplespringapp.common;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtility {
    private static final String CHROMEDRIVER_BIN = "C:\\Users\\nwe\\test\\java\\learn-selenium-with-java-and-cucumber\\chromedriver.exe";
    public static ChromeDriver init() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_BIN);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        // ChromeDriverService service = new ChromeDriverService.Builder()
        //      .usingDriverExecutable(new File(CHROMEDRIVER_BIN))
        //      .build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
//        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--headless");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // disable "chrome is controlled by test software"
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.merge(capabilities);
        return new ChromeDriver(/*service, */options);
    }

    public static void wait4PageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
