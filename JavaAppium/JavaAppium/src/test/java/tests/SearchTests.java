package tests;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.*;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {
    @Test
    @Tags({
            @Tag("android"),
            @Tag("mobile_web")
    })
    @Feature(value = "Search")
    @DisplayName("Check if input field on the Main Page has text \"Search Wikipedia\"")
    @Description("We check that input field element has specific title")
    @Step("Starting test testInputFieldContainsTextSearchWikipedia")
    @Severity(value = SeverityLevel.MINOR)
    public void testInputFieldContainsTextSearchWikipedia() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.assertInputElementHasText("Search Wikipedia");
    }

    @Test
    @Tags({
            @Tag("android"),
            @Tag("mobile_web")
    })
    @Feature(value = "Search")
    @DisplayName("Check search results disappear after pressing Cancel search Button")
    @Description("We init search, click cancel search button and check that search results have disappeared")
    @Step("Staring test testCheckSearchResultIsNotPresent")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchResultIsNotPresent() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearch();
        searchPageObject.inputSearchLine("Java");
        searchPageObject.waitForSearchResultsPresent();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForSearchResultsNotPresent();

    }

    @Test
    @Tags({
            @Tag("android"),
            @Tag("mobile_web")
    })
    @Feature(value = "Search")
    @DisplayName("Check that every search result contains search line")
    @Description("We init search and check that every search result contains search line")
    @Step("Staring test testCheckEverySearchResultContainsSearchString")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckEverySearchResultContainsSearchString() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearch();
        searchPageObject.inputSearchLine("Java");
        if (Platform.isAndroid()) {
            ((AppiumDriver) driver).hideKeyboard();
        }
        List<WebElement> list = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));

        for (WebElement element : list
        ) {
            searchPageObject.assertEverySearchResultContainsSearchString("Java");
        }
    }

    @Test
    @Tags({
            @Tag("android"),
            @Tag("mobile_web")
    })
    @Feature(value = "Search")
    @DisplayName("Check that search results have specific titles and description")
    @Description("We init search and check 3 search results on having specific title and description")
    @Step("Starting test testCheckSearchResultsHaveArticlesByTitleAndDescription")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckSearchResultsHaveArticlesByTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        String search_line = "Twisted metal",
                first_article_title = "Twisted Metal",
                first_article_description = "Vehicular combat video game series",
                second_article_title = "Twisted Metal (TV series)",
                second_article_description = "2023 television series",
                third_article_title = "Twisted Metal (2012 video game)",
                third_article_description = "2012 video game";
        searchPageObject.initSearch();
        searchPageObject.inputSearchLine(search_line);
        searchPageObject.waitForElementByTitleAndDescription(first_article_title, first_article_description);
        searchPageObject.waitForElementByTitleAndDescription(second_article_title, second_article_description);
        searchPageObject.waitForElementByTitleAndDescription(third_article_title, third_article_description);
        //searchPageObject.takeScreenshot("search_results");
    }
}
