package com.N11.utilities;
import com.N11.pages.Locations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Methods {
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
    public static WebElement findElement(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(Driver.get());
        wait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(3000))
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}