package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {
    protected static String
            RETURN_BACK_BUTTON,
            VIEW_LIST_BUTTON,
            NAVIGATION_BUTTON,
            WIKIPEDIA_ELEMENT,
            LOGIN_BUTTON;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Returning from article to the search results")
    public void returnToSearchResultsFromTheArticle() {
        this.waitForElementAndClick(
                RETURN_BACK_BUTTON,
                "Cannot find button to return to the search results list",
                5
        );
    }

    @Step("Returning to the Main page")
    public void returnToTheMainPage() {
        this.waitForElementAndClick(
                WIKIPEDIA_ELEMENT,
                "Cannot find wikipedia logo to return to the main page",
                5
        );
    }

    @Step("Clicking by view list button")
    public void clickByViewListButton() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    VIEW_LIST_BUTTON,
                    "Cannot click WATCHLIST button in navigation",
                    100
            );
        } else {
            this.waitForElementAndClick(
                    VIEW_LIST_BUTTON,
                    "Cannot go to View list",
                    5
            );
        }
    }


    @Step("Opening navigation bar")
    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(
                    NAVIGATION_BUTTON,
                    "Cannot click navigation button",
                    5);
        }

    }

    @Step("Clicking login button in navigation bar")
    public void clickLogin() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    LOGIN_BUTTON,
                    "Cannot click Navigation button",
                    100
            );
        }
    }
}
