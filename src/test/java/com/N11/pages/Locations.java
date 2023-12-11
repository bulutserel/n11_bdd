package com.N11.pages;

import com.N11.utilities.Driver;
import com.N11.utilities.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locations {
    public Locations() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//a[@class='btnSignIn']")
    public WebElement signInButton;

    @FindBy (id = "email")
    public WebElement eMailField;

    @FindBy(id = "password")
    public WebElement pwField;

    @FindBy(id = "loginButton")
    public WebElement grsYap;

    @FindBy(id = "searchData")
    public WebElement searchBar;

    @FindBy(xpath = "//div[@class='myAccount myAccountElliptical']")
    public WebElement myAccount;

    @FindBy(xpath = "//a[@class='logoutBtn']")
    public WebElement logOutButton;

    @FindBy(xpath = "//a[@alt='pioner']")
    public WebElement displaySearch;

    @FindBy(xpath = "//span [@class=\"iconsSearch\"]")
    public WebElement searchButton;

    @FindBy(id = "myLocation-close-info")
    public WebElement locationClose;

    @FindBy(xpath = "(//div[@class='errorText'])[4]")
    public WebElement errorText;



    //------------------------------------------------###### METHODS ######-------------------------------------------\\
        static Locations lct = new Locations();
        static JavascriptExecutor js = (JavascriptExecutor) Driver.get();

        public static void waitFor(int seconds) {
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public static void waitForPageToLoad(long timeOutInSeconds) {
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                }
            };
            try {
                WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
                wait.until(expectation);
            } catch (Throwable error) {
                error.printStackTrace();
            }

        }
        public static boolean isElementPresent(WebElement element){
            try{
                return element.isDisplayed();
            }catch (NoSuchElementException e){
                return false;
            }

        }
}