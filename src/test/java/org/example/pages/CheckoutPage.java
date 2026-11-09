package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    private final By finishButton = By.id("finish");
    private final By completeHeader = By.className("complete-header");
    private final By summaryProductName = By.className("inventory_item_name");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void enterCustomerInformation(
            String firstName,
            String lastName,
            String postalCode
    ) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameInput)
        ).sendKeys(firstName);

        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }

    public void clickContinue() {
        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }

    public String getSummaryProductName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(summaryProductName)
        ).getText();
    }

    public void clickFinish() {
        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }

    public String getCompleteMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(completeHeader)
        ).getText();
    }
}