package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
    SEARCH_INIT_ELEMENT ="id:org.wikipedia:id/search_container";
    SEARCH_INPUT="id:org.wikipedia:id/search_src_text";
    SEARCH_RESULT="id:org.wikipedia:id/page_list_item_title";
    SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_description' and contains(@text,'{SUBSTRING}')]";
    SEARCH_CANCEL_BUTTON="id:org.wikipedia:id/search_close_btn";
    ARTICLE_ELEMENT="xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']/../*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']/..";
}
    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }



}
