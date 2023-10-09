package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            ARTICLE_ELEMENT;
    public SearchPageObject(RemoteWebDriver driver){
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
                SEARCH_INIT_ELEMENT,
                "element to init search is not present" + SEARCH_INIT_ELEMENT,
                5);
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click seach init element",
                5
        );
    }
    public void inputSearchLine(String searchLine){
        this.waitForElementPresent(
                SEARCH_INPUT,
                "element to input search line is not present",
                5);
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                searchLine,
                "Cannot find search input and send keys",
                5
        );

    }
    public void waitForSearchResultsPresent(){
        this.waitForElementPresent(
                SEARCH_RESULT,
                "No search results found",
                5
        );
    }
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Search button is not present",
                5
        );
    }
    public void waitForSearchResultsNotPresent(){
        this.waitForElementNotPresent(
                SEARCH_RESULT,
                "Search results are still present",
                5
        );
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring " + substring,
                10
        );
    }
    public void clickCancelSearch(){
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click cancel button",
                5
        );
    }
    public void assertEverySearchResultContainsSearchString(String search_line){
        this.assertElementContainsText(SEARCH_RESULT,
                search_line,
                "Search result title does not contain text "+search_line );

    }
    public void assertInputElementHasText(String expected_text){
        this.assertElementHasText("xpath://*[contains(@text, 'Search Wikipedia')]", expected_text,
                "Field for text input does not have text" + expected_text);
    }
    public void waitForElementByTitleAndDescription(String title, String description){
        String article_xpath = getResultArticleXpath(title,description);
        this.waitForElementPresent(
                article_xpath,
                "There is no article with title/description: "+title +"/"+description,
                10
        );
    }
}
