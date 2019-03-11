package automation.functionaltesting;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class MarshallsPetZone
{
	public WebDriver driver;
	String prodList[] = new String[2];
	public Map<String,String> input = new HashMap<String,String>();
	@BeforeClass
	public void setProperties()
	{
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
	}
	
	@Test
	public void getInput() throws IOException
	{
		FileInputStream fileInput = new FileInputStream("./ExtFile/MPZInput.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fileInput);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		Row r;
		Cell c1,c2;
		for(int rn = 0; rn <= sheet.getLastRowNum(); rn++)
		{
			r = sheet.getRow(rn);
			for(int cn = 0; cn <= r.getLastCellNum(); cn=cn+2)
			{
				c1 = r.getCell(cn);
				c2 = r.getCell(cn+1);
				if((c1 == null && c2 == null) || (c1.getCellType() == CellType.BLANK && c2.getCellType() == CellType.BLANK))
				{
					continue;
				}
				if(c1.getCellType() == CellType.STRING && c2.getCellType() == CellType.STRING)
				{
					input.put(c1.getStringCellValue(),c2.getStringCellValue());
				}
			}
		}
		wb.close();
		fileInput.close();
	}
	
	@Test
	public void launchBrowser()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test(dependsOnMethods = {"launchBrowser"})
	public void selectCategory()
	{
		driver.get("https://www.marshallspetzone.com/");
		driver.findElement(By.linkText(input.get("Category"))).click();
		driver.findElement(By.linkText(input.get("Subcategory"))).click();
	}
	@Test(dependsOnMethods = {"selectCategory"})
	public void  selectProducts()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
		
		int prdcnt = 0,i=1;
		while(prdcnt < 2)
		{
			String prdavl = driver.findElement(By.xpath("//div[@class='products row products-grid']//div[contains(@class,'js-product-miniature-wrapper')][" + i + "]//div[@class='product-availability']/span")).getText();
			if(!prdavl.equalsIgnoreCase("Out of stock"))
			{
				String prdct = driver.findElement(By.xpath("//div[@class='products row products-grid']//div[contains(@class,'js-product-miniature-wrapper')][" + i + "]//h3[@class='h3 product-title']/a")).getText();
				prodList[prdcnt] = prdct;
				System.out.println(prdct);
				driver.findElement(By.xpath("//div[@class='products row products-grid']//div[contains(@class,'js-product-miniature-wrapper')][" + i + "]//h3[@class='h3 product-title']/a")).click();
				driver.findElement(By.xpath("//*[@id='add-to-cart-or-refresh']/div[2]/div/div[2]/div/button")).click();
				if(prdcnt != 1)
				{
					new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/button"))).click();
					driver.navigate().back();
				}
				else
				{
					new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/a"))).click();
				}
				prdcnt++;
			}
			i++;
		}
	}
	@Test(dependsOnMethods = {"selectProducts"})
	public void verifyProducts()
	{
		List<WebElement> cartPrdcts = driver.findElements(By.xpath("//ul[@class='cart-items']//li[@class='cart-item']//div[@class='product-line-info']"));
		
		int i=0;
		for(WebElement cPrdct: cartPrdcts)
		{
			Assert.assertEquals(prodList[i], cPrdct.getText());
			i++;
		}
	}
	
}
