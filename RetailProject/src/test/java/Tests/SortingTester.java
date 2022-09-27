package Tests;

import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import PageObjects.CompanyLandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SortingTester extends BaseRetailTest {
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
		public void testSorting() throws InterruptedException {
			test = extent.createTest("Test sorting");
		    Thread.sleep(500);
		    
		    // Open the landing page
			CompanyLandingPage landingPage = new CompanyLandingPage(driver);
			Thread.sleep(5000);
			
			// Click on Companies from the left pane to list all Companies 
			landingPage.companies().click();
			test.pass("Clicked companies");
			Thread.sleep(5000);
			
			// Click on the "Name" column header to sort the list in descending order 
			landingPage.name().click();
			test.pass("Clicked name option");
			Thread.sleep(3000);
			
			// Fetch the names of all the companies displayed in the Table
			List<String> names = landingPage.getNamesFromTable();
			test.pass(names.toString());
			
			// Verify that they are all sorted in descending order
			assertTrue(isSortedDescending(names));
			
		}
		
		@AfterMethod
		public void close() {
			driver.close();
			test.pass("Browser closed");
			test.info("Sorting testcase completed");
			extent.flush();


		}
	
		// https://www.baeldung.com/java-check-if-list-sorted
		public boolean isSortedDescending(List<String> listOfStrings) {
		    if (listOfStrings == null || listOfStrings.size() == 0 || listOfStrings.size() == 1) {
		        return true;
		    }

		    Iterator<String> iter = listOfStrings.iterator();
		    String current, previous = iter.next();
		    while (iter.hasNext()) {
		    	previous = previous.toLowerCase().replaceAll("\\s+","");
		        current = iter.next();
		        current = current.toLowerCase().replaceAll("\\s+","");
//		        System.out.println(previous);
//		        System.out.println(current);
//		        System.out.println(previous.toLowerCase().compareTo(current.toLowerCase()));
		        if (previous.toLowerCase().compareTo(current.toLowerCase()) < 0) {
		            return false;
		        }
		        previous = current;
		    }
		    return true;
		}

	}
		

