package automationTest;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {
	public static String month1,month2,adult,child,infant;
	public static List <WebElement> dates;
	public static WebDriver driver;
	
	//Depart Date Function
		public void departDate() throws InterruptedException
		{
			String userDate = "13 April 2019";
			String ud[] = userDate.split(" ");
			String date = ud[0];
			String mn = ud[1] + " " + ud[2];
			driver.findElement(By.xpath("//*[@id="+'"'+"searchWidgetCommon"+'"'+"]/div/div[3]/div[1]/div[1]/div/input")).click();
			month1 = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Caption']")).getText();
			month2 = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Caption']")).getText();
			while((!month1.equalsIgnoreCase(mn)) && (!month2.equalsIgnoreCase(mn)))
			{
				Thread.sleep(2000);
				new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'DayPicker-NavButton DayPicker-NavButton--next')]"))).click();
				month1 = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Caption']")).getText();
				month2 = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Caption']")).getText();
			}
			if(month1.equals(mn))
			{
				dates = driver.findElements(By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Body']//div[@class='DayPicker-Day']"));
				for(WebElement day:dates)
				{
					String d[] = day.getText().split("\\r?\\n");
					for(String d1:d)
					{
						
						if(d1.equalsIgnoreCase(date))
						{
							boolean r = false;
							int attempt = 0;
							while(attempt < 2)
							{
								try
								{
									day.click();
									r = true;
									break;
								}
								catch(StaleElementReferenceException e){}
								attempt++;
							}
							return;
						}
					}
				}
			}
			else if(month2.equals(mn))
			{
				dates = driver.findElements(By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Body']//div[@class='DayPicker-Day']"));
				for(WebElement day:dates)
				{
					String d[] = day.getText().split("\\r?\\n");
					for(String d1:d)
					{
						
						if(d1.equalsIgnoreCase(date))
						{
							boolean r = false;
							int attempt = 0;
							while(attempt < 2)
							{
								try
								{
									day.click();
									r = true;
									break;
								}
								catch(StaleElementReferenceException e){}
								attempt++;
							}
							return;
						}
					}
				}
			}
			//return true;
		}
		
		//Return Date Function
		public void returnDate() throws InterruptedException
		{
			String userDate1 = "13 June 2019";
			String ud1[] = userDate1.split(" ");
			String date1 = ud1[0];
			String mn1 = ud1[1] + " " + ud1[2];
			driver.findElement(By.xpath("//*[@id="+'"'+"searchWidgetCommon"+'"'+"]/div/div[3]/div[1]/div[2]/div/input[1]")).click();
			month1 = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Caption']")).getText();
			month2 = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Caption']")).getText();
			while((!month1.equalsIgnoreCase(mn1)) && (!month2.equalsIgnoreCase(mn1)))
			{
				Thread.sleep(2000);
				new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'DayPicker-NavButton DayPicker-NavButton--next')]"))).click();
				month1 = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Caption']")).getText();
				month2 = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Caption']")).getText();
			}
			if(month1.equals(mn1))
			{
				dates = driver.findElements(By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Body']//div[@class='DayPicker-Day']"));
				for(WebElement day:dates)
				{
					String d[] = day.getText().split("\\r?\\n");
					for(String d1:d)
					{
						
						if(d1.equalsIgnoreCase(date1))
						{
							boolean r = false;
							int attempt = 0;
							while(attempt < 2)
							{
								try
								{
									day.click();
									r = true;
									break;
								}
								catch(StaleElementReferenceException e){}
								attempt++;
							}
							return;
						}
					}
				}
			}
			else if(month2.equals(mn1))
			{
				dates = driver.findElements(By.xpath("//div[@class='DayPicker-Month'][2]//div[@class='DayPicker-Body']//div[@class='DayPicker-Day']"));
				for(WebElement day:dates)
				{
					String d[] = day.getText().split("\\r?\\n");
					for(String d1:d)
					{
						
						if(d1.equalsIgnoreCase(date1))
						{
							boolean r = false;
							int attempt = 0;
							while(attempt < 2)
							{
								try
								{
									day.click();
									r = true;
									break;
								}
								catch(StaleElementReferenceException e){}
								attempt++;
							}
							return;
						}
					}
				}
			}
		}
		
		//Traveller Function
		public void traveller() throws InterruptedException
		{
			adult = "1";
			child = "0";
			infant = "0";
			driver.findElement(By.xpath("//*[@id="+'"'+"pax_link_common"+'"'+"]")).click();
			//Checking for Adult Traveller
			WebElement Traveller = driver.findElement(By.xpath("//*[@id="+'"'+"adultPaxBox"+'"'+"]"));
			String ad = Traveller.getAttribute("value");
			//System.out.println(driver.findElement(By.id("adultPaxBox")).getAttribute("value"));
			while(!adult.equals(ad))
			{
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id="+'"'+"adultPaxPlus"+'"'+"]")).click();
				ad = Traveller.getAttribute("value");
			}
			
			//Checking for Child Traveller
			Traveller = driver.findElement(By.xpath("//*[@id="+'"'+"childPaxBox"+'"'+"]"));
			String ch = Traveller.getAttribute("value");
			while(!child.equalsIgnoreCase(ch))
			{
				driver.findElement(By.xpath("//*[@id="+'"'+"childPaxPlus"+'"'+"]")).click();
				ch = Traveller.getAttribute("value");
			}
			
			//Checking for Infant Traveller
			Traveller = driver.findElement(By.xpath("//*[@id="+'"'+"infantPaxBox"+'"'+"]"));
			String in = Traveller.getAttribute("value");
			while(!infant.equalsIgnoreCase(in))
			{
				driver.findElement(By.xpath("//*[@id="+'"'+"infantPaxPlus"+'"'+"]")).click();
				in = Traveller.getAttribute("value");
			}
		}
		
		//Class Function
		public void classFunc()
		{
			String cls = "Economy";
			driver.findElement(By.id("gi_class")).click();
			Select classDD = new Select(driver.findElement(By.id("gi_class")));
			classDD.selectByVisibleText(cls);
		}
		
		//Comparison of Price for Left
		public int compareOnw(int i)
		{
			int result,lp1,lp2;
			String price1 = driver.findElement(By.xpath("//div[@id='onwFltContainer']//div[@class='card fl width100 marginB10']["+i+"]//div[@class='card-block fl width100 padT20 padB15']//span[@class='ico20 fr fn']")).getText();
			String price2 = driver.findElement(By.xpath("//div[@id='onwFltContainer']//div[@class='card fl width100 marginB10']["+(i+1)+"]//div[@class='card-block fl width100 padT20 padB15']//span[@class='ico20 fr fn']")).getText();
			//System.out.println(price1);
			//System.out.println(price2);
			lp1 = price1.length();
			lp2 = price2.length();
			if(lp1 > lp2)
			{
				result = 1;
				//System.out.println(lp1 + " - " + lp2);
				return result;
			}
			else
			{
				result = price1.compareTo(price2);
				return result;
			}
		}
		public int compareRndTrip(int i)
		{
			int result,lp1,lp2;
			String price1 = driver.findElement(By.xpath("//div[@id='retFltContainer']//div[@class='card fl width100 marginB10']["+i+"]//div[@class='card-block fl width100 padT20 padB15']//span[@class='ico20 fr fn']")).getText();
			String price2 = driver.findElement(By.xpath("//div[@id='retFltContainer']//div[@class='card fl width100 marginB10']["+(i+1)+"]//div[@class='card-block fl width100 padT20 padB15']//span[@class='ico20 fr fn']")).getText();
			//System.out.println(price1);
			//System.out.println(price2);
			lp1 = price1.length();
			lp2 = price2.length();
			if(lp1 > lp2)
			{
				result = 1;
				//System.out.println(lp1 + " - " + lp2);
				return result;
			}
			else
			{
				result = price1.compareTo(price2);
				return result;
			}
		}
		
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Jay\\KT\\Learning\\Selenium\\chromedriver_win32_2.41\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("https://www.goibibo.com/");//Step 1 : URL
		driver.get("https://www.goibibo.com/");
		driver.manage().window().maximize();
		
