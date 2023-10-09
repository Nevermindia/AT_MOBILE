package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
            LOGIN_BUTTON="css:a[type='button']",
            LOGIN_INPUT="css:input[name='wpName']",
            PASSWORD_INPUT="css:input[name='wpPassword']",
            SUBMIT_BUTTON="css:#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    public void clickAuthButton() throws InterruptedException {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        WebDriverWait wait=new WebDriverWait(driver, 20);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[type='button']")));
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot click auth button", 5);
    }
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login,"Cannot enter login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "cannot enter password", 5);
    }
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click auth submit button", 5);
    }
}
