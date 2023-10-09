package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        RETURN_BACK_BUTTON="xpath://*[@content-desc='Navigate up']";
        VIEW_LIST_BUTTON="xpath://*[@text='View list']";
    }

    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
