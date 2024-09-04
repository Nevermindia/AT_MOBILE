package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://localhost:4723/";

    private Platform() {
    }

    private static Platform instance;

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isMW() {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    public boolean isIos() {
        return isPlatform(PLATFORM_IOS);
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AppiumDriver(URL, getAndroidDesiredCapabilities());
        } else if (this.isIos()) {
            return new IOSDriver(URL, getIosDesiredCapabilities());
        } else if (this.isMW()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            return new ChromeDriver(this.getMVChromeOptions());
        } else throw new Exception("Cannot detect type of the Driver. Platform values is " + this.getPlatformVar());
    }

    public static Platform getInstance() {
        if (instance == null) {
            return new Platform();
        } else return instance;
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel_XL_API_31");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("appium:app", "/Users/stacia/IdeaProjects/automation_testing/JavaAppium/JavaAppium/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIosDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 13");
        capabilities.setCapability("platformVersion", "15.0");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:app", "/Users/stacia/IdeaProjects/automation_testing/JavaAppium/JavaAppium/apks/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMVChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");
        return chromeOptions;
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
