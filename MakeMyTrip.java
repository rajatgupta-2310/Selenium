package automationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MakeMyTrip 
{
	public static String tripType;
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		
//Step 1: Select Trip Type
		tripType = "round trip";
		for(int i = 1 ;i <= 3; i++)
		{
			String tT = driver.findElement(By.xpath("//div[@class='hp-widget']//div[@class='hp-widget-top']//div[@class='switchBTN']["+ i +"]")).getText();
			if(tT.equalsIgnoreCase(tripType))
			{
				boolean chkd = driver.findElement(By.xpath("//div[@class='hp-widget']//div[@class='hp-widget-top']//div[@class='switchBTN']["+ i +"]/input")).isSelected();
				if(chkd == false)
				{
					driver.findElement(By.xpath("//div[@class='hp-widget']//div[@class='hp-widget-top']//div[@class='switchBTN']["+ i +"]/label")).click();
					break;
				}
			}
		}
		
// Step 2: Enter Mandatory Details to Search Flight
		driver.findElement(By.xpath("//*[@id="+'"'+"hp-widget__sfrom"+'"'+"]")).clear();
		driver.findElement(By.xpath("//*[@id="+'"'+"hp-widget__sTo"+'"'+"]")).clear();
		driver.findElement(By.xpath("//*[@id="+'"'+"hp-widget__sfrom"+'"'+"]")).sendKeys("Chennai");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id="+'"'+"hp-widget__sTo"+'"'+"]")).sendKeys("Pune");
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//*[@id="+'"'+"hp-widget__depart"+'"'+"]")).clear();
		driver.findElement(By.xpath("//*[@id="+'"'+"hp-widget__depart"+'"'+"]")).click();
		
	}
}
