package Tests;

import static org.testng.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.CompanyLandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTester extends BaseRetailTest{
	
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

	@Test(dataProvider = "searchPerson")
	public void searchFunction(String searchName) throws InterruptedException {
		test = extent.createTest("Testing for searching a person");

		// Open the langing page
		CompanyLandingPage landingPage = new CompanyLandingPage(driver);
		Thread.sleep(5000);
		
		// Click on Pending companies from the left pane
		landingPage.pending().click();
		
		// Enter the search term in the search box and press "Return"
		// key to perform search
		landingPage.search().sendKeys(searchName);
		landingPage.search().sendKeys(Keys.RETURN);
		Thread.sleep(5000);
		test.pass("Searched for a person");
		
		//Verify that the search result contains the searched term
		assertTrue(StringUtils.containsIgnoreCase(landingPage.firstRow().getText(), searchName));

	}
	@AfterMethod
	public void close() {
		driver.close();
		test.pass("Browser closed");
		test.info("searchFunction testcase completed");
		extent.flush();


	}

	@DataProvider
	public Object[][] searchPerson() {

		Object[][] data = { { "enerkem" } };

		return data;

	}

}
