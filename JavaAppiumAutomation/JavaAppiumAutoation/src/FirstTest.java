import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

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
    public void firstTest(){
        System.out.println("First test run");
    }
}