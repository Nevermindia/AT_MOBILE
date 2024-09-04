package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {

    protected RemoteWebDriver driver;

    protected Platform Platform;


    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        this.Platform = lib.Platform.getInstance();
        driver = this.Platform.getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortraits();
        this.pressSkipButton();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() {
        driver.quit();
    }

    @Step("Rotate screen to portrait mode (this method is not working for Mobile Web)")
    protected void rotateScreenPortraits() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else System.out.println("Method does nothing for platform ");
    }

    @Step("Press Skip button (this method is not working for Mobile Web)")
    protected void pressSkipButton() {
        if (Platform.isAndroid()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.findElement(By.id("org.wikipedia:id/fragment_onboarding_skip_button")).click();
        } else if (Platform.isIos()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.findElement(By.name("Skip")).click();
        } else System.out.println("Method does nothing for platform ");
    }

    @Step("rotate screen to landscape mode (this method is not working for Mobile Web)")
    protected void rotateScreenLandscape() {
        if (Platform.isAndroid()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else System.out.println("Method does nothing for platform ");
    }

    @Step("Run app in background (this method is not working for Mobile Web)")
    protected void backgroundApp(int seconds) {
        if (Platform.isAndroid()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else System.out.println("Method does nothing for platform");
    }

    @Step("Open wiki web page for MobileWeb (this method is not working for Android)")
    protected void openWikiWebPageForMobileWeb() {
        if (Platform.isMW()) {
            driver.get("https://en.m.wikipedia.org");
        } else System.out.println("Method does nothing for platform ");
    }

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", lib.Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
