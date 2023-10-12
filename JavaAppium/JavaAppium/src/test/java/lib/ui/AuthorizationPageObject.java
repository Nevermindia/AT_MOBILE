package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
            LOGIN_INPUT="css:input[name='wpName']",
            PASSWORD_INPUT="css:input[name='wpPassword']",
            SUBMIT_BUTTON="css:#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Entering login '{login}' and password '{password}'")
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login,"Cannot enter login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "cannot enter password", 5);
    }
    @Step("Pressing submit button")
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click auth submit button", 5);
    }
}
