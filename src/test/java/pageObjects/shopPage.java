package pageObjects;

import base.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shopPage extends baseTest {

    int value;
    String item_price;

    float int_item_price;

    @FindBy(xpath = "//*[@class='total ng-binding']")
    private WebElement OverAllTotal;


public shopPage(WebDriver driver){
    baseTest.driver = driver;
    PageFactory.initElements(driver,this);
}


    public void shoppingItemBuyButton(String Item_name, int number_of_order){
        WebElement item = driver.findElement(By.xpath("//h4[@class='product-title ng-binding' and contains(text(),'"+Item_name+"')]//following-sibling::p/a"));
        for(int i = 0; i<= number_of_order-1; i++){
            item.click();
        }
    }
    public Integer getItemValue(String Item_name){
        WebElement item = driver.findElement(By.xpath("//tbody//child::td[@class='ng-binding' and contains(text(),'"+Item_name+"')]//following-sibling::td[2]/input"));
        value = Integer.parseInt( item.getAttribute("value"));
        //System.out.println("count:: "+value);
        return value;
    }
    public Float getTheItemPrice(String Item_name){
    WebElement item = driver.findElement(By.xpath("//tbody//child::td[@class='ng-binding' and contains(text(),'"+Item_name+"')]//following-sibling::td[1]"));
    item_price = item.getText().replace("$","");
    int_item_price = Float.parseFloat(item_price);
    return int_item_price;
    }

    public Float getActualSubTotal(String Item_name){
        WebElement item = driver.findElement(By.xpath("//tbody//child::td[@class='ng-binding' and contains(text(),'"+Item_name+"')]//following-sibling::td[3]"));
        item_price = item.getText().replace("$","");
        int_item_price = Float.parseFloat(item_price);
        return int_item_price;
    }

    public Float getOverAllSubtotal(){
        String total = OverAllTotal.getText().replace("Total: ","");
        int_item_price = Float.parseFloat(total);
        return int_item_price;
    }

}
