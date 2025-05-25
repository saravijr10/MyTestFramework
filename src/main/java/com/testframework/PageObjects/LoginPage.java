package com.testframework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    //locators
    private By userName = By.name("username");
    private By passWord = By.name("password");
    private By clickSubmit = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//p[text()='Invalid credentials']");

    //method to enter username
    public void enterUsername(String username){
        driver.findElement(userName).sendKeys(username);
    }

    //method to enter password
    public void enterPassword(String password){
        driver.findElement(passWord).sendKeys(password);
    }

    //method to click submit button
    public void clickSubmit(){
        driver.findElement(clickSubmit).click();
    }

    //method to get error message
    public String getErrorMessage(){
        String errmessage = driver.findElement(errorMessage).getText();
        return errmessage;
    }

    //login method
    public void loginFunction(String username, String password){
        driver.findElement(userName).sendKeys(username);
        driver.findElement(passWord).sendKeys(password);
        driver.findElement(clickSubmit).click();
    }

}
