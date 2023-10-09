package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT ="css:button#searchIcon";
        SEARCH_INPUT="css:form>input[type='search']";
        SEARCH_RESULT="css:ul.page-list>li.page-summary";
        SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON="css:button.cancel";
        //не исправлен один локатор ------>
        ARTICLE_ELEMENT="xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']/../*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']/..";
    }
    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
