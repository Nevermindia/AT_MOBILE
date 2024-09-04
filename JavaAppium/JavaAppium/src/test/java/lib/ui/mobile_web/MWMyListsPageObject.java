package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://li[contains(@class, 'page-summary with-watchstar')]//h3[contains(text(),'{ARTICLE_TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://li[contains(@class, 'page-summary with-watchstar')]//h3[contains(text(),'{ARTICLE_TITLE}')]/../../a[contains(@class, 'watched')]";

    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
