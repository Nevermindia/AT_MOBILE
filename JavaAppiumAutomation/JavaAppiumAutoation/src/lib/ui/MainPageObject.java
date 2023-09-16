package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }
    public WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }
    public void assertElementHasText(By by, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(by,"Element not found", 5);
        String actual_text = element.getText();
        Assert.assertEquals(error_message, expected_text, actual_text );

    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;

    }

    public WebElement waitForElementAndSendKeys(By by, String keys, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(keys);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }
    public void assertElementContainsText(By by, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(by,"Element not found", 5);
        String actual_text = element.getText().toLowerCase();
        Assert.assertTrue(error_message, actual_text.contains(expected_text.toLowerCase()) );

    }
    public void swipeElementToTheLeft(By by, String error_message){
        WebElement element = waitForElementPresent(
                by,
                error_message,
                5
        );
        int left_x=element.getLocation().getX();
        int right_x=left_x+element.getSize().getWidth();
        int lower_y = element.getLocation().getY();
        int upper_y=lower_y+element.getSize().getHeight();
        int middle_y=(upper_y+lower_y)/2;
        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(right_x,middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .moveTo(PointOption.point(left_x,middle_y))
                .release()
                .perform();

    }
    public void assertElementPresent(By by, String error_message){
        int amount_of_elements = driver.findElements(by).size();
        Assert.assertTrue(
                error_message +"\n" + "Element " + by.toString() + " supposed to be present",
                amount_of_elements==1);
    }

}
