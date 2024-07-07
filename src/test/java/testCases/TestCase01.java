package testCases;

import base.baseTest;
import org.testng.Assert;
import pageObjects.contactsPage;
import pageObjects.homePage;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestCase01 extends baseTest {


    @Test
    //(dataProviderClass = ReadExcelData.class,dataProvider = "credentials_data")
    public void TC01_validate_error_message(Method testname) {
        homePage objHomePage = new homePage(edriver);
        contactsPage objContact = new contactsPage(edriver);
        //From the home page go to contact page
        objHomePage.contact_tab_click();
        //Click submit button
        objContact.setSubmitButton_click();
        //Verify error messages
        Assert.assertEquals(objContact.getErrorMsg(),"We welcome your feedback - but we won't get it unless you complete the form correctly.");
        Assert.assertEquals(objContact.getForenameErrorMsg(),"Forename is required");
        Assert.assertEquals(objContact.getEmailErrorMsg(),"Email is required");
        Assert.assertEquals(objContact.getMessageFieldErrorMsg(),"Message is required");
        //Populate mandatory fields
        objContact.setForeNameSendKeys("john");
        objContact.setEmailSendKeys("john@gmail.com");
        objContact.setMessageSendKeys("test message");
        //Validate errors are gone
        Assert.assertFalse(objContact.setErrorMessageIsDisplayed(),"Header error message is gone");
        Assert.assertFalse(objContact.setForenameErrorMsgIsDisplayed(),"Forename error message is gone");
        Assert.assertFalse(objContact.setEmailErrorMsgIsDisplayed(),"Email error message is gone");
        Assert.assertFalse(objContact.setMessageFieldErrorMsgIsDisplayed(),"Message error message is gone");

    }


}
