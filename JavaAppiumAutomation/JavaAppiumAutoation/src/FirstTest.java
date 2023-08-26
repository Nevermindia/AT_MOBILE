import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp () throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel_XL_API_31");
        capabilities.setCapability("platformVersion", "12.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("appium:app", "C:\\Users\\bsc-apetkevich\\GIT_HUB\\automation_testing\\JavaAppiumAutomation\\JavaAppiumAutoation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), capabilities);
    }
    @After
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void testInputFieldContainsTextSearchWikipedia(){
        assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Search Wikipedia",
                "Field for text input does not have text 'Search Wikipedia'");


    }
    @Test
    public void testCheckSearchResultIsNotPresent(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find Search... locator",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "No search results are found",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Search results are still present",
                5

        );

    }
    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }
    private void assertElementHasText(By by, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(by,"Element not found", 5);
        String actual_text = element.getText();
        Assert.assertEquals(error_message, expected_text, actual_text );

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;

    }

    private WebElement waitForElementAndSendKeys(By by, String keys, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(keys);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }
}



