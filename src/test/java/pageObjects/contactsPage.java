package pageObjects;

import base.baseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class contactsPage extends baseTest {
    String errorMessage;

    public contactsPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//a[contains(text(),'Submit')]")
    private WebElement getSubmitButton;

    @FindBy(xpath = "//div[@ui-if='!contactValidSubmit']//child::div[@class='alert alert-error ng-scope']")
    private WebElement getHeaderErrorMessage;

    @FindBy(xpath = "//span[@class='help-inline ng-scope' and @ui-if='form.forename.$dirty && form.forename.$error.required' ]")
    private WebElement getForenameErrorMsg;

    @FindBy(xpath = "//span[@class='help-inline ng-scope' and @ui-if='form.email.$dirty && form.email.$error.required' ]")
    private WebElement getEmailErrorMsg;

    @FindBy(xpath = "//span[@class='help-inline ng-scope' and @ui-if='form.message.$dirty && form.message.$error.required' ]")
    private WebElement getMessageFieldErrorMsg;

    @FindBy(xpath = "//input[@id='forename']")
    private WebElement getForenameField;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement getEmailField;

    @FindBy(xpath = "//textarea[@id='message']")
    private WebElement getMessageField;

    @FindBy(xpath ="//div[@class='alert alert-success']")
    private WebElement getSuccessfulMessage;

    @FindBy(xpath ="//h1[contains(text(),'Sending Feedback')]")
    private WebElement getSendingFeedback;



    public void setSubmitButton_click() {
        getSubmitButton.click();
    }

    public String getErrorMsg() {
        errorMessage = getHeaderErrorMessage.getText();
        System.out.println(errorMessage);

        return errorMessage;
    }

    public String getForenameErrorMsg() {
        errorMessage = getForenameErrorMsg.getText();
        System.out.println(errorMessage);

        return errorMessage;
    }

    public String getEmailErrorMsg() {
        errorMessage = getEmailErrorMsg.getText();
        System.out.println(errorMessage);

        return errorMessage;
    }

    public String getMessageFieldErrorMsg() {
        errorMessage = getMessageFieldErrorMsg.getText();
        System.out.println(errorMessage);

        return errorMessage;
    }

    public void setForeNameSendKeys(String data) {
        getForenameField.sendKeys(data);
    }

    public void setEmailSendKeys(String data) {
        getEmailField.sendKeys(data);
    }

    public void setMessageSendKeys(String data) {
        getMessageField.sendKeys(data);
    }

    public boolean setErrorMessageIsDisplayed() {
        boolean bool = true;
        try {
            getHeaderErrorMessage.isDisplayed();
        } catch (Exception e) {
            bool = false;
        }
        return bool;
    }

    public boolean setEmailErrorMsgIsDisplayed() {
        boolean bool = true;
        try {
            getEmailErrorMsg.isDisplayed();
        } catch (Exception e) {
            bool = false;
        }
        return bool;
    }

    public boolean setForenameErrorMsgIsDisplayed() {
        boolean bool = true;
        try {
            getForenameErrorMsg.isDisplayed();
        } catch (Exception e) {
            bool = false;
        }
        return bool;
    }

    public boolean setMessageFieldErrorMsgIsDisplayed() {
        boolean bool = true;
        try {
            getMessageFieldErrorMsg.isDisplayed();
        } catch (Exception e) {
            bool = false;
        }
        return bool;
    }

    public boolean SendingFeedbackIsDisplayed() {
        boolean bool = true;
        try {
            getSendingFeedback.isDisplayed();
        } catch (Exception e) {
            bool = false;
        }
        return bool;
    }

    public String getSuccessfulMsg() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(getSuccessfulMessage));
        String Message = getSuccessfulMessage.getText();
        System.out.println(Message);

        return Message;
    }


}
