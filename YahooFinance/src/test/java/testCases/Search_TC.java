package testCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.Search_Page;

public class Search_TC extends Search_Page {

	@BeforeTest
	public void Launch() throws InterruptedException {
		Search_Page.setUp();
	}

	   @Test(priority = 0, dataProvider = "SerachData", description = "Searching for stock symbol")
	    public void search(String symbol) {
	        try {
	            new Search_Page().EnterSymbols(symbol);
	            Search_Page.IWaits();
	        } catch (Exception e) {
	            System.out.println("Error in search test: " + e.getMessage());
	        }
	    }

	    @Test(priority = 1, dataProvider = "CompanyName", dependsOnMethods = {"search"}, description = "Verifying the name of the stock in suggestion")
	    public void VerifyStockName(String companyName) {
	        try {
	            new Search_Page().VerifyStockName(companyName);
	            Search_Page.IWaits();
	        } catch (Exception e) {
	            System.out.println("Error in verifying stock name: " + e.getMessage());
	        }
	    }

	    @Test(dependsOnMethods = {"VerifyStockName"}, description = "Verify price of the stock is >200 in stock details page")
	    public void VerifyStockPrice() {
	        try {
	            new Search_Page().VerifyNaviAndStockPrice();
	        } catch (Exception e) {
	            System.out.println("Error in verifying stock price: " + e.getMessage());
	        }
	    }
	    @AfterTest
	    public void Quit() {
	    	driver.quit();
	    }

	    @DataProvider(name = "SerachData")
	    public Object[][] ReadSymbol() {
	        return new Object[][] { {"TSLA"} };//Enter your symbol here
	    }

	    @DataProvider(name = "CompanyName")
	    public Object[][] ReadCmpny() {
	        return new Object[][] { { "Tesla, Inc." } };//Enter your company name here to verify
	    }
	}
