package com.N11.step_definations;

import com.N11.pages.Locations;
import com.N11.utilities.ConfigurationReader;
import com.N11.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static com.N11.utilities.Driver.driver;
import static com.N11.utilities.Methods.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class StepDefinationN11 {

    Actions act = new Actions(driver);

    Locations lct = new Locations();
    Logger logger = LogManager.getLogger();


    @Given("^the user navigates to home page and verify it$")
    public void theUserNavigatesToHomePageAndVerifyIt() {

        Driver.get().navigate().to(ConfigurationReader.get("url"));
        assertTrue("Verify the main page address is correct", Driver.get().getCurrentUrl().contains("https://www.n11.com/"));
        waitForPageToLoad(5);

        if (Locations.isElementPresent(lct.locationClose)) {
            lct.locationClose.click();
            logger.info("location pop-up closed");
        } else {
            logger.error("Location pop-up DOES NOT shown up");
        }


    }

    @And("the user should click giris yap button")
    public void theUserShouldClickGirisYapButton() {

        waitFor(1);
        lct.signInButton.click();

    }

    @And("the user should navigate login page and verify it")
    public void theUserShouldNavigateLoginPageAndVerifyIt() {

        Driver.get().navigate().to(ConfigurationReader.get("url2"));
        assertTrue("Verify the login page address is correct", Driver.get().getCurrentUrl().contains("https://www.n11.com/giris-yap"));
        waitForPageToLoad(5);

    }

    @And("the user should enter login credentials")
    public void theUserShouldEnterLoginCredentials() {

        lct.eMailField.sendKeys(ConfigurationReader.get("username"));
        lct.pwField.sendKeys(ConfigurationReader.get("password"));
        lct.grsYap.click();
        waitForPageToLoad(10);

    }

    @And("the user should logout the account")
    public void theUserShouldLogoutTheAccount() {

        act.moveToElement(lct.myAccount).pause(1000).build().perform();
        waitFor(2);
        lct.logOutButton.click();
    }


    @And("the user should search keyword")
    public void theUserShouldSearchKeyword() {


        lct.searchBar.sendKeys("pioner");
    }

    @And("the user display the search results in Browser")
    public void theUserDisplayTheSearchResultsInBrowser() {

        waitFor(2);
        Assert.assertTrue(lct.displaySearch.getText().contains("pioner"));
        waitFor(2);
    }

    @And("Include screenshot images from successful")
    public void includeScreenshotImagesFromSuccessful() {
        waitFor(2);

        if (lct.displaySearch.isDisplayed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            logger.info("Screen Shot Successfull: " + OutputType.BYTES);
        }
        else{

            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            logger.info("Screen Shot Fail: " + OutputType.BYTES);
        }
        waitFor(1);
        lct.searchButton.click();

    }

    @And("the user enter wrong e-mail")
    public void theUserEnterWrongEMail() {
        if(lct.eMailField.isEnabled());{

            lct.eMailField.clear();
        }
        lct.eMailField.click();
        lct.eMailField.sendKeys(ConfigurationReader.get("username2"));
        lct.pwField.click();
        lct.pwField.sendKeys(ConfigurationReader.get("password"));
        lct.grsYap.click();
        String checkMessage = lct.errorText.getText();
        logger.info("Error Message Receive Successfully = " + checkMessage);
        Assert.assertEquals("E-posta", "E-posta adresiniz veya şifreniz hatalı",checkMessage);
        waitForPageToLoad(10);

    }

    @And("the user enter wrong password")
    public void theUserEnterWrongPassword() {

        lct.eMailField.sendKeys(ConfigurationReader.get("username"));
        lct.pwField.click();
        lct.pwField.sendKeys(ConfigurationReader.get("password2"));
        lct.grsYap.click();
        String checkMessage = lct.errorText.getText().replaceAll("E-posta", "").trim();
        logger.info("Error Message Receive Successfully = " + checkMessage);
        Assert.assertEquals("E-posta", "E-posta adresiniz veya şifreniz hatalı",checkMessage);
        waitForPageToLoad(10);


    }
}


