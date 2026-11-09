
package org.example.tests;

import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

//        Assert.assertTrue(
//                loginPage.getCurrentUrl().contains("inventory.html"),
//                "Login failed: Inventory page did not open"
//        );
        Assert.assertTrue(false, "Screenshot test");

    }
}
