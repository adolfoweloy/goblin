package br.com.goblin;

import org.openqa.selenium.WebDriver;

public enum DriverFactory {
	
	CHROME(new ChromeWebDriverFactory()), 
	HTML_UNIT(null);
	
	private WebDriverFactory factory;
	
	private DriverFactory(WebDriverFactory factory) { this.factory = factory; }
	
	public WebDriver getWebDriver() {
		return factory.createWebDriver();
	}
}
