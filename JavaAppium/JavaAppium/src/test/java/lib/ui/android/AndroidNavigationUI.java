package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        RETURN_BACK_BUTTON="xpath://*[@content-desc='Navigate up']";
        VIEW_LIST_BUTTON="xpath://*[@text='View list']";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
