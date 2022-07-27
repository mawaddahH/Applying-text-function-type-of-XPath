package ass2textfunctionXPath;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromWebDriver {
	public WebDriver driver;

	/**
	 * Set up browser settings and open the application
	 */

	@BeforeTest
	public void setUp() {
		// the path for open WebSite
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\lo0ol\\" + "Downloads\\Compressed\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

		// Navigate to a WebSite
		driver.navigate().to("https://medium.com/");

		// Maximize current window
		driver.manage().window().maximize();
	}

	/**
	 * Test using text() function, Type of XPath
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void LearnEnglishWebSite() throws InterruptedException {

		// ex1 ------- text() function With AND operation
		WebElement textMethod1 = driver
				.findElement(By.xpath("//p[contains(text(),'Help') and contains(@class,'bv b bw bx ho')]"));
		textMethod1.click();
		System.out.println("ex1 DONE Successfully");

		// Delay execution for 3 seconds to see the result carefully.
		Thread.sleep(3000);

		// ex2 ------- text() function with normalize-space() function
		WebElement textMethod2 = driver.findElement(By.xpath("//*[normalize-space(text()) = 'Back to medium.com']"));
		textMethod2.click();
		System.out.println("ex2 DONE Successfully");

		// Delay execution for 3 seconds to see the result carefully.
		Thread.sleep(3000);

		// ex3 ------- basic text() function
		WebElement textMethod3 = driver.findElement(By.xpath("//a[text()='Our story']"));
		textMethod3.click();
		System.out.println("ex3 DONE Successfully");

		// Delay execution for 3 seconds to see the result carefully.
		Thread.sleep(3000);

		// ex4 ------- text() function With child concept
		WebElement textMethod4 = driver
				.findElement(By.xpath("//div[@class='tri-col-item-name']/child::h4[text()='Sarah Cottrell']"));
		textMethod4.click(); // it will open it in a new tab window

		// Delay execution for 3 seconds to see the result carefully.
		Thread.sleep(3000);
		
		
		//Handling multiple windows - https://www.guru99.com/alert-popup-handling-selenium.html
		String MainWindow = driver.getWindowHandle();

		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				
				WebElement textMethod5 = driver
						.findElement(By.xpath("//input[@class='ea lg bn bo bp gj cf lh li ii lj']"));
				textMethod5.sendKeys("MawaddahHanbali");
			
				// Delay execution for 3 seconds to see the result carefully.
				Thread.sleep(3000);
				
				// Closing the Child Window.
				driver.close();
			}
		}
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);
		
		System.out.println("ex4 DONE Successfully");

		// Delay execution for 3 seconds to see the result carefully.
		Thread.sleep(3000);
	}

	/**
	 * Tear down the setup after test completes
	 */
	@AfterTest
	public void terminateBrowser() {
		// Close the browser
		driver.quit();
	}
}
