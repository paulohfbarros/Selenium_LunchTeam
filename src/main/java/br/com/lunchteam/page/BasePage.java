package br.com.lunchteam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
	
	private static WebDriver driver;
	
	public void click(By by) {
		driver.findElement(by).click();
	}
	
	public void clickId(String id) {
		driver.findElement(By.id(id)).click();;
	}

}
