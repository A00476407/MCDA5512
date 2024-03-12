package com.assign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Set the path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        System.setProperty("webdriver.chrome.whitelistedIps", "");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Initialize Chrome WebDriver
        driver = new ChromeDriver(options);
    }

    @Test
    public void testFormSubmissionAndConfirmation() {
        // Open the webpage
        driver.get("https://dev.cs.smu.ca/sel/practice.html");

        // Fill out the form
        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys("Ka Man Leung");

        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("kaman.leung@smu.ca");

        WebElement femaleSelect = driver.findElement(By.id("female"));
        femaleSelect.click();

        WebElement countryDropdown = driver.findElement(By.id("country"));
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("Canada");

        WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
        newsletterCheckbox.click();

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Wait for the next page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://dev.cs.smu.ca/sel/confirmation.html"));

        // Assert the confirmation message on the next page
        WebElement confirmationMessage = driver.findElement(By.className("confirmation-message"));
        String confirmationText = confirmationMessage.getText();
        Assert.assertTrue(confirmationText.contains("Thank you"), "Confirmation page not found!");

        // Assert the submitted details on the confirmation page
        WebElement detailsContainer = driver.findElement(By.className("details-container"));

        WebElement nameElement = detailsContainer.findElement(By.id("name"));
        Assert.assertEquals(nameElement.getText(), "Ka Man Leung", "Name mismatch");

        WebElement emailElement = detailsContainer.findElement(By.id("email"));
        Assert.assertEquals(emailElement.getText(), "kaman.leung@smu.ca", "Email mismatch");

        WebElement countryElement = detailsContainer.findElement(By.id("country"));
        Assert.assertEquals(countryElement.getText(), "Canada", "Country mismatch");

        WebElement genderElement = detailsContainer.findElement(By.id("gender"));
        Assert.assertEquals(genderElement.getText(), "Female", "Gender mismatch");

        WebElement newsletterElement = detailsContainer.findElement(By.id("newsletterValue"));
        Assert.assertEquals(newsletterElement.getText(), "Yes", "Newsletter subscription mismatch");
    }

    @AfterTest
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
