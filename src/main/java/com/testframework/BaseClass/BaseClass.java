package com.testframework.BaseClass;

import com.testframework.Utilities.LoggerManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    //public static WebDriver driver;
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    protected static Properties properties;
    public static final Logger logger = LoggerManager.getLogger(BaseClass.class);

    //load config
    @BeforeSuite
    public void loadConfig(){
        properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(fis);
            System.out.println("config file loaded..");
            logger.info("config.properties file loaded");
        } catch (IOException e) {
            logger.info("Failed to load config.properties file");
            throw new RuntimeException(e);
        }
    }


    public void setDriver(ThreadLocal<WebDriver> driver) {
        this.driver = driver;
    }

    public static WebDriver getDriver() {
        if(driver == null){
            throw new IllegalStateException("Webdriver not initialized");
        }
        return driver.get();
    }

    //lanuch browser
    @BeforeMethod
    @Parameters({"browser"})
    public synchronized void launchBrowser(String browserName){
        switch (browserName) {
            case "chrome":
                driver.set(new ChromeDriver());
                System.out.println(browserName+" launched");
                logger.info("chrome browser launched");
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                System.out.println(browserName+" launched");
                logger.info("firefox browser launched");
                break;
            case "edge":
                driver.set(new EdgeDriver());
                System.out.println(browserName+" launched");
                logger.info("edge browser launched");
                break;
            default:
                System.out.println(browserName+" Not launched. please enter valid browser");
                logger.info("failed to launch browser");
                break;
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get(properties.getProperty("appurl"));
        logger.info("url loaded into browser");
    }

    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            logger.info("browser closed");
            getDriver().quit();
        }
    }

    //exceptions
    //org.openqa.selenium.WebDriverException: Timed out waiting for driver server to stop.
    //org.openqa.selenium.StaleElementReferenceException: stale element reference: stale element not found
    //org.openqa.selenium.NoSuchSessionException: Session ID is null. Using WebDriver after calling quit()?
}
