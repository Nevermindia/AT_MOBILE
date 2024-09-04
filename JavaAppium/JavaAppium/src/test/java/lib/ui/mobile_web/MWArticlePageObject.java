package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        SAVE_BUTTON = "css:#ca-watch>span.minerva-icon.minerva-icon--star-base20";
        MY_LIST_NANE_INPUT = "id:org.wikipedia:id/text_input";
        ARTICLE_TITLE_TPL = "css:#content h1";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#ca-watch > span.minerva-icon.minerva-icon--unStar-progressive";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
