package br.com.goblin;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.goblin.domain.supplier.Supplier;

public class LiabilitiesCreationStep {
	
	private WebDriver driver;
	
	public LiabilitiesCreationStep(WebDriver  chromeDriver) {
		driver = chromeDriver;
	}

	@Given("I am registering a new liability for a supplier")
	public void newSupplier() {
		driver.get("http://localhost:8080/goblin/liabilities.xhtml");
	}
	
	@When("I type supplier data, due date and average payment value")
	public void configureLiability() {
		
	}
	
	@Then("I would like to see this liability within a list ordered by due date")
	public void saveSettings() {
	}
	
	
}