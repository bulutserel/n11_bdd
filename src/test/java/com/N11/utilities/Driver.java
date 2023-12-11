package com.N11.utilities;
import com.N11.step_definations.Hooks;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private Driver() {
    }


    public static RemoteWebDriver driver;
    static Actions actions;
    static Logger logger = LogManager.getLogger();
    public static String browser;

    public static RemoteWebDriver get() {



        if (driver == null) {
            // this line will tell which browser should open based on the value from properties file
            browser = ConfigurationReader.get("browser");
            switch (browser) {
                case "chrome":
                    ChromeOptions option = new ChromeOptions();
                    DesiredCapabilities capabilities1 = DesiredCapabilities.chrome();
                    option.setExperimentalOption("w3c", false);
                    option.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    option.addArguments("disable-translate");
                    option.addArguments("--disable-notifications");
                    option.addArguments("--disable-popup-blocking");
                    Map<String, Object> pref = new HashMap<>();
                    option.setExperimentalOption("prefs", pref);
                    pref.put("profile.default_content_setting_values.notifications", 2);
                    capabilities1.setCapability(ChromeOptions.CAPABILITY, option);
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(option);
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;

                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;

            }
        }

        return driver;
    }


    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}