package automation.functionaltesting;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationTestingSchenario1 {

	    public WebDriver driver;
	    int i=1,j=1;
	    int productCount=0;
	    String productVerification[] = new String[2];
	    String verifySummary[] = new String[2];
        @BeforeTest
        public void setChromeDriver(){
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");	
        }
        
        @Test
        public void launchBrowser(){
        driver = new ChromeDriver();
    	driver.manage().window().maximize();
        }
        
        
        @Test(dependsOnMethods = {"launchBrowser"})
        public void navigateCategory() throws InterruptedException{
		driver.get("https://www.marshallspetzone.com");
		driver.findElement(By.linkText("Bird")).click();
		driver.findElement(By.linkText("Food")).click();
        }
        
        @Test(dependsOnMethods = {"navigateCategory"})
        public void ifAvailableAddToCart(){
        
        while(productCount<2)
        {
        String OutOfStock = driver.findElement(By.xpath("//div[@class='products row products-grid']//div[@class='js-product-miniature-wrapper         col-6 col-md-4 col-lg-4 col-xl-3     ']["+i+"]//div[@class='product-availability']/span")).getText();
        if(!OutOfStock.equalsIgnoreCase("Out of stock"))
       	{
        WebElement clickAvailable = driver.findElement(By.xpath("//div[@class='products row products-grid']//div["+i+"]//div[@class='product-description']/h3/a"));
        productVerification[productCount] = clickAvailable.getText();
        System.out.println(clickAvailable.getText());
        clickAvailable.click();
        driver.findElement(By.xpath("//*[@id='add-to-cart-or-refresh']/div[2]/div/div[2]/div/button")).click();
        
        if(productCount==1){
        //driver.findElement(By.linkText("Proceed to checkout")).click();
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Proceed to checkout"))).click();
        }
        else
        {
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/button"))).click();
        driver.navigate().back();
   		}
        productCount++;
       	}
        i++;
        }
        }
        
        @Test
        public void verifyProductsAvailableOnSummary()
        {
        	List<WebElement> cartPrdcts = driver.findElements(By.xpath("//ul[@class='cart-items']//li[@class='cart-item']//div[@class='product-line-info']"));
    		
    		int i=0;
    		for(WebElement cPrdct: cartPrdcts)
    		{
    			Assert.assertEquals(productVerification[i], cPrdct.getText());
    			i++;
    		}
        }
        
        @AfterTest
        public void closeBrowser()
        {
        driver.close();
        }
}


