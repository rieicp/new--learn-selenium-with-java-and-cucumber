package com.example.simplespringapp.mouseactions;

import com.example.simplespringapp.common.CommonUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MouseActionsTests {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = CommonUtility.init();
    }

    @Test
    public void mouseHoverTest() throws InterruptedException {
        driver.get("https://global.jd.com/");
        CommonUtility.wait4PageLoad(driver);
        // 定位“我的京东”
        WebElement myjdLink = driver.findElement(By.xpath("//*[contains(@id, 'myjd')]"));
        assertNotNull(myjdLink);
        // 定位“我的京东”的子菜单的“我的订单”
        WebElement orderLink = myjdLink.findElement(By.xpath("//*[contains(text(),\"订单\")]"));
        assertNotNull(orderLink);

        Actions actions = new Actions(driver);
        // 鼠标移到“我的京东”
        actions.moveToElement(myjdLink).build().perform();
        CommonUtility.wait4PageLoad(driver);
        // 鼠标移到“我的订单”，点击，弹出新窗口要求登录
        actions.moveToElement(orderLink).click().build().perform();
        // 断言新窗口的属性
        Set<String> handlevalues = driver.getWindowHandles();
        for(String h:handlevalues)
        {
            String title = driver.switchTo().window(h).getTitle();
            if(title.contains("登录") ) {
                WebElement login = driver.findElement(By.partialLinkText("登录"));
                assertNotNull(login);
            }
        }
    }

    @Test
    public void rightClickTest() throws InterruptedException {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        assertTrue(driver.getTitle().toLowerCase().contains("context"));

        // 定位 right click me 按钮
        WebElement rcmEl = driver.findElement(By.xpath("//span[text()='right click me']"));
        assertNotNull(rcmEl);

        Actions actions = new Actions(driver);
        // 右键单击，浮出上下文菜单
        actions.moveToElement(rcmEl).contextClick().build().perform();
        CommonUtility.wait4PageLoad(driver);

        // 菜单中的Copy项
        WebElement copyMenu = driver.findElement(By.xpath("/html/body/ul/li/span[text()='Copy']"));
        assertNotNull(copyMenu);

        // 点击Copy，会弹出Alert
        actions.moveToElement(copyMenu).click().build().perform();
        Thread.sleep(500);

        // 切换到Alert弹窗，并确认
        driver.switchTo().alert().accept();
    }

    @Test
    public void doubleClickTest() throws InterruptedException {
        driver.get("http://api.jquery.com/dblclick/");
        driver.switchTo().frame(0); //switch to frame
        WebElement ele=driver.findElement(By.xpath("/html/body/div"));
        assertTrue(ele.getCssValue("background").contains("rgb(0, 0, 255)"));
        Actions act=new Actions(driver);
        act.doubleClick(ele).build().perform(); // Double click on element
        Thread.sleep(1000);
        assertTrue(ele.getCssValue("background").contains("rgb(255, 255, 0)"));
        act.doubleClick(ele).build().perform(); // Double click on element
        Thread.sleep(1000);
        assertTrue(ele.getCssValue("background").contains("rgb(0, 0, 255)"));
    }

    @Test
    public void dragAndDropTest() throws InterruptedException {
        driver.get("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
        WebElement source_element=driver.findElement(By.id("box6")); //Rome - source element
        WebElement target_element=driver.findElement(By.id("box106")); //Italy -target element
        WebElement source_element2=driver.findElement(By.id("box2")); //Stockholm
        WebElement target_element2=driver.findElement(By.id("box101")); //Norway -target element
        Actions act=new Actions(driver);
        act.dragAndDrop(source_element, target_element).build().perform(); //Drag and drop
        assertTrue(source_element.getCssValue("background").contains("rgb(0, 255, 0)"));
        Thread.sleep(1000);
        act.dragAndDrop(source_element2, target_element2).build().perform(); //Drag and drop
        assertFalse(source_element2.getCssValue("background").contains("rgb(0, 255, 0)"));
        Thread.sleep(3000);
    }

    @Test
    public void resizingTest() {
        driver.get("https://jqueryui.com/resizable/");
        CommonUtility.wait4PageLoad(driver);
        driver.switchTo().frame(0); //switch to frame
        // 定位可改变尺寸的Box
        WebElement rzBox=driver.findElement(By.cssSelector("#resizable"));
        // 获取该Box的长宽
        int width = rzBox.getSize().getWidth();
        int height = rzBox.getSize().getHeight();
        // 定位改变尺寸的句柄
        WebElement rzhandle=rzBox.findElement(By.cssSelector("#resizable > div.ui-icon"));
        assertNotNull(rzhandle);
        Actions act=new Actions(driver);
        // 拖曳改变尺寸
        act.moveToElement(rzhandle).dragAndDropBy(rzhandle,200, 150).build().perform(); //resizing
        assertEquals(width + 200, rzBox.getSize().getWidth());
        assertEquals(height + 150, rzBox.getSize().getHeight());
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
