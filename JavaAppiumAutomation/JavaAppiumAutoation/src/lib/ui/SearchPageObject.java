package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT="org.wikipedia:id/search_src_text",
            SEARCH_RESULT="org.wikipedia:id/page_list_item_title",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON="org.wikipedia:id/search_close_btn",
            ARTICLE_ELEMENT="//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']/../*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']/..";
    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }
    /*Templates methods */
    public static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    public static String getResultArticleXpath(String title, String description){
        return ARTICLE_ELEMENT.replace("{TITLE}",title).replace("{DESCRIPTION}", description);}
    /*Templates methods */
    public void initSearch(){
        this.waitForElementPresent(
                By.id(SEARCH_INIT_ELEMENT),
                "element to init search is not present",
                5);
        this.waitForElementAndClick(
                By.id(SEARCH_INIT_ELEMENT),
                "Cannot find and click seach init element",
                5
        );
    }
    public void inputSearchLine(String searchLine){
        this.waitForElementPresent(
                By.id(SEARCH_INPUT),
                "element to input search line is not present",
                5);
        this.waitForElementAndSendKeys(
                By.id(SEARCH_INPUT),
                searchLine,
                "Cannot find search input and send keys",
                5
        );

    }
    public void waitForSearchResultsPresent(){
        this.waitForElementPresent(
                By.id(SEARCH_RESULT),
                "No search results found",
                5
        );
    }
    public void waitForSearchResultsNotPresent(){
        this.waitForElementNotPresent(
                By.id(SEARCH_RESULT),
                "Search results are still present",
                5
        );
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                10
        );
    }
    public void clickCancelSearch(){
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click cancel button",
                5
        );
    }
    public void assertEverySearchResultContainsSearchString(String search_line){
        this.assertElementContainsText(By.id(SEARCH_RESULT),
                search_line,
                "Search result title does not contain text "+search_line );

    }
    public void assertInputElementHasText(String expected_text){
        this.assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), expected_text,
                "Field for text input does not have text" + expected_text);
    }
    public void waitForElementByTitleAndDescription(String title, String description){
        String article_xpath = getResultArticleXpath(title,description);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "There is no article with title/description: "+title +"/"+description,
                10
        );
    }
}
