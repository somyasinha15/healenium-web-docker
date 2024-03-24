package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SharedResource {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver;

		System.out.println("******* - Start of Scenario - *******");
		WebDriverManager.chromedriver().setup();

		WebDriver chromedriver = new ChromeDriver();
		// declare delegate
		// create Self-healing driver
		driver = SelfHealingDriver.create(chromedriver);

		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		System.out.println("Before Click");
		driver.findElement(By.xpath("//a[text()='Gmail']")).click();
		System.out.println("After Click");

		System.out.println("*******--End of Scenario--*******");

		driver.quit();

	}
}