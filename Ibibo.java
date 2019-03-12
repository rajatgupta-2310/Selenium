package automation.functionaltesting;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Ibibo{
public WebDriver driver=null;
public HashMap<String,String> h = new HashMap<String,String>();
	@BeforeTest
  public void setBrowser () {
	  System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("https://www.goibibo.com");
  }
  
    @Test(priority=0)
	public void readExcel() throws IOException{
    System.out.println(Thread.activeCount());
	   File f= new File("./GoIbibo/GoIbibo.xlsx");
	   FileInputStream Ifs= new FileInputStream(f);
	   XSSFWorkbook w=new XSSFWorkbook(Ifs);
	   XSSFSheet s=w.getSheet("Sheet1");	  
	   int rcount=s.getLastRowNum();
	   System.out.println(rcount);
	   String key=null;
	   String value=null;
	   for(int i=0;i<=rcount;i++){
		   XSSFRow r=s.getRow(i);
		   key=r.getCell(0).getStringCellValue();
		   value=r.getCell(1).getStringCellValue();
		   h.put(key, value);
	   }
	   w.close();
   }
	@Test(priority=1)
   public void MainTestGoIbibo( ) throws Exception{
		System.out.println(Thread.activeCount());
	   driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']")).sendKeys(h.get("source"));
	   driver.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li[1]")).click();
	   driver.findElement(By.xpath("//input[@id='gosuggest_inputDest']")).sendKeys(h.get("destination"));
	   driver.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li[1]")).click();
	   driver.findElement(By.xpath("//input[@placeholder='Departure']")).click();
	  WebElement MnthCpt= driver.findElement(By.xpath("//div[@class='DayPicker-Caption' and @role='heading']"));
	  while(!MnthCpt.getText().trim().contains(h.get("departuremonth"))){
		  driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
	  }
	  if(MnthCpt.getText().trim().contains(h.get("departuremonth")))
	  {
		  List<WebElement> Deprtd=driver.findElements(By.xpath("//div[@class='DayPicker-Day']"));
		  for(WebElement a : Deprtd){
			  System.out.println(a.getAttribute("aria-label"));
			  if(a.getAttribute("aria-label").contains(h.get("depart"))){
				  a.click();
				  break;
			  }
		  }
	  }
	  System.out.println("Depart Date Selection Passed");
	  driver.findElement(By.xpath("//input[@placeholder='Return']")).click();
	  WebElement RMnthCpt2=driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"));
	  while(!RMnthCpt2.getText().trim().contains(h.get("returnmonth"))){
		  driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		  System.out.println("Next button click passed");
	  }
	  if(RMnthCpt2.getText().trim().contains(h.get("returnmonth"))){
		  Thread.sleep(3000);
		  List<WebElement> d=driver.findElements(By.xpath("//div[@class='DayPicker-Day']"));
		  for(WebElement f:d){
			 System.out.println(f.getAttribute("aria-label"));
			 if(f.getAttribute("aria-label").contains(h.get("return"))){
				 System.out.println("if paassed");
				 f.click();
				 break;
			 }
		  }   
	  }
	  System.out.println("Return Date Selection passed/n");
	   driver.findElement(By.xpath("//div[@id='pax_link_common']")).click();
	   WebElement adult=driver.findElement(By.xpath("//button[@id='adultPaxPlus']"));
	   WebElement child=driver.findElement(By.xpath("//button[@id='childPaxPlus']"));
	   WebElement infant=driver.findElement(By.xpath("//button[@id='infantPaxPlus']"));
	   
	   if(h.get("TypeOfMem").contains("Adults")){
		   for(int i=1;i<Integer.parseInt(h.get("noOfAdultMem"));i++){
			   adult.click();
		   }
	   }
	   else if(h.get("TypeOfMem").contains("Child"))
	    for(int i=1;i<=Integer.parseInt(h.get("noOfChildMem"));i++){
	    	child.click();
	    }
	   else{
		   for(int i=1;i<=Integer.parseInt(h.get("noOfInfant"));i++){
			   infant.click();
		   }
	   }
	  System.out.println("Memebers selection passed");
      
	  WebElement Tclass=driver.findElement(By.xpath("//select[@id='gi_class']"));
	  Tclass.click();
	  Select s= new Select(Tclass);
	  s.selectByVisibleText(h.get("TravelClass"));
	  
	  System.out.println("Travel Class selection passed");
	  driver.findElement(By.xpath("//button[@id='gi_search_btn']")).click();
	  
	  System.out.println("Search button click passed");
	  
	}
	@AfterTest
   public void closeBrowser() throws InterruptedException{
	   Thread.sleep(50000);
	   driver.quit();
   }
    
}
