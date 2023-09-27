package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidMyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(AppiumDriver driver) {
        return new AndroidMyListsPageObject(driver);
    }
}
