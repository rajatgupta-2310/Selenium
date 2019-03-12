package seleniumTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhpTravels {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.goindigo.in/");
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("window.scrollBy(0,700)");
		driver.findElement(By.xpath("//*[@id='bookFlightTab']/form/div[2]/span[1]/label")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		driver.findElement(By.xpath("//*[@id='bookFlightTab']/form/div[3]/div[1]/div[1]/div/div/input")).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT,Keys.END),"CCU");
		driver.findElement(By.xpath("//*[@id='bookFlightTab']/form/div[3]/div[1]/div[1]/div/div/input")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//*[@id='bookFlightTab']/form/div[3]/div[1]/div[2]/div/div/input")).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT,Keys.END),"DEL");
		driver.findElement(By.xpath("//*[@id='bookFlightTab']/form/div[3]/div[1]/div[2]/div/div/input")).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("4"))).click();
		
		driver.findElement(By.xpath("//*[@id='bookFlightTab']/form/div[5]/div[1]/div[1]/input")).click();
		driver.findElement(By.xpath("//*[@id='bookFlightTab']/form/div[5]/div[1]/div[2]/ul/li[1]/div/button[2]")).click();
		driver.findElement(By.xpath("//*[@id='bookFlightTab']/form/div[5]/div[1]/div[2]/div/button")).click();
		
		driver.findElement(By.xpath("//*[@id='bookFlightTab']/form/div[7]/div[6]/button")).click();
	
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='flightSelectMount']/div/div[1]/div/div/div/div[1]/div[1]/div/div/div[1]/div/button[2]"))).click();
		
		String fltno = driver.findElement(By.xpath("//span[contains(@class,'flightNo')]")).getText();
		System.out.println(fltno);
		
		driver.findElement(By.xpath("//*[@id='headerLogo']/img")).click();
		
		driver.close();
		
	}

}
