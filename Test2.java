package automationTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//for Test Case www.marshallsptezone.com

public class Test2 
{
	public static String prod[] = new String[2];
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.marshallspetzone.com/");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		//Creating Action Builder
		Actions builder = new Actions(driver);
		WebElement ctgry = driver.findElement(By.linkText("BIRD"));
		builder.moveToElement(ctgry).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id="+'"'+"cbp-hrmenu"+'"'+"]/ul/li[4]/div/div/div/div/div[1]/div/div/div/a")).click();
		Thread.sleep(2000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
		int prdCnt = 0,i=1;
		while(prdCnt < 2)
		{
			String prdchk = driver.findElement(By.xpath("//ul[@class='product_list grid row']//li["+ i +"]//div[@class='product-container']//div[@class='button-container']/a")).getAttribute("title");
			if(prdchk.equalsIgnoreCase("add to cart"))
			{
				String product = driver.findElement(By.xpath("//ul[@class='product_list grid row']//li["+ i +"]//div[@class='product-container']//h5[@class='product-name-container']//a[@class='product-name']")).getText();
				System.out.println(product);
				prod[prdCnt] = product;
				driver.findElement(By.xpath("//ul[@class='product_list grid row']//li["+ i +"]//div[@class='product-container']//div[@class='button-container']/a")).click();
				if(prdCnt == 0)
				{
					new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id="+'"'+"layer_cart"+'"'+"]/div[3]/div/span"))).click();
				}
				else
				{
					new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id="+'"'+"layer_cart"+'"'+"]/div[3]/div/a"))).click();
				}
				prdCnt++;
				i++;
			}
			else
			{
				i++;
			}
		}
		
		/*System.out.println("Prod Array");
		for(String p:prod)
		{
			System.out.println(p);
		}*/
		
		//Verifying Cart Details
		i=0;
		WebElement cartTable = driver.findElement(By.id("cart_summary"));
		List <WebElement> cartRow = cartTable.findElements(By.xpath("//tbody//tr//td[@class='cart_description']//p[@class='product-name']"));
		for(WebElement item: cartRow)
		{
			if(prod[i].equals(item.getText()))
			{
				System.out.println("Product "+ i + " : " + item.getText() + " - Passed");
			}
			else
			{
				System.out.println("Product "+ i + " : " + item.getText() + " - Failed");
			}
			i++;
		}
	}
}
