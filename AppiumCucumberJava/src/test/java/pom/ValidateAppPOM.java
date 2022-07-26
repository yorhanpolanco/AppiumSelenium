package pom;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ValidateAppPOM {
	
	AndroidDriver driver;
	
	@FindAll({@FindBy(xpath ="//android.widget.LinearLayout[@resource-id='com.ba.universalconverter:id/keypad']/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button" )})
	public List <WebElement> cellKeyboard;
	
	
	@FindBy(id ="com.ba.universalconverter:id/from_units_spinner" )
	public WebElement from_unit;
	
	
	@FindBy(id ="com.ba.universalconverter:id/to_units_spinner" )
	public WebElement to_unit;
	
	@FindBy(id = "com.ba.universalconverter:id/source_value")
	public WebElement valueToConverted;
	
	
	@FindAll({@FindBy(id ="com.ba.universalconverter:id/select_unit_spinner_menu_name" )})
	public List <WebElement> allUnits;

	
	@FindBy(id = "com.ba.universalconverter:id/target_value")
	public WebElement convertedValue;
	
	@FindBy(xpath = "//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public WebElement options;
	
	@FindAll({@FindBy(id ="com.ba.universalconverter:id/drawerCategoryName" )})
	public List <WebElement> allConversions;
		
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.ba.universalconverter:id/action_bar']/android.widget.TextView")
	public WebElement conversionType;
	
	
	@FindBy(id ="com.ba.universalconverter:id/action_search" )
	public WebElement loupe;
	
	@FindBy(id ="com.ba.universalconverter:id/search_src_text" )
	public WebElement searchField;
	
	@FindAll({@FindBy(id ="com.ba.universalconverter:id/search_results_item_name" )})
	public List <WebElement> listfind;
	
	
	
	public ValidateAppPOM(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	

	
	public boolean applicationOpened() {
		return from_unit.isDisplayed()&&to_unit.isDisplayed();
	}	

	
	public boolean selectConversionUnit(WebElement unit  ,List<WebElement> allUnits,String unitToSelect) {
		String selected;
		unit.click();
		selectItemFromList(allUnits,unitToSelect);	
		selected=unit.findElement(By.id("com.ba.universalconverter:id/select_unit_spinner")).getText();

		return selected.equals(unitToSelect)?true:false;
	}
	
	
	public boolean valueToConvert(List<WebElement> list, String typedNumber, WebElement converted) {
		
		String number=typedNumber;
		String digits;
		String inserted;
		
		for (int i = 0;i < number.length(); i++){
		    digits=String.valueOf(number.charAt(i));
		    
			Duration.ofSeconds(2);	
		selectItemFromList(list,digits);
		}
		inserted=converted.getText();
		
		return inserted.equals(number)?true:false;
	}
	
	public void selectItemFromList(List <WebElement> list, String value) {
		
		for(WebElement element:list) {
			
		
			Duration.ofSeconds(5);
			if(element.getText().equals(value)) {
				element.click();
				break;
			}
		}
	}
	
	public boolean validateConvertedValue(String expectedResult ) {
		String value=convertedValue.getText();
		
		
		return value.equals(expectedResult)?true:false;
	}
	
	public void clickOptions() {
		options.click();
	}

	
	public boolean changeConversionType(String conversion) {
		loupe.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(searchField));
		//searchField.
		searchField.sendKeys(conversion);
		driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

		selectItemFromList(listfind,conversion);
		
		return conversion.equals(conversionType.getText())?true:false;
	}

	
	
}
