package resources;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReportManager {
public static ExtentReports report;
	
	public static ExtentReports getReportInstance() {
		
		if(report==null) {
			String reportName=dateUtils.getTimeStamp()+".html";
			ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/"+reportName);
			
			report=new ExtentReports();
			report.attachReporter(htmlReporter);
			
			
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "QE&A");
			report.setSystemInfo("Team Name", "Code to Explode");
			report.setSystemInfo("Event Name", "Hackathon");
			
			
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle("Automation testing in emicalculator.net");
			htmlReporter.config().setReportName("Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd,yyyy  hh:mm:ss");
		}
		return report;
		
	}
}
