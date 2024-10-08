package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        ADD_TO_LIST_BUTTON = "xpath://*[@text='Add to list']";
        MY_LIST_NANE_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        ARTICLE_TITLE_TPL = "xpath://android.view.View[@content-desc='{TITLE}']";
        ADD_TO_ANOTHER_LIST = "xpath://*[@text='Add to another reading list']";
        FOLDER_ELEMENT_TPL = "xpath://*[@text='{FOLDER_NAME}']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