//Test Step 1 : Searching a Flights
		//Input Source and Destination Location
		driver.findElement(By.xpath("//*[@id="+'"'+"gosuggest_inputSrc"+'"'+"]")).sendKeys("Chennai");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id="+'"'+"gosuggest_inputDest"+'"'+"]")).sendKeys("Pune");
		Thread.sleep(2000);
		
		Test obj = new Test();
		//Selecting Depart Date
		obj.departDate();
	
		//Selecting Return Date
		obj.returnDate();
		
		//Selecting Traveller
		obj.traveller();
		
		//Selecting Class
		obj.classFunc();
		
		//Search Flight
		Thread.sleep(2000);
		driver.findElement(By.id("gi_search_btn")).click();
		
//Test Step 2 : Select Flight
		//Test obj = new Test();
		
		//Sort a One Way Flight list in Ascending Order
		Thread.sleep(2000);
		driver.findElement(By.id("sortByPriceOnw")).click();
						
		//Price Comparison Logic for One Way Flights
		boolean loop = true;
		int i = 1;
		int r = obj.compareOnw(i);
		//System.out.println(r);
		while(loop)
		{
			if(r==0)
			{
				i++;
				r = obj.compareOnw(i);
				//System.out.println(r);
			}
			else if(r>0)
			{
				Thread.sleep(2000);
				driver.findElement(By.id("sortByPriceOnw")).click();
				i=1;
				r = obj.compareOnw(i);
			}
			else
			{
				boolean status = driver.findElement(By.xpath("//div[@id='onwFltContainer']//div[@class='card fl width100 marginB10'][1]//div[@class='card-block fl width100 padT20 padB15']//input[@name='o_radio']")).isSelected();
				if(status == false)
				{
					driver.findElement(By.xpath("//div[@id='onwFltContainer']//div[@class='card fl width100 marginB10'][1]//div[@class='card-block fl width100 padT20 padB15']//div[@class='control__indicator']")).click();
				}
				//System.out.println("Sorted");
				loop = false;
			}
		}
		
		//Sort Return Flight List in Ascending Order
		/*boolean exist = driver.findElement(By.xpath("//*[@id="+'"'+"content"+'"'+"]/div/div[2]/div/div[5]/div/i")).isDisplayed();
		if(exist == true)
		{
			driver.findElement(By.xpath("//*[@id="+'"'+"content"+'"'+"]/div/div[2]/div/div[5]/div/i")).click();
		}*/
		//Scrolling Screen 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		
		Thread.sleep(2000);
		driver.findElement(By.id("sortByPriceRet")).click();
		
		//Price Comparison Logic for Return Flights
		loop = true;
		i = 1;
		r = obj.compareRndTrip(i);
		//System.out.println(r);
		while(loop)
		{
			if(r==0)
			{
				i++;
				r = obj.compareRndTrip(i);
				//System.out.println(r);
			}
			else if(r>0)
			{
				Thread.sleep(2000);
				driver.findElement(By.id("sortByPriceRet")).click();
				i=1;
				r = obj.compareRndTrip(i);
			}
			else
			{
				boolean status = driver.findElement(By.xpath("//div[@id='retFltContainer']//div[@class='card fl width100 marginB10'][1]//div[@class='card-block fl width100 padT20 padB15']//input[@name='r_radio']")).isSelected();
				if(status == false)
				{
					driver.findElement(By.xpath("//div[@id='retFltContainer']//div[@class='card fl width100 marginB10'][1]//div[@class='card-block fl width100 padT20 padB15']//div[@class='control__indicator']")).click();
				}
				//System.out.println("Sorted");
				loop = false;
			}
		}
		
		driver.findElement(By.xpath("//div[@id='fltTcktVoucher']//input[@class='button orange fr']")).click();
		
		/*TakesScreenshot sht = ((TakesScreenshot)driver);
		File src = new File("D:\\Test.png");
		src = sht.getScreenshotAs(OutputType.FILE);*/
		
		/*boolean status = driver.findElement(By.xpath("//div[@id='onwFltContainer']//div[@class='card fl width100 marginB10'][1]//div[@class='card-block fl width100 padT20 padB15']//input[@name='o_radio']")).isSelected();
		System.out.println(status);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='onwFltContainer']//div[@class='card fl width100 marginB10'][1]//div[@class='card-block fl width100 padT20 padB15']//div[@class='control__indicator']")).click();
		status = driver.findElement(By.xpath("//div[@id='onwFltContainer']//div[@class='card fl width100 marginB10'][1]//div[@class='card-block fl width100 padT20 padB15']//input[@name='o_radio']")).isSelected();
		System.out.println(status);*/
	}
}
