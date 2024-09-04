package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosArticlePageObject extends ArticlePageObject {
    static {
        SAVE_BUTTON = "";
        ADD_TO_LIST_BUTTON = "";
        MY_LIST_NANE_INPUT = "";
        MY_LIST_OK_BUTTON = "";
        ARTICLE_TITLE_TPL = "";
        ADD_TO_ANOTHER_LIST = "";
        FOLDER_ELEMENT_TPL = "";
    }

    public IosArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}