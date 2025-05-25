package com.testframework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    public WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    private By dashBoard = By.xpath("//h6[text()='Dashboard']");
    private By admin = By.xpath("//span[text()='Admin']");
    private By userDropdown = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private By logout = By.xpath("//a[text()='Logout']");
    private By adminTab = By.xpath("//h6[text()='User Management']");
    private By loginText = By.xpath("//h5[text()='Login']");

    //method to check dashBoard displays
    public boolean isDashBoardDisplays(){
        boolean isDisplayed = driver.findElement(dashBoard).isDisplayed();
        if(isDisplayed == true){
            System.out.println("Dashboard displayed");
            return true;
        }
        else{
            System.out.println("Dashboard not displayed");
            return false;
        }
    }

    //method to check admin tab displays
    public boolean checkAdminDisplays(){
        boolean isAdminDisplayed = driver.findElement(admin).isDisplayed();
        if(isAdminDisplayed == true){
            System.out.println("Admin displayed");
            return true;
        }
        else{
            System.out.println("Admin not displayed");
            return false;
        }
    }

    //method to click admin tab
    public String clickadminTab(){
        driver.findElement(admin).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String text = driver.findElement(adminTab).getText();
        return text;
    }

    //method to click user dropdown
    public void clickDropdown(){
        driver.findElement(userDropdown).click();
    }

    //method to check logout functionality
    public String clickLogout(){
        driver.findElement(userDropdown).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(logout).click();
        String logoutText = driver.findElement(loginText).getText();
        return logoutText;
    }

}
