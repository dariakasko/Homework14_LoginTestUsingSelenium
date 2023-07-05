package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.nio.Buffer;

public class LoginTest {
    static WebDriver chromeDriver;

    @BeforeAll
    public static void init() {
        chromeDriver = new ChromeDriver();
    }
    @BeforeEach
    public void maximizeWindow() {
        chromeDriver.manage().window().maximize();
    }

    @BeforeEach
    public void openStartPage() {
        chromeDriver.get("https://the-internet.herokuapp.com/login");
    }
    @Test
    public void verifyLogin() {
        enterUserName("tomsmith");
        enterUserPassword("SuperSecretPassword!");
        clickOnLoginButton();
        WebElement afterLoginPage = chromeDriver.findElement(By.xpath("//div[contains(text(),\"You logged into a secure area!\")]"));
        Assertions.assertTrue(afterLoginPage.isDisplayed());
    }
    @Test
    public void verifyLoginNegative() {
        enterUserName("Dasha");
        enterUserPassword("SuperSecretPassword!");
        clickOnLoginButton();
        WebElement welcomPage = chromeDriver.findElement(By.xpath("//div[contains(text(),\"Your username is invalid!\")]"));
        Assertions.assertTrue(welcomPage.isDisplayed());
    }

    public void enterUserName(String userName) {
        WebElement userNameField = chromeDriver.findElement(By.xpath("//input[@id='username']"));
        //other variant of XPath:
        //WebElement userNameField = chromeDriver.findElement(By.xpath("input[@id='username']"));
        //by css selector
        //WebElement userNameField = chromeDriver.findElement(By.cssSelector("#userName"));
        userNameField.clear();
        userNameField.sendKeys(userName);
    }

    public void enterUserPassword(String password) {
        WebElement userPassword = chromeDriver.findElement(By.xpath("//input[@id=\"password\"]"));
        userPassword.clear();
        userPassword.sendKeys(password);
    }
    public void clickOnLoginButton() {
        WebElement loginButton = chromeDriver.findElement(By.xpath("//button[@class='radius']"));
        loginButton.click();
    }

    @AfterAll
    public static void clear() {
        chromeDriver.quit();
    }
}
