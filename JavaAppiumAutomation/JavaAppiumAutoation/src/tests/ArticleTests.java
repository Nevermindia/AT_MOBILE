package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCheckIfArticleHasTitle(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearch();
        String article_search = "Java";
        searchPageObject.inputSearchLine(article_search);
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String title_of_the_article="Java (programming language)";
        articlePageObject.assertArticleHasTitle(title_of_the_article);




    }
}
