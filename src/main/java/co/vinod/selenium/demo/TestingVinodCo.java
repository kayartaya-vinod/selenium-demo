package co.vinod.selenium.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class TestingVinodCo {
	WebDriver driver;
	
	String name;
	String email;
	String phone;
	
	public TestingVinodCo(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	@BeforeClass
	public static void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/vinodkumar/Documents/__temp/chromedriver");
	}

	@Before
	public void setup() {
		driver = new ChromeDriver();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Parameterized.Parameters
	public static Collection<?> data() {
		return Arrays.asList(new Object[][]{
				{"John doe", "john.doe@example.com", "555 4844 231"},
				{"Jane doe", "jane.doe@example.com", "555 2345 123"},
				{"Ramesh Kumar", "ramesh.kumar@example.com", "984 4731 883"}
			});
	}
	
	
	@Test
	public void testAddContact() {

		driver.get("https://vinod.co/phonebook-webapp/");

		List<WebElement> rows = driver.findElements(By.cssSelector("div > table > tbody > tr"));
		int rowsBefore = rows.size();

		WebElement elem = driver.findElement(By.cssSelector("input#name"));
		elem.sendKeys(name);
		elem = driver.findElement(By.name("email"));
		elem.sendKeys(email);
		elem = driver.findElement(By.name("phone"));
		elem.sendKeys(phone);

		driver.findElement(By.id("btnAdd")).click();
		rows = driver.findElements(By.cssSelector("div > table > tbody > tr"));
		int rowsAfter = rows.size();

		Assert.assertEquals(rowsBefore + 1, rowsAfter);

	}

}
