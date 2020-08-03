package br.com.lunchteam.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.lunchteam.core.BaseTest;
import br.com.lunchteam.core.DSL;

public class LoginTest extends BaseTest {

	private static WebDriver driver;
	private DSL dsl;

	@Before
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://react-cloudfront-test.lunch.team/");
		dsl = new DSL(driver);
	}

	@After
	public void ends() {
		// driver.quit();
	}

	@Test
	public void loginSuccessfully() {
		dsl.writeEmailLogin("email", "successlogin@automatedtest.com");
		dsl.clickBtnNext();
		waiting(2000);
		dsl.writePasswordLogin("password", "123456");
		dsl.clickBtnLogin();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div > .btn:nth-child(1)")));
		driver.findElement(By.cssSelector("div > .btn:nth-child(1)")).click();

		dsl.clickLogout();

	}

	@Test
	public void loginEmailNotRegisteredYet() {
		dsl.writeEmailLogin("email", "emailnotregistered@automatedtest.com");
		dsl.clickBtnNext();
		waiting(2000);
		Assert.assertEquals("Terms & Conditions and Privacy Policy", dsl.getTextMessageTM());
	}

	@Test
	public void loginDomainNotRegisteredYet() {
		dsl.writeEmailLogin("email", "test@notdomain.com");
		dsl.clickBtnNext();
		waiting(3000);
		Assert.assertEquals("Oh No!", dsl.getTextMessageAlert());

	}

	@Test
	public void loginInvalidPassword() {
		dsl.writeEmailLogin("email", "successlogin@automatedtest.com");
		dsl.clickBtnNext();
		waiting(3000);
		dsl.writePasswordLogin("password", "123");
		dsl.clickBtnLogin();
		waiting(3000);
		Assert.assertEquals("Oh No!", dsl.getTextMessageAlert());

	}

}
