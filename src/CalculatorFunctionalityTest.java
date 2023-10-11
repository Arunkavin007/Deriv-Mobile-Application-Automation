

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CalculatorFunctionalityTest {

	
	AndroidDriver<WebElement> driver;
	
	  WebDriverWait wait;
	
	 ExtentReports extent;
	 
	 @BeforeClass 
		public  void initialize() throws MalformedURLException  {
			// TODO Auto-generated method stub
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");  
			dc.setCapability(MobileCapabilityType.APP, "C:/Users/Hp/Downloads/Calculator.apk");
			
			URL url = new URL("http://127.0.0.1:4723/wd/hub");

			 driver = new AndroidDriver<WebElement>(url,dc);
			
		}
	 
	 @BeforeTest
		public void config() {
			
			String path =System.getProperty("user.dir")+"\\reports\\index.html";

			ExtentSparkReporter reporter = new ExtentSparkReporter(path);

			reporter.config().setReportName("Mobile Automation Results");

			reporter.config().setDocumentTitle("Test Results");
			
			 extent =new ExtentReports();

			extent.attachReporter(reporter);

			extent.setSystemInfo("Tester", "Arunkumar");
		}
	 
	 
	 @AfterTest
		public void TestCleanup() {
		 extent.flush();
	 }
	 
	 public String screenshot(String FileName) throws IOException {
		 
	 String screenShot = System.getProperty("user.dir")+"\\Artifacts\\"+ FileName +".png";

    
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    
    FileUtils.copyFile(scrFile, new File(screenShot));
    

    
  return screenShot;
	 }
	 
	
	
	
	public WebElement Digits(int num) {
		
		WebElement numbers = driver.findElementById("com.google.android.calculator:id/digit_"+num);
		return numbers;
	}
	
	@Test
	public  void  mathematicaloperation() throws IOException  {
		
		 ExtentTest test= extent.createTest("MathematicalOperation");
		 
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 
		 test.info("Calculator Application Launched in the Mobile");
		
		
		 
		//Addition
		
       Digits(7).click();
		
		WebElement add= driver.findElementById("com.google.android.calculator:id/op_add");
		
		add.click();
		
		 
		
		 Digits(3).click();
		
		WebElement equal= driver.findElementById("com.google.android.calculator:id/eq");
		
		
		equal.click();
		
		WebElement res= driver.findElementById("com.google.android.calculator:id/result_final");
		
		Assert.assertEquals( Integer.parseInt(res.getText()),10 );
		
		test.info("Addition operation performed and validated");
		
		
		
		//Subtraction
		
		 Digits(5).click();
		
		WebElement sub= driver.findElementById("com.google.android.calculator:id/op_sub");
		
		sub.click();
		
		
		
		Digits(3).click();
		
		
		equal.click();
		
		Assert.assertEquals( Integer.parseInt(res.getText()),2 );  
		
		test.info("Subtraction operation performed and validated");
		
		
		//Multiplication
		
		Digits(4).click();
			
			WebElement multiple= driver.findElementById("com.google.android.calculator:id/op_mul");
			
			multiple.click();
			
			
			 
			
			Digits(3).click();
			
			
			equal.click();
			
			Assert.assertEquals( Integer.parseInt(res.getText()),12 );  
			
			test.info("Multiplication operation performed and validated");
			
			
			//Division
			
			Digits(8).click();
			
			WebElement divide= driver.findElementById("com.google.android.calculator:id/op_div");
			
			divide.click();
			
				
			
			Digits(2).click();
			
			
			equal.click();
			
			Assert.assertEquals( Integer.parseInt(res.getText()),4 );  
			
			test.info("Division operation performed and validated");
			
			test.addScreenCaptureFromPath(screenshot("MathematicalOperation"), "All the Mathematical Operations performed and Validated");
			 
	}

}
