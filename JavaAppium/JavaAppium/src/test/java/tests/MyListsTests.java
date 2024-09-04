package tests;

import io.qameta.allure.*;
import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

@Epic("Tests for my list (watchlist)")
public class MyListsTests extends CoreTestCase {
    private final static String login = "Stacia31415";
    private final static String password = "Stacia314159";

    @Test
    @Tags({
            @Tag("android"),
            @Tag("mobile_web")
    })
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "My list")})
    @DisplayName("Save 2 articles to my list and delete one of them")
    @Description("We save 2 article to my list (watchlist), delete 1 of them and check that remaining article is second")
    @Step("Starting test testSaveTwoArticlesAndDeleteOne")
    @Severity(value = SeverityLevel.NORMAL)
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
            myListsPageObject.waitForSavedArticleAndClick("JavaScript");
            articlePageObject.assertTitleHasContentDesc("JavaScript");
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

