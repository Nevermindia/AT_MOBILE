package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
            SAVE_BUTTON,
            ADD_TO_LIST_BUTTON,
            MY_LIST_NANE_INPUT,
            MY_LIST_OK_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ARTICLE_TITLE_TPL,
            ADD_TO_ANOTHER_LIST,
            FOLDER_ELEMENT_TPL,
            SEARCH_BUTTON;
    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
    /*Templates methods */
    public static String getArticleTitleXpath(String substring){
        return ARTICLE_TITLE_TPL.replace("{TITLE}",substring);


    }
    public static String getFolderXpathByName(String folder_name){
        return FOLDER_ELEMENT_TPL.replace("{FOLDER_NAME}",folder_name);


    }
    /*Templates methods */
    public WebElement waitForTitleElementPresent(String title_text){
        String article_title_xpath = getArticleTitleXpath(title_text);
        return this.waitForElementPresent(
                article_title_xpath,
                "Cannot find article title",
                15);
    }
    public void saveArticleToNewFolder(String name_of_folder){

        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find button to save article",
                5);
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find 'add to reading list' button",
                5);
        this.waitForElementAndSendKeys(
                MY_LIST_NANE_INPUT,
                name_of_folder,
                "Cannot find input to set name for articles folder",
                5);
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }
    public void saveArticleToExistingFolder(String name_of_folder){
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find button to save article",
                5);
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find button to save article",
                5);
        this.waitForElementAndClick(
                ADD_TO_ANOTHER_LIST,
                "Cannot find 'Add to another reading list' option",
                5
        );
        String folder_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_xpath,
                "Cannot find " +name_of_folder +" folder",
                5
        );
    }
    public void removeArticleFromSavedIfItAddes(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click remove button",
                    1);
            this.waitForElementPresent(
                    SAVE_BUTTON,
                    "Cannot find and click button to save article to my list after removing it",
                    5
            );
        }

    }
    public void assertArticleHasTitle(String article_title){
        String article_title_xpath = getArticleTitleXpath(article_title);
        this.assertElementPresent(
                article_title_xpath,
                "Cannot find title of the article"
        );
    }
    public void assertTitleHasText(String expected_article_title){
        String article_title_xpath = getArticleTitleXpath(expected_article_title);
        this.assertElementHasText(
                article_title_xpath,
                expected_article_title,
                "Article doesn't have title "+expected_article_title
        );
    }
    public void waitForSaveButtonAndClick(){
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAddes();
        }
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find button to save article",
                5);
    }

}
