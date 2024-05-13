package rahulshettyacademy.resources;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentRepoterNG {
	
	public static ExtentReports getReportObect()
	{
		//We need two classes to genrate report a)ExtentSparkReporter and ExtentReports
				//E:\Eclipse Workspace\ExtentReports
				String path = System.getProperty("user.dir")+"\\reports\\index.html";
				
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results");
				reporter.config().setDocumentTitle("Test Results");
				
				ExtentReports	 extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Naveen Shrivastava");
				return extent;
	}
	
	
	
	
}
