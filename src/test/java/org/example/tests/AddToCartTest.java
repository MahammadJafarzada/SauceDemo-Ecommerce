package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.InventoryPage;
import org.example.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void addBackpackToCartTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.waitForPageLoad();

        inventoryPage.addBackpackToCart();

        Assert.assertEquals(
                inventoryPage.getCartBadgeCount(),
                "1",
                "Cart badge count is incorrect"
        );

        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartProductName(),
                "Sauce Labs Backpack",
                "Product was not added to cart"
        );
    }
}