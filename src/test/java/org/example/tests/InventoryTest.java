package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {

    @Test
    public void verifyInventoryProductsTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.waitForPageLoad();

        Assert.assertEquals(
                inventoryPage.getProductCount(),
                6,
                "Product count is incorrect"
        );

        Assert.assertEquals(
                inventoryPage.getFirstProductName(),
                "Sauce Labs Backpack",
                "First product name is incorrect"
        );
    }
}