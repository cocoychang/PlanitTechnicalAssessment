package pageObjects;

import base.baseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class homePage extends baseTest {

    public homePage(WebDriver driver) {
        baseTest.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@id='nav-contact']/a")
    private WebElement contactTab;

    @FindBy(xpath = "//a[contains(text(),'Start Shopping')]")
    private WebElement getStartShoppingButton;


    @FindBy(xpath = "//a[@href='#/cart']")
    private WebElement getCartButton;


    public void contact_tab_click() {
        contactTab.click();
    }

    public void startShoppingClick(){
        getStartShoppingButton.click();
    }

    public void cart_click(){
        getCartButton.click();

    }


}