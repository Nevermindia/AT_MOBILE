package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    private static final String
            FOLDER_BY_NAME_TPL="xpath://*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL="xpath://*[@text='{ARTICLE_TITLE}']";
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
    /*Templates methods */
    public static String getFolderXpathByName(String substring){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",substring);


    }
    public static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}",article_title);


    }
    /*Templates methods */

    public void openFolderByName(String name_of_folder){
        String folder_name_xpath=getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find " + name_of_folder +" folder",
                5
        );
    }
    public void waitForArticleToAppear(String article_title){
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article with title " + article_title,
                15
        );
    }
    public void waitForArticleToDisappear(String article_title){
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_title_xpath,
                "Saved article with title " + article_title + "is still present",
                15
        );
    }
    public void swipeByArticleToDelete(String article_title){
        this.waitForArticleToAppear(article_title);
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToTheLeft(
                article_title_xpath,
                "Cannot find saved article"
        );
        this.waitForArticleToDisappear(article_title);

    }
    public void waitForSavedArticleAndClick(String article_title){
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                article_title_xpath,
                "Cannot find second article",
                5

        );
    }
}
