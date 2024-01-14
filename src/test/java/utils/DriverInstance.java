package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * Singleton class to create the driver
 */
public class DriverInstance {
    public static WebDriver driver = null;

    /**
     * Initialize the driver
     */
    public static void init(){
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("mac")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        }
        if(osName.contains("win")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        }
        System.setProperty("webdriver.chrome.logfile", "chromedriver.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        ChromeOptions options = new ChromeOptions();
        driver =  new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
    }
    public static void quit(){
        driver.quit();
    }
}
