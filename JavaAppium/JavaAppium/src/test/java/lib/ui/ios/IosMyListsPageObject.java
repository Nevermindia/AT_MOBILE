package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "";
        ARTICLE_BY_TITLE_TPL = "";
    }

    public IosMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
