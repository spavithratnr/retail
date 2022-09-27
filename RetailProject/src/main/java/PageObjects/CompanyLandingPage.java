package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanyLandingPage {

	WebDriver driver;

	public CompanyLandingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	/*
	 * @FindBy(xpath="//i[text()='chevron_right']") WebElement rightArrow;
	 * 
	 * public WebElement rightArrow() {
	 * 
	 * return rightArrow; }
	 */
	@FindBy(xpath = "//span[@title ='Pending']")
	WebElement pending;

	@FindBy(xpath = "//input[@class =\"ember-text-field ember-view js-beta-search-bar-input c-beta-search-bar__input\"]")
	WebElement search;

	@FindBy(xpath = "//tbody[@class=\"l-table-frame-body\"]/tr[1]")
	WebElement firstRow;

	@FindBy(xpath = "//span[@class='c-beta-filter-button__text']        ")
	WebElement filter;

	@FindBy(xpath = "//span[@class='c-beta-select__value']")
	WebElement selectField;

	@FindBy(xpath = "//li[@class=\"c-form-beta-select-dropdown-content__item\"]/span[text()='status']")
	WebElement statusField;

	@FindBy(xpath = "(//div[starts-with(@class, 'js-ember') and contains(@class,'c-beta-select__input')]/span[@class='c-beta-select__value'])[3]")
	WebElement selectValue;

	@FindBy(xpath = "//li[starts-with(@class,'c-form-beta-select-dropdown-content__item')]/span[text()='Pending']")
	WebElement pendingValue;

	@FindBy(xpath = "//span[text()='Apply filters']")
	WebElement applyFilters;

	@FindBy(xpath = "//span[@title ='Live']")
	WebElement live;
	
	@FindBy(xpath = "//h1[@class=\"c-layout-collection-empty__title\"]")
	WebElement empty;
	
	@FindBy(xpath = "//span[@title='Rejected']")
	WebElement rejected;
	
	@FindBy(xpath = "//li[@class=\"c-form-beta-select-dropdown-content__item\"]/span[text()='headquarter']")
	WebElement headquarterField;
	
	@FindBy(xpath = "(//i[@class=\"material-icons c-beta-select__icon \"])[2]")
	WebElement dropdownField;

	
	@FindBy(xpath = "//li[@class=\"c-form-beta-select-dropdown-content__item\"]/span[text()='contains']")
	WebElement containsField;
	
	@FindBy(xpath = "//div[@class=\"l-beta-input ember-view\"]/input")
	WebElement enterText;
	
	@FindBy(xpath = "//span[@title=\"Companies\"]")
	WebElement companies;
	
	@FindBy(xpath = "//span[text()=\"name\"]")
	WebElement name;








	public WebElement pending() {

		return pending;

	}

	public WebElement search() {

		return search;

	}

	public WebElement firstRow() {

		return firstRow;

	}

	public WebElement filter() {

		return filter;

	}

	public WebElement selectField() {

		return selectField;

	}

	public WebElement statusField() {

		return statusField;

	}

	public WebElement selectValue() {

		return selectValue;

	}

	public WebElement pendingValue() {

		return pendingValue;

	}

	public WebElement applyFilters() {

		return applyFilters;

	}

	public WebElement live() {

		return live;

	}
	public WebElement empty() {

		return empty;

	}
	public WebElement rejected() {

		return rejected;

	}
	public WebElement headquarterField() {

		return 	headquarterField;

	}
	public WebElement dropdownField() {

		return 	dropdownField;

	}

	public WebElement containsField() {

		return 	containsField;

	}
	public WebElement enterText() {

		return 	enterText;

	}
	public WebElement companies() {

		return companies;

	}
	public WebElement name() {

		return name;

	}
	
	public List<String> getNamesFromTable() {
		List<String> names = new ArrayList<String>();
		
		List<WebElement> nameCells = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		for (int i=0; i< nameCells.size(); i++) {
			String name = nameCells.get(i).getText();
			names.add(name);
		}
		
		return names;
	}






}
