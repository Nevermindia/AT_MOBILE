package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject{
    protected static String
            RETURN_BACK_BUTTON,
            VIEW_LIST_BUTTON;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }
    public void returnToSearchResultsFromTheArticle(){
        this.waitForElementAndClick(
                RETURN_BACK_BUTTON,
                "Cannot find button to return to the search results list",
                5
        );
    }
    public void clickByViewListButton(){
        this.waitForElementAndClick(
                VIEW_LIST_BUTTON,
                "Cannot go to View list",
                5
        );
    }

}
