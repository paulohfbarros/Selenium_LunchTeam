package br.com.lunchteam.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

	private static WebDriver driver;

	public void click(By by) {
		driver.findElement(by).click();
	}
	
	public void waiting(long tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitElementDeliveryAddress() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div > .btn:nth-child(1)")));
		driver.findElement(By.cssSelector("div > .btn:nth-child(1)")).click();
	}
	
	public void waitElementName(String nome) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(nome)));
		
	}

}
