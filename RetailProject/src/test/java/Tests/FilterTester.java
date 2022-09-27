package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import PageObjects.CompanyLandingPage;

import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FilterTester extends BaseRetailTest {

	// Create first WebDriver reference.
	WebDriver driver;

	@Parameters({ "Browser" })
	@BeforeTest
	public void openBrowser(String browser) {
		if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
	}

	@Parameters({ "URL" })
	@BeforeTest(dependsOnMethods = { "openBrowser" })
	public void getURL(String URL) throws InterruptedException {

		driver.get(URL);
		Thread.sleep(500);
	}

	@Test
	public void selectPendingFilterInLiveCustomers() throws InterruptedException {
		test = extent.createTest("Testing for filter based on pending");
		Thread.sleep(500);
		// Open Page
		CompanyLandingPage landingPage = new CompanyLandingPage(driver);
		Thread.sleep(5000);
		// Select Live Companies from the left pane
		landingPage.live().click();
		Thread.sleep(5000);
		
		// Click on the Filter option
		landingPage.filter().click();
		test.pass("Clicked filter option");
		
		// Apply Filter based on status field
		landingPage.selectField().click();
		test.pass("Clicked select field option");
		Thread.sleep(5000);
		landingPage.statusField().click();
		test.pass("Clicked status field");
		landingPage.selectValue().click();
		test.pass("Clicked select value option");
		
		//Select "Pending" as the status filter and click "apply filters"
		landingPage.pendingValue().click();
		test.pass("Clicked pending value option");
		landingPage.applyFilters().click();
		test.pass("Clicked apply filters option");
		Thread.sleep(3000);
		
		//Verify that no rows are returned
		assertTrue(landingPage.empty().isDisplayed());

	}
	@AfterMethod
	public void close() {
		driver.close();
		test.pass("Browser closed");
		test.info("selectPendingFilterInLiveCustomers testcase completed");
		extent.flush();


	}

	
}