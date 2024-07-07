package testCases;

import base.baseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.contactsPage;
import pageObjects.homePage;

import java.lang.reflect.Method;

public class TestCase02 extends baseTest {

    @Test
    public void TC02_Validate_Successful_Submission_message(Method testname){
        homePage objHomePage = new homePage(edriver);
        contactsPage objContact = new contactsPage(edriver);
        //From the home page go to contact page
        objHomePage.contact_tab_click();
        //Populate mandatory fields
        objContact.setForeNameSendKeys("john");
        objContact.setEmailSendKeys("john@gmail.com");
        objContact.setMessageSendKeys("test message");
        //Click submit button
        objContact.setSubmitButton_click();
        //Validate successful submission message
        Assert.assertTrue(objContact.SendingFeedbackIsDisplayed());
        Assert.assertEquals(objContact.getSuccessfulMsg(),"Thanks john, we appreciate your feedback.");

    }
}
