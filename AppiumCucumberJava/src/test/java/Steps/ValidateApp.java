package Steps;

import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.ValidateAppPOM;

public class ValidateApp {

	
	static AndroidDriver driver;
	ValidateAppPOM screen;

	public void open() throws Exception {
	 //System.out.println("Inside Step - User has the App open");
	 DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Pixel");
		cap.setCapability("udid", "emulator-5554");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("appPackage", "com.ba.universalconverter");
		cap.setCapability("appActivity", "com.ba.universalconverter.MainConverterActivity");
		
		URL url= new URL("http://127.0.0.1:4723/wd/hub");
		
		driver = new AndroidDriver(url,cap);
				
		screen = new ValidateAppPOM(driver);
		

	}
	
	
	
	
	@Given("user proceeds to open the application")
	public void user_proceeds_to_open_the_application() throws Exception {
	    open();
	    Assert.assertEquals("The app didn't open",true, screen.applicationOpened());
	}
	
	@When("the user selects {string} as the conversion type")
	public void the_user_selects_as_the_conversion_type(String conversion)  {
	    Assert.assertEquals("The selected conversion type is not displayed",true, screen.changeConversionType(conversion));
	}
	

	@And("select {string} as the source unit of measure")
	public void select_as_the_source_unit_of_measure(String oUnit) {
		Assert.assertEquals("The selected unit of measure isn't displayed",true, screen.selectConversionUnit(screen.from_unit,screen.allUnits, oUnit));
	}

	@And("select {string} as the destination unit of measure")
	public void select_as_the_destination_unit_of_measure(String dUnit) {
	    Assert.assertEquals("The selected unit of measure isn't displayed",true, screen.selectConversionUnit(screen.to_unit,screen.allUnits, dUnit));
	}

	@And("insert the {string} to convert")
	public void insert_the_value_to_convert(String value) {
	   Assert.assertEquals("It wasn't the entered value",true, screen.valueToConvert(screen.cellKeyboard,value,screen.valueToConverted));
	}

	@Then("the application shows {string} as the result of the conversion")
	public void the_application_shows_as_the_result_of_the_conversion(String result) {
	    Assert.assertEquals("The conversion doesn't match the expected result",true, screen.validateConvertedValue(result));
	    driver.quit();
	}

}
