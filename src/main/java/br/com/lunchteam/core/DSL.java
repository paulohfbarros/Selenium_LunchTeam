package br.com.lunchteam.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
//	*** FIELDS ***

	public void writeEmailLogin(String id_field, String text) {
		driver.findElement(By.id(id_field)).sendKeys(text);
	}
	
	public void writePasswordLogin(String id_field, String text) {
		driver.findElement(By.id(id_field)).sendKeys(text);
	}
	
//	*** BUTTONS ***

	public void clickBtnNext() {
		driver.findElement(By.cssSelector("form:nth-child(2) > .bt")).click();
	}	
	
	public void clickBtnLogin() {
		driver.findElement(By.cssSelector("form:nth-child(2) > .bt")).click();
	}
	
	public void clickLogout() {
		driver.findElement(By.cssSelector("li:nth-child(11) b")).click();
	}
	
//	*** GET TEXT ***
	
	public String getTextMessageTM() {
		return driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div/div/h3")).getText();
	}
	
	public String getTextMessageAlert() { 
		return driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div[2]/span/strong")).getText();
	}
}
