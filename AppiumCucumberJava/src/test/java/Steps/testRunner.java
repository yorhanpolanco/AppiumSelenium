package Steps;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",glue = {"Steps"},
monochrome=true,
//plugin={"pretty","html:target/HtmlReports"}
//plugin={"pretty","json:target/JSONReports/report.json"}
plugin={"json:target/cucumber.json"},
tags="@conversions"
)
public class testRunner {

}
