package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private final static String login = "Stacia31415";
    private final static String password = "Stacia314159";

    @Test
    public void testSaveTwoArticlesAndDeleteOne() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        if (Platform.isMW()) {
            navigationUI.openNavigation();
            navigationUI.clickLogin();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            driver.get("https://en.m.wikipedia.org");
        }
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearch();
        String article_search = "Java";
        searchPageObject.inputSearchLine(article_search);
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElementPresent("Java (programming language)");
        String name_of_folder = "Learning programming";
        if (Platform.isMW()) {
            articlePageObject.waitForSaveButtonAndClick();
        } else {
            articlePageObject.saveArticleToNewFolder(name_of_folder);
        }

        if (Platform.isMW()) {
            navigationUI.returnToTheMainPage();
            searchPageObject.initSearch();
            searchPageObject.inputSearchLine(article_search);
        } else {
            navigationUI.returnToSearchResultsFromTheArticle();
        }

        searchPageObject.clickByArticleWithSubstring("igh-level programming language");
        articlePageObject.waitForTitleElementPresent("JavaScript");
        if (Platform.isAndroid()) {
            articlePageObject.saveArticleToExistingFolder(name_of_folder);
            navigationUI.clickByViewListButton();
            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeOrClickByArticleToDelete("Java (programming language)");
            myListsPageObject.waitForSavedArticleAndClick("High-level programming language");
            articlePageObject.assertTitleHasText("JavaScript");
        } else {
            articlePageObject.waitForSaveButtonAndClick();
            navigationUI.openNavigation();
            navigationUI.clickByViewListButton();
            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.swipeOrClickByArticleToDelete("Java (programming language)");
            driver.navigate().refresh();
            if (Platform.isMW()) {
                myListsPageObject.assertTitleHasText("JavaScript");

            } else {
                myListsPageObject.waitForSavedArticleAndClick("High-level programming language");

            }

        }
    }
}

