package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosNavigationUI extends NavigationUI {
    static {
        RETURN_BACK_BUTTON = "";
        VIEW_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name='Saved']";
    }

    public IosNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
