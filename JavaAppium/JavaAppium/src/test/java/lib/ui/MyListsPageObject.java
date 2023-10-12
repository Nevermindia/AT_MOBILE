package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON;
    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    /*Templates methods */
    public static String getFolderXpathByName(String substring){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",substring);


    }
    public static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}",article_title);


    }
    public static String getRemoveButtonByTitle(String article_title){

        return REMOVE_FROM_SAVED_BUTTON.replace("{ARTICLE_TITLE}",article_title);

    }
    /*Templates methods */

    @Step("Opennig folder with name '{name_of_folder}'")
    public void openFolderByName(String name_of_folder){
        String folder_name_xpath=getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find " + name_of_folder +" folder",
                5
        );
    }
    @Step("Waiting for article with title '{article_title}' to appear")
    public void waitForArticleToAppear(String article_title){
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article with title " + article_title,
                15
        );
    }
    @Step("Waiting for article with title '{article_title}' to disappear")
    public void waitForArticleToDisappear(String article_title){
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_title_xpath,
                "Saved article with title " + article_title + "is still present",
                15
        );
    }
    @Step("Deleting article with title '{article_title}' from my list (watchlist)")
    public void swipeOrClickByArticleToDelete(String article_title){
        if (Platform.getInstance().isAndroid()) {
            this.waitForArticleToAppear(article_title);
            String article_title_xpath = getSavedArticleXpathByTitle(article_title);
            this.swipeElementToTheLeft(
                    article_title_xpath,
                    "Cannot find saved article"
            );
            this.waitForArticleToDisappear(article_title);
        }
        else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot clock button to remove article from the list",
                    10
            );
        }

    }
    @Step("Waiting for saved article with title '{article_title}' and selecting it")
    public void waitForSavedArticleAndClick(String article_title){
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                article_title_xpath,
                "Cannot find second article",
                5

        );
    }
    @Step("Making sure article has title '{expected_article_title}'")
    public void assertTitleHasText(String expected_article_title){
        String article_title_xpath = getSavedArticleXpathByTitle(expected_article_title);
        this.assertElementHasText(
                article_title_xpath,
                expected_article_title,
                "Saved article doesn't have title "+expected_article_title
        );
    }

}
