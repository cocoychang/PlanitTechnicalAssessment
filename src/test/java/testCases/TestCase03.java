package testCases;

import base.baseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.homePage;
import pageObjects.shopPage;

import java.text.DecimalFormat;

public class TestCase03 extends baseTest {

    //private static final DecimalFormat roundFor = new DecimalFormat("0.00");
    String Item_1 = "Stuffed Frog";
    String Item_2 = "Fluffy Bunny";
    String Item_3 = "Valentine Bear";

    int orderCount_Item1 = 2;
    int orderCount_Item2 = 5;
    int orderCount_Item3 = 3;

    @Test
    public void TC03_Validate_The_Add_Items_on_Cart() {
        homePage objHomePage = new homePage(edriver);
        shopPage objShopPage = new shopPage(driver);
        objHomePage.startShoppingClick();
        //Buy 2 Stuffed Frog, 5 Fluffy Bunny , 3 Valentine Bear
        objShopPage.shoppingItemBuyButton(Item_1, orderCount_Item1);
        objShopPage.shoppingItemBuyButton(Item_2, orderCount_Item2);
        objShopPage.shoppingItemBuyButton(Item_3, orderCount_Item3);
        //Go to the cart page
        objHomePage.cart_click();
        //Verify the subtotal for each product is correct
        //validate the total quantity of each items
        Assert.assertEquals(objShopPage.getItemValue(Item_1),orderCount_Item1);
        Assert.assertEquals(objShopPage.getItemValue(Item_2),orderCount_Item2);
        Assert.assertEquals(objShopPage.getItemValue(Item_3),orderCount_Item3);
        //validate subtotal per item
        Float subTotal_Item_1 = objShopPage.getItemValue(Item_1) * objShopPage.getTheItemPrice(Item_1);
        Float subTotal_Item_2 = objShopPage.getItemValue(Item_2) * objShopPage.getTheItemPrice(Item_2);
        Float subTotal_Item_3 = objShopPage.getItemValue(Item_3) * objShopPage.getTheItemPrice(Item_3);
        Assert.assertEquals(objShopPage.getActualSubTotal(Item_1),floatConverter(subTotal_Item_1));
        Assert.assertEquals(objShopPage.getActualSubTotal(Item_2),floatConverter(subTotal_Item_2));
        Assert.assertEquals(objShopPage.getActualSubTotal(Item_3),floatConverter(subTotal_Item_3));
        //Verify that total = sum(sub totals)
        Float overAllTotal = subTotal_Item_1 + subTotal_Item_2 + subTotal_Item_3;
        System.out.println(overAllTotal);
        Assert.assertEquals(objShopPage.getOverAllSubtotal(),floatConverter(overAllTotal));


    }

    public Float floatConverter(Float number){
        Float converted = null;
        DecimalFormat roundFor = new DecimalFormat("0.00");
        converted = Float.parseFloat(roundFor.format(number));
        return converted;
    }


}
