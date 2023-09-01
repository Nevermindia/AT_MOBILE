import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp () throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel_XL_API_31");
        capabilities.setCapability("platformVersion", "12.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", "org.wikipedia.main.MainActivity");

        capabilities.setCapability("appium:app", "C:\\Users\\bsc-apetkevich\\GIT_HUB\\automation_testing\\JavaAppiumAutomation\\JavaAppiumAutoation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/"), capabilities);
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find skip button", 5);
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
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search Wikipedia locator",
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
    @Test
    public void testCheckEverySearchResultContainsSearchString(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search... locator",
                5
        );
        driver.hideKeyboard();

        List<WebElement> list = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));

//        for (WebElement element:list
//             ) {
//            System.out.println(element.getText());
//
//        }
        for (WebElement element:list
             ) {assertElementContainsText(By.id("org.wikipedia:id/page_list_item_title"), "Java", "Search result title does not contain text 'Java'" );

        }

    }
    @Test
    public void testSaveTwoArticlesAndDeleteOne(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );
        String article_search = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                article_search,
                "Cannot find Search Wikipedia locator",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find locator for text Object-oriented programming language",
                5

        );
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find article title",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find 'add to reading list' button",
                5);
        String name_of_article_list = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_article_list,
                "Cannot find input to set name for articles folder",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@content-desc='Navigate up']"),
                "Cannot find button to return to the search results list",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='High-level programming language']"),
                "Cannot find locator for text 'High-level programming language' article",
                5

        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button to save article",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Add to another reading list']"),
                "Cannot find 'Add to another reading list' option",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannot find " +name_of_article_list +" folder",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='View list']"),
                "Cannot go to View list",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Learning programming']"),
                "Cannot find " +name_of_article_list +" folder",
                5
        );
        swipeElementToTheLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Saved article was not deleted",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='High-level programming language']"),
                "Cannot find second article",
                5

        );
        assertElementHasText(
                By.xpath("//*[@text='JavaScript']"),
                "JavaScript",
                "Article doesn't have title 'JavaScript'"
        );

    }
    @Test
    public void testCheckIfArticleHasTitle(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia input",
                5
        );
        String article_search = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                article_search,
                "Cannot find Search Wikipedia locator",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']"),
                "Cannot find locator for text Object-oriented programming language",
                5

        );
        assertElementPresent(
                By.xpath("//*[@text='Java (programming language)' and @class='android.view.View']"),
                "Cannot find title of the article"
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
    private void assertElementContainsText(By by, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(by,"Element not found", 5);
        String actual_text = element.getText().toLowerCase();
        Assert.assertTrue(error_message, actual_text.contains(expected_text.toLowerCase()) );

    }
    protected void swipeElementToTheLeft(By by, String error_message){
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
                .press(right_x,middle_y)
                .waitAction(150)
                .moveTo(left_x,middle_y)
                .release()
                .perform();

    }
    private void assertElementPresent(By by, String error_message){
        int amount_of_elements = driver.findElements(by).size();
        Assert.assertTrue(
                error_message +"\n" + "Element " + by.toString() + " supposed to be present",
                amount_of_elements==1);
    }

    }




