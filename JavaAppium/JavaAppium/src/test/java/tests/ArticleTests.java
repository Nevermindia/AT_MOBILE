package tests;

import io.qameta.allure.*;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;

@Epic("Tests for article page")
public class ArticleTests extends CoreTestCase {
    @Test
    @Tags({
            @Tag("android"),
            @Tag("mobile_web")
    })
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Check if article has title ")
    @Description("We init search, go to the article and check that it has a title")
    @Step("Starting test testCheckIfArticleHasTitle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckIfArticleHasTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearch();
        String article_search = "Java";
        searchPageObject.inputSearchLine(article_search);
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String title_of_the_article = "Java (programming language)";
        if (Platform.isMW()) {
            articlePageObject.assertTitleHasText(title_of_the_article);
        } else {
            articlePageObject.assertTitleHasContentDesc(title_of_the_article);
        }
    }
}
