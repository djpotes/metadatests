package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Utilities for all types of waits
 */
public class Waits {
    private WebDriver driver = null;

    public Waits(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Sleep the tests
     * @param millis Milliseconds to sleep
     */
    public static void sleep(long millis){
        try{
            Thread.sleep(millis);
        }
        catch(InterruptedException ie){
        }
    }
}
