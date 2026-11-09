package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By inventoryList = By.className("inventory_list");
    private final By inventoryItems = By.className("inventory_item");
    private final By productNames = By.className("inventory_item_name");

    private final By addToCartBackpackButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private final By cartBadge =
            By.className("shopping_cart_badge");

    private final By cartLink =
            By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void waitForPageLoad() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(inventoryList)
        );
    }

    public int getProductCount() {
        waitForPageLoad();
        return driver.findElements(inventoryItems).size();
    }

    public String getFirstProductName() {
        waitForPageLoad();

        return driver.findElements(productNames)
                .get(0)
                .getText();
    }

    public void addBackpackToCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(addToCartBackpackButton)
        ).click();
    }

    public String getCartBadgeCount() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).getText();
    }

    public void openCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(cartLink)
        ).click();
    }
}