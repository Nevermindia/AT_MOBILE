package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        VIEW_LIST_BUTTON="css:a[data-event-name='menu.watchlist']";
        NAVIGATION_BUTTON="css:#mw-mf-main-menu-button";
        WIKIPEDIA_ELEMENT="css:div[class='branding-box']";
        LOGIN_BUTTON="css:a[data-event-name='menu.login']";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }


}
