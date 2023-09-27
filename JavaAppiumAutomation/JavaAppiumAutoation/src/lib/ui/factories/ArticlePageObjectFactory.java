package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidSearchPageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver) {
        return new AndroidArticlePageObject(driver);
    }
}
