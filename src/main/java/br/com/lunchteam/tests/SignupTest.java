package br.com.lunchteam.tests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.lunchteam.core.BaseTest;
import br.com.lunchteam.core.ConnectionFactory;
import br.com.lunchteam.core.DSL;
import br.com.lunchteam.page.BasePage;

public class SignupTest extends BaseTest {

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
	public void signupUserWithoutDomain() {
		dsl.writeEmailLogin("email", "testing@notdomain.com");
		dsl.clickBtnNext();
		waiting(2000);
		String text = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div[2]/span")).getText();
		System.out.println(text);
		Assert.assertEquals("Oh No!\n"
				+ "Hey looks like we don't have your office set up. Chat to a member of our team in Live Chat and sign up in seconds!",
				text);
	}

	@Test
	public void signupNewUser() throws SQLException {
		dsl.writeEmailLogin("email", "newuser@automatedtest.com");
		dsl.clickBtnNext();
		waiting(3000);
		Assert.assertEquals("Terms & Conditions and Privacy Policy", dsl.getTextMessageTM());
		driver.findElement(By.id("terms-and")).click();
		driver.findElement(By.cssSelector(".btn")).click();
		waiting(2000);
		String text = driver.findElement(By.cssSelector(".singup:nth-child(2)")).getText();
		System.out.println(text);
		Assert.assertEquals("Delivery to Automated Tests Company, O'Connell Street Lower, Dublin, Ireland", text);
		driver.findElement(By.name("Delivery")).click();
		driver.findElement(By.cssSelector(".btn")).click();
		waiting(2000);
		driver.findElement(By.name("firstName")).sendKeys("New");
		driver.findElement(By.name("lastName")).sendKeys("User");
		driver.findElement(By.name("mobile")).sendKeys("899793521");
		driver.findElement(By.name("phone")).sendKeys("353624125");
		driver.findElement(By.id("sendDetails")).click();
		waiting(3000);
		Assert.assertEquals("New, check your email to finish your registration.",
				driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div/div/h3")).getText());

		ConnectionFactory conn = new ConnectionFactory();
		Statement st = conn.getConnection().createStatement();
		System.out.println(ConnectionFactory.status); // imprime o valor de status

		st.executeQuery(
				"select * from projectxdbnew.user where email = 'newuser@automatedtest.com' and status_reg = 'W'");

		ResultSet rs = st.getResultSet();

		while (rs.next()) {
			int iduser = rs.getInt("iduser");
			String email = rs.getString("email");
			System.out.printf(iduser + "," + email);
			Statement st1 = conn.getConnection().createStatement();
			st1.executeUpdate("update projectxdbnew.user set status_reg = 'I' where iduser ='" + iduser + "'");

		}

	}

}
