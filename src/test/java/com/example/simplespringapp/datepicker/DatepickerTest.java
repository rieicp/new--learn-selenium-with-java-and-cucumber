package com.example.simplespringapp.datepicker;

import com.example.simplespringapp.common.CommonUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatepickerTest {
    private WebDriver driver;
    private static final HashMap<Integer, String> monthNameMapping = mappingMonthName();

    @BeforeEach
    public void setup() {
        driver = CommonUtility.init();
    }

    @Test
    public void getSearchPage() {
        this.driver.get("https://jqueryui.com/datepicker");
        CommonUtility.wait4PageLoad(driver);
        // locate the frame containing the datepicker
        WebElement iframe = driver.findElement(By.xpath("//*[@id='content']/iframe[1]"));
        assertNotNull(iframe);
        assertTrue(iframe.getAttribute("src").contains("/resources/demos/datepicker/default.html"));
        // switch to the frame
        driver.switchTo().frame(iframe);
        // locate the datepicker input within the frame
        WebElement datepickerInput = driver.findElement(By.xpath("//input[@id='datepicker']"));
        assertNotNull(datepickerInput);

        //String year="2018";
        Calendar cal = Calendar.getInstance();
        int currentYearInt = cal.get(Calendar.YEAR);
        int currentMonthInt = cal.get(Calendar.MONTH) + 1;
        String pastMonth = (currentMonthInt > 1) ? monthNameMapping.get(currentMonthInt - 1) : monthNameMapping.get(12);
        String futureMonth = (currentMonthInt < 12) ? monthNameMapping.get(currentMonthInt + 1) : monthNameMapping.get(1);
        String date = "17";

        // click the datepicker input
        datepickerInput.click();
//        selectPastDate(date, pastMonth);
//        if (currentMonthInt > 1) {
//            assertEquals(String.format("%s/%s/%d", mappingMonthInt(pastMonth), date, currentYearInt), datepickerInput.getAttribute("value"));
//        } else {
//            assertEquals(String.format("%s/%s/%d", mappingMonthInt(pastMonth), date, currentYearInt - 1), datepickerInput.getAttribute("value"));
//        }
//        selectCurrentDate();
        selectFutureDate(date, futureMonth);
        if (currentMonthInt < 12) {
            assertEquals(String.format("%s/%s/%d", mappingMonthInt(futureMonth), date, currentYearInt), datepickerInput.getAttribute("value"));
        } else {
            assertEquals(String.format("%s/%s/%d", mappingMonthInt(futureMonth), date, currentYearInt + 1), datepickerInput.getAttribute("value"));
        }
    }

    private static HashMap<Integer, String> mappingMonthName() {
        HashMap<Integer, String> map = new HashMap<Integer, String>() {{
            put(1, "January");
            put(2, "February");
            put(3, "March");
            put(4, "April");
            put(5, "May");
            put(6, "June");
            put(7, "July");
            put(8, "August");
            put(9, "September");
            put(10, "October");
            put(11, "Noverbem");
            put(12, "December");
        }};
        return map;
    }

    private static String mappingMonthInt(String monthName) {
        for (Integer i : monthNameMapping.keySet()) {
            if (monthNameMapping.get(i).equals(monthName)) {
                return (i >= 10) ? String.valueOf(i) : "0" + String.valueOf(i);
            }
        }
        return null;
    }

    private void selectCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        int d = cal.get(Calendar.DATE); // return the current date in int format
        String date = String.valueOf(d); //convert int to string
        driver.findElement(By.linkText(date)).click();
    }

    private void selectFutureDate(String d,String m)
    {
        Calendar cal = Calendar.getInstance();
        int currentmonth = cal.get(Calendar.MONTH)+1; // return the current date in int format
        for(int i = currentmonth; i >= 1; i++) {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[2]/span")).click();//Next arrow
            String mon = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span")).getText();
            if(mon.equals(m)) {
                driver.findElement(By.linkText(d)).click();
                break;
            }
        }
    }

    private void selectPastDate(String d,String m)
    {
        Calendar cal = Calendar.getInstance();
        int currentmonth = cal.get(Calendar.MONTH)+1; // return the current date in int format
        for(int i = currentmonth; i >= 1; i--) {
            driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/a[1]/span")).click();//Prev arrow
            String mon = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/span")).getText();
            if(mon.equals(m)) {
                System.out.println();
                driver.findElement(By.linkText(d)).click();
                break;
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
