package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://localhost:4723/";


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel_XL_API_31");
        capabilities.setCapability("platformVersion", "12.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("appium:app", "C:\\Users\\bsc-apetkevich\\GIT_HUB\\automation_testing\\JavaAppiumAutomation\\JavaAppiumAutoation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        driver.findElement(By.id("org.wikipedia:id/fragment_onboarding_skip_button")).click();
    }


    @Override
    protected void tearDown() throws Exception {

        driver.quit();
        super.tearDown();
    }



}
