package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_RESULT = "xpath://XCUIElementTypeCell";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        ARTICLE_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']/../XCUIElementTypeStaticText[@name='{DESCRIPTION}']/..";
    }

    public IosSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
