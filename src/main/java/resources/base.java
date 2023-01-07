package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class base {

	public WebDriver driver;
	public Properties prop;
	public ExtentReports report=ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();	
		String userid = System.getProperty("user.dir");	
		FileInputStream fis = new FileInputStream(userid + "/src/main/java/resources/data.properties");
		prop.load(fis);	
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", userid + "/src/main/java/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", userid + "/src/main/java/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", userid + "/src/main/java/resources/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", userid + "/src/main/java/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}
	public String takeScreenShotOnFail(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}
	
	
}
