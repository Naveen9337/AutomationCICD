package rahulshettyacademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandAloneTest {

//This is test for CICD

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		
		// Login 
		//LandingPage LandingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("naveen.naveen11@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("L-FRsh86#");
		driver.findElement(By.id("login")).click();
		
		Thread.sleep(10000);
		
		
		//Extract Card Name from Shiopping page 
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
			List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
	WebElement prod =	products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
  List<WebElement>	cartProducts =	driver.findElements(By.cssSelector(".cartSection h3"));
                   
  Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
  Assert.assertTrue(match);
  
  driver.findElement(By.cssSelector(".totalRow button")).click();
  
  Actions a = new Actions(driver);
  
  a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
  
  driver.findElement(By.xpath("(//*[contains(@class,'ta-item')])[2]")).click();
  
  driver.findElement(By.cssSelector(".action__submit")).click();
  
  
  String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
  Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
  
  driver.close();
  
  
  
  
		
	
		
	}

}
