package br.com.lunchteam.tests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.lunchteam.core.BaseTest;
import br.com.lunchteam.core.ConnectionFactory;
import br.com.lunchteam.core.DSL;
import br.com.lunchteam.page.BasePage;

public class ResetPassword extends BaseTest {
	
	private static WebDriver driver;
	private DSL dsl;
	BasePage page = new BasePage();
	ConnectionFactory connection = new ConnectionFactory();

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
	public void resetANewPassword() throws SQLException {
		dsl.writeEmailLogin("email", "resetpassword@automatedtest.com");
		dsl.clickBtnLogin();
		waiting(2000);
		driver.findElement(By.linkText("Forgot your Password?")).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBe(By.cssSelector("strong"), "Check your email to reset your password."));
		driver.findElement(By.cssSelector(".lb-content > .bt")).click();
		
		ConnectionFactory conn = new ConnectionFactory();
		Statement st = conn.getConnection().createStatement();
		System.out.println(ConnectionFactory.status); // imprime o valor de status

		st.executeQuery(
				"select * from projectxdbnew.user where email = 'newuser@automatedtest.com' and status_reg = 'W'");

		ResultSet rs = st.getResultSet();
		
	}
	
}
