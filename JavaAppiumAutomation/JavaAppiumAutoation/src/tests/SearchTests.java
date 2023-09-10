package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testInputFieldContainsTextSearchWikipedia(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.assertInputElementHasText("Search Wikipedia");


    }
    @Test
    public void testCheckSearchResultIsNotPresent(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearch();
        searchPageObject.inputSearchLine("Java");
        searchPageObject.waitForSearchResultsPresent();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForSearchResultsNotPresent();

    }
    @Test
    public void testCheckEverySearchResultContainsSearchString(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearch();
        searchPageObject.inputSearchLine("Java");
        driver.hideKeyboard();

        List<WebElement> list = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));

//        for (WebElement element:list
//             ) {
//            System.out.println(element.getText());
//
//        }
        for (WebElement element:list
        ) {searchPageObject.assertEverySearchResultContainsSearchString("Java");

        }

    }

}
