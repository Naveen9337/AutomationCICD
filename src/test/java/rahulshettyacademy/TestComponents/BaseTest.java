package rahulshettyacademy.TestComponents;

//import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage LandingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//Java ternary operator to directly run from Maven and parameterize bowser
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser"):prop.getProperty("browser");
		
		//With out using Maven command
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			//To rum in headless mode
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless"))
            {
            	options.addArguments("headless");
            }
            
			//options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//Help to run in fullscreen
			
			//To run in headed mode
			//driver = new ChromeDriver();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// Invoke Firefox
		}

		else if (browserName.equalsIgnoreCase("Edge")) {
			// Invoke Edge
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException 
	
	{
		
		//Read json to String
		     String jsonContent =    FileUtils.readFileToString(new File(filePath)
		    		 ,StandardCharsets.UTF_8);
		     
		     //String to Hashmap adding Jackson Databind as an dependency into pom.xml
		     ObjectMapper mapper = new ObjectMapper();
		     
		     //to Hashmap
		   List<HashMap<String,String>> data =  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()
		   {});
		   
		   return data;

}

	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException {
		driver = initializeDriver();
		LandingPage = new LandingPage(driver);
		LandingPage.goTo();
		return LandingPage;
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() 
	{
		driver.close();
	}

}
