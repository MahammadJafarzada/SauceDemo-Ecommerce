package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.InventoryPage;
import org.example.pages.CartPage;
import org.example.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void completeCheckoutTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.waitForPageLoad();
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartProductName(),
                "Sauce Labs Backpack"
        );

        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterCustomerInformation(
                "Mahammad",
                "Jafarzada",
                "AZ1000"
        );

        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getSummaryProductName(),
                "Sauce Labs Backpack",
                "Product is missing from checkout summary"
        );

        checkoutPage.clickFinish();

        Assert.assertEquals(
                checkoutPage.getCompleteMessage(),
                "Thank you for your order!",
                "Order was not completed successfully"
        );
    }
}