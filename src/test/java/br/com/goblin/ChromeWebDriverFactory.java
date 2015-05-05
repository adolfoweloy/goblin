package br.com.goblin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriverFactory implements WebDriverFactory {
	
	@Override
	public WebDriver createWebDriver() {
		System.setProperty("webdriver.chrome.driver", "/home/aeloy/devel/drivers/chromedriver");
		return new ChromeDriver();
	}
	
}
