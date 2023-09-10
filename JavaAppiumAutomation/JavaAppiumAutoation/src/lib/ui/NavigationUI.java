package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    private static final String
            RETURN_BACK_BUTTON="//*[@content-desc='Navigate up']",
            VIEW_LIST_BUTTON="//*[@text='View list']";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }
    public void returnToSearchResultsFromTheArticle(){
        this.waitForElementAndClick(
                By.xpath(RETURN_BACK_BUTTON),
                "Cannot find button to return to the search results list",
                5
        );
    }
    public void clickByViewListButton(){
        this.waitForElementAndClick(
                By.xpath(VIEW_LIST_BUTTON),
                "Cannot go to View list",
                5
        );
    }

}
