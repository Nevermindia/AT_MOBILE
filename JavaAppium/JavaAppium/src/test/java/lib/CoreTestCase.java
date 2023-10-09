package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    protected Platform Platform;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.Platform = lib.Platform.getInstance();
        driver = this.Platform.getDriver();
        this.rotateScreenPortraits();
        this.pressSkipButton();
        this.openWikiWebPageForMobileWeb();
    }


    @Override
    protected void tearDown() throws Exception {

        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortraits(){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
        else System.out.println("Method does nothing for platform ");
    }
    protected void pressSkipButton(){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.findElement(By.id("org.wikipedia:id/fragment_onboarding_skip_button")).click();
        }
        else System.out.println("Method does nothing for platform ");
    }
    protected void rotateScreenLandscape(){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }
        else System.out.println("Method does nothing for platform ");

    }

    protected void backgroundApp(int seconds){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));;
        }
        else System.out.println("Method does nothing for platform ");
    }
    protected void openWikiWebPageForMobileWeb(){
        if (Platform.isMW()){
            driver.get("https://en.m.wikipedia.org");
        }
        else System.out.println("Method does nothing for platform ");

    }
}
