package commonMethods;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Commons {
    public static WebDriver driver = new ChromeDriver();

    public static void setUp() throws InterruptedException {
    	   try {
    		    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    	        driver.get("https://finance.yahoo.com/");

    	    } catch (Exception e) {
    	        System.out.println("Error in setup: " + e.getMessage());
    	    }
    	}
    	
    
//    public static WebDriver getDriver() {
//        return driver;
//    }

    public void SendKeys(By element, String values) {
        try {
            if (values == null || values.isEmpty() || values.matches(".*[!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>/?].*")) {
                throw new IllegalArgumentException("Invalid input: Contains special symbols");
            }
            WebElement elem = driver.findElement(element);
            elem.sendKeys(values);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public void Click(By element) {
        try {
            WebElement elem = driver.findElement(element);
            elem.click();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public void ExWaits(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException e) {
            System.out.println("Timeout while waiting for element: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while waiting for element: " + e.getMessage());
        }
    }

    public static void IWaits() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
}