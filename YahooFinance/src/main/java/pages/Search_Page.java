package pages;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import commonMethods.Commons;

public class Search_Page extends Commons{
    By SrchBx = By.id("ybar-sbq");
    By SearchRslt = By.xpath("(//li[@data-test='srch-sym'])[1]");
    By StockName = By.xpath("(//div[contains(@class,'quoteCompanyName')])[1]");
    By StockClick = By.xpath("//li[@data-id='result-quotes-0']");
    By StockPrice = By.xpath("(//span[contains(@data-testid, 'qsp')])[4]");
    By PreviouClose = By.xpath("(//span[@class='value yf-gn3zu3'])[1]");
    By Volume = By.xpath("(//span[@class='value yf-gn3zu3'])[7]");
    SoftAssert softassert=new SoftAssert();

    public void EnterSymbols(String symbol) {
        SendKeys(SrchBx, symbol);
        IWaits();
        try {
            if (driver.findElement(SearchRslt).isDisplayed()) {
                Assert.assertTrue(true);
            }
        } catch (NoSuchElementException e) {
            Assert.fail("No results found for symbol: " + symbol);
        } catch (Exception e) {
            Assert.fail("Unexpected error during symbol search: " + e.getMessage());
        }
    }

    public void VerifyStockName(String companyName) {
        try {
            WebElement stockNameElement = driver.findElement(StockName);
            if (stockNameElement.isDisplayed()) {
                String expStockName = stockNameElement.getText();
                Assert.assertEquals(expStockName, companyName);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Stock name element not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while verifying stock name: " + e.getMessage());
        }
    }

    public void VerifyNaviAndStockPrice() {
    	ExWaits(StockClick);
        try {
            Click(StockClick);
            String priceText = driver.findElement(StockPrice).getText().replaceAll("[^0-9.]", "");
            double decimalValue = Double.parseDouble(priceText);

            if (decimalValue > 200) {
                Assert.assertTrue(true, "Stock price is greater than 200");
                System.out.println("Price of stock: " + decimalValue);
            } else {
                System.out.println("Price of stock: " + decimalValue);              
                softassert.fail("Stock price is less than 200");
               // Assert.fail("Amount is less than 200");
            }

            System.out.println("Previous Close: " + driver.findElement(PreviouClose).getText());
            System.out.println("Volume: " + driver.findElement(Volume).getText());
        } catch (NoSuchElementException e) {
            System.out.println("Stock price or other elements not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing stock price: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error in verifying stock price: " + e.getMessage());
        }
        softassert.assertAll();
    }
}
