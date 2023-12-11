package com.N11.step_definations;
import com.N11.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        Driver.get().manage().window().maximize();
        System.out.println("WEB TEST is starting... ");
        System.out.println("SCENARIO NAME : " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {


        if (scenario.isFailed()) {
            System.err.println("SCENARIO IS FAILED");
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        } else {

            System.out.println("SCENARIO PASSED");
        }

        Thread.sleep(2000);
        Driver.closeDriver();

    }
}