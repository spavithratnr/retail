package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;

import PageObjects.CompanyLandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FilterTesterSecond extends BaseRetailTest{
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
	
	@Test(dataProvider = "placeName")
public void selectHeadquarterFilterInRejectedCustomers(String place) throws InterruptedException {
	test = extent.createTest("Testing for filters based on headquarters");
Thread.sleep(500);

	// Open the page
	CompanyLandingPage landingPage = new CompanyLandingPage(driver);
	Thread.sleep(5000);
	
	// Select "Rejected" companies link from the left pane
	landingPage.rejected().click();
	Thread.sleep(5000);
	
	// Click on the Filter option
	landingPage.filter().click();
	test.pass("Clicked filter option");
	landingPage.selectField().click();
	test.pass("Clicked select field option");
	Thread.sleep(5000);
	
	// Apply Filter based on "Headquarter" field
	landingPage.headquarterField().click();
	test.pass("Clicked headquarter field");
	landingPage.dropdownField().click();
	test.pass("Clicked dropdown field");
	landingPage.containsField().click();
	test.pass("Clicked contains option");
	landingPage.enterText().click();
	test.pass("Clicked text field");
	
	//Apply filter based on the "place" data
	landingPage.enterText().sendKeys(place);
	test.pass("Entered place");
	landingPage.applyFilters().click();
	test.pass("Clicked apply filters option");
	Thread.sleep(3000);
	
	// Verify that in the first row returned, the filtered "place" is available
	assertTrue(StringUtils.containsIgnoreCase(landingPage.firstRow().getText(), place));
}
	@AfterMethod
	public void close() {
		driver.close();
		test.pass("Browser closed");
		test.info("Login testcase completed");
		extent.flush();


	}


@DataProvider
public Object[][] placeName() {
	Object[][] data = { { "New York" } };

return data;
}


}
