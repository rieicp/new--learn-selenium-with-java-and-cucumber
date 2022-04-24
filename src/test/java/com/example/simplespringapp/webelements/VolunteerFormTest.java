package com.example.simplespringapp.webelements;

import java.util.List;

import com.example.simplespringapp.common.CommonUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;

public class VolunteerFormTest {
	private WebDriver driver;

	@BeforeEach
	public void setup() {
		driver = CommonUtility.init();
	}

	@Test
	public void formTest() throws InterruptedException {
		WebDriver driver= CommonUtility.init();

		driver.get("http://h2868196.stratoserver.net/selenium-course-volunteer-sign-up-mhn2");
		CommonUtility.wait4PageLoad(driver);

		String title=driver.getTitle(); // returns the title of the page
		assertEquals("Selenium Practice Form", title);
		
		String url=driver.getCurrentUrl();// returns the URL of the page
		assertEquals("http://h2868196.stratoserver.net/selenium-course-volunteer-sign-up-mhn2", url);
		
		//System.out.println(driver.getPageSource()); // returns HTML code for page
		
		//Firstname
		driver.findElement(By.xpath("//*[@id=\"RESULT_TextField-1\"]")).sendKeys("John");
		//Lastname
		driver.findElement(By.xpath("//*[@id=\"RESULT_TextField-2\"]")).sendKeys("Canedy");
		//phone
		driver.findElement(By.xpath("//*[@id=\"RESULT_TextField-3\"]")).sendKeys("2345789876");
		//country
		driver.findElement(By.xpath("//*[@id=\"RESULT_TextField-4\"]")).sendKeys("Canada");
		//city
		driver.findElement(By.xpath("//*[@id=\"RESULT_TextField-5\"]")).sendKeys("Toronto");
		//email
		driver.findElement(By.xpath("//*[@id=\"RESULT_TextField-6\"]")).sendKeys("anbdfge@gmail.com");
		
		//Handling Drop down
		//---------------------------
		WebElement drp = driver.findElement(By.xpath("//*[@id=\"RESULT_RadioButton-9\"]"));
		assertNotNull(drp);
		Select dropdown=new Select(drp);

		// 1)Find how many options present in drop down
		List <WebElement> options=dropdown.getOptions();
		assertEquals(4, options.size());
		
		//2) Extract all the options and print them
		for(WebElement e:options) {
			System.out.println(e.getText());
		}
		//3) Select option from the dropdown 
		//dropdown.selectByVisibleText("Morning");
		//dropdown.selectByIndex(3); //Evening  --> index starts from 0
		dropdown.selectByValue("Radio-1"); //AFTERNOON
		assertEquals("Afternoon", dropdown.getFirstSelectedOption().getText());

		//Handling radio buttons
		//----------------------------
		//Conditional commands
		WebElement radmale=driver.findElement(By.id("RESULT_RadioButton-7_1"));
		assertTrue(radmale.isDisplayed()); // check displayed or not - true
		assertTrue(radmale.isEnabled()); //checks enable or not - true
		assertFalse(radmale.isSelected()); // false
		if (
			(radmale.isDisplayed()) &&
			(radmale.isEnabled())
		) {
			radmale.click(); // select radio 'male'
		} else {
			WebElement labelMale = driver.findElement(By.xpath("//label[@for='RESULT_RadioButton-7_0']"));
			System.out.println(labelMale.isDisplayed()); // check displayed or not - true
			labelMale.click();  // alternative: select radio 'male'
		}
		assertTrue(radmale.isSelected()); //true

		//Handling check boxes
		//-----------------
		WebElement checkbox80 = driver.findElement(By.id("RESULT_CheckBox-8_0")); //Sunday
		assertFalse(checkbox80.isSelected());
		checkbox80.click();
		assertTrue(checkbox80.isSelected());

		WebElement checkbox81 = driver.findElement(By.id("RESULT_CheckBox-8_6")); //Saturday
		assertFalse(checkbox81.isSelected());
		checkbox81.click();
		assertTrue(checkbox81.isSelected());
		
		//Text Area
		//---------------
		WebElement textArea = driver.findElement(By.id("RESULT_TextArea-12"));
		textArea.sendKeys("TESTING");
		assertEquals("TESTING", textArea.getAttribute("value"));
		
		//Handle Links
		driver.findElement(By.linkText("Software Testing Tutorials")).click();
		Thread.sleep(1000);
		assertEquals("SDET QA Automation Techie", driver.getTitle());
		driver.navigate().back(); // go back to your previous page
		assertEquals("Selenium Practice Form", driver.getTitle());
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("Tools Training")).click();
		Thread.sleep(1000);
		assertEquals("Pavanonlinetrainings", driver.getTitle());
		driver.navigate().back(); // go back to your previous page
		assertEquals("Selenium Practice Form", driver.getTitle());
		Thread.sleep(1000);
		driver.navigate().forward();//move forward to your page
		Thread.sleep(1000);
		assertEquals("Pavanonlinetrainings", driver.getTitle());
		
		driver.navigate().refresh(); //refresh the page
	}

	@AfterEach
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
