package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }
    public WebElement waitForElementPresent(String locator, String error_message, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }
    public void assertElementHasText(String locator, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(locator,"Element not found", 5);
        String actual_text = element.getText();
        Assert.assertEquals(error_message, expected_text, actual_text );

    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, 5);
        element.click();
        return element;

    }

    public WebElement waitForElementAndSendKeys(String locator, String keys, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, 5);
        element.sendKeys(keys);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public boolean isElementPresent(String locator){
        By by = this.getLocatorByString(locator);
        return driver.findElements(by).size()>0;
    }
    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts=0;
        boolean need_more_attempts = true;
        while (need_more_attempts){
            try {
                this.waitForElementAndClick(
                        locator,
                        error_message,
                        1
                );
                need_more_attempts=false;
            }
            catch (ElementNotInteractableException e){
                if (current_attempts>amount_of_attempts){
                    this.waitForElementAndClick(
                            locator,
                            error_message,
                            1
                    );
                }
            }
            current_attempts++;
        }
    }
    public void assertElementContainsText(String locator, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(locator,"Element not found", 5);
        String actual_text = element.getText().toLowerCase();
        Assert.assertTrue(error_message, actual_text.contains(expected_text.toLowerCase()) );

    }
    public void swipeElementToTheLeft(String locator, String error_message){
        if (Platform.getInstance().isAndroid()){
            WebElement element = waitForElementPresent(
                    locator,
                    error_message,
                    5
            );
            int left_x=element.getLocation().getX();
            int right_x=left_x+element.getSize().getWidth();
            int lower_y = element.getLocation().getY();
            int upper_y=lower_y+element.getSize().getHeight();
            int middle_y=(upper_y+lower_y)/2;
            TouchAction action = new TouchAction ((AppiumDriver)(driver));
            action
                    .press(PointOption.point(right_x,middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                    .moveTo(PointOption.point(left_x,middle_y))
                    .release()
                    .perform();
        }
        else System.out.println("Method does nothing for platform");

    }
    public void scrollWebPageUp(){
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor)driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        }
        else System.out.println("Method does nothing for platform");
    }


    public void assertElementPresent(String locator, String error_message){
        By by = this.getLocatorByString(locator);
        int amount_of_elements = driver.findElements(by).size();
        Assert.assertTrue(
                error_message +"\n" + "Element " + by.toString() + " supposed to be present",
                amount_of_elements==1);
    }
    private By getLocatorByString(String locator_with_type){
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if (by_type.equals("xpath")){
            return By.xpath(locator);
        }
        if (by_type.equals("id")){
            return By.id(locator);
        }
        if (by_type.equals("css")){
            return By.cssSelector(locator);
        }
        else throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
    }
    public String takeScreenshot(String name){
        TakesScreenshot ts = (TakesScreenshot) this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") +"/"+ name +"_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: "+path);
        }
        catch (Exception e){
            System.out.println("Cannot take screenshot. Error "+e.getMessage());
        }
        return path;
    }
    @Attachment
    public static byte[] screenshot(String path){
       byte[] bytes = new byte[0];
       try {
           bytes = Files.readAllBytes(Paths.get(path));
       }
       catch (IOException e){
           System.out.println("Cannot get bytes from screenshots. Error: " + e.getMessage());

       }
       return bytes;
    }


}
