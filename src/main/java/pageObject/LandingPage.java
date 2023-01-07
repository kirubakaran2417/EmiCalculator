package pageObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.apache.commons.io.FileUtils;

//import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.ExtentReportManager;

public class LandingPage {
	
	
	public WebDriver driver;
	public Properties prop;
	By  title;
	public ExtentTest logger;
	
	public LandingPage(WebDriver driver, Properties prop1) {
		this.driver = driver;
		prop = prop1;
		title = By.xpath(prop.getProperty("title_XPATH"));
		
	}

	
	public String getTitles() {

		return driver.findElement(title).getText();
	}
	
	
	/*****************************************************************************************************
	 *********************************** close the Browser************************************************
	 *****************************************************************************************************/

	public void quiteBrowser() {
		driver.quit();
	}

	/*****************************************************************************************************
	 ***************************** entering Data into the input Field*************************************
	 *****************************************************************************************************/

	public void inputData(String key, String data, String toClickKey) {
		
		if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(prop.getProperty(key))).clear();
			driver.findElement(By.xpath(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.xpath(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.xpath(prop.getProperty(key))).sendKeys(data);
			
			
		} else if (key.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(prop.getProperty(key))).clear();
			driver.findElement(By.linkText(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.linkText(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.linkText(prop.getProperty(key))).sendKeys(data);
		
			
		}else if(key.endsWith("_ID")) {
			driver.findElement(By.id(prop.getProperty(key))).clear();
			driver.findElement(By.id(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.id(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.id(prop.getProperty(key))).sendKeys(data);
			
			
		}else if(key.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(prop.getProperty(key))).clear();
			driver.findElement(By.className(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.className(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.className(prop.getProperty(key))).sendKeys(data);
		
			
		}else if(key.endsWith("_PARTIALLINKTEXT")) {
			driver.findElement(By.partialLinkText(prop.getProperty(key))).clear();
			driver.findElement(By.partialLinkText(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.partialLinkText(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.partialLinkText(prop.getProperty(key))).sendKeys(data);
			
			
		}else if(key.endsWith("_CSSSELECTOR")) {
			driver.findElement(By.cssSelector(prop.getProperty(key))).clear();
			driver.findElement(By.cssSelector(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.cssSelector(prop.getProperty(key))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.cssSelector(prop.getProperty(key))).sendKeys(data);
			
		} 
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		eleToClick(toClickKey);
		
	}

	/*****************************************************************************************************
	 *************************** Clicks the element with passed parameter*********************************
	 *****************************************************************************************************/

	public void eleToClick(String key) {

		if (key.endsWith("_XPATH")) {
			driver.findElement(By.xpath(prop.getProperty(key))).click();
		} else if (key.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(prop.getProperty(key))).click();
		}else if(key.endsWith("_ID")) {
			driver.findElement(By.id(prop.getProperty(key))).click();
		}else if(key.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(prop.getProperty(key))).click();
		}else if(key.endsWith("_PARTIALLINKTEXT")) {
			driver.findElement(By.partialLinkText(prop.getProperty(key))).click();
		}else if(key.endsWith("_CSSSELECTOR")) {
			driver.findElement(By.cssSelector(prop.getProperty(key))).click();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	/*****************************************************************************************************
	 ************************** scroll to the element with passed parameter*****************************
	 *****************************************************************************************************/

	public void scrollToEle(String key) {
		
		WebElement ele;
		
		if (key.endsWith("_XPATH")) {
			ele = driver.findElement(By.xpath(prop.getProperty(key)));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		} else if (key.endsWith("_LINKTEXT")) {
			ele = driver.findElement(By.linkText(prop.getProperty(key)));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		}else if(key.endsWith("_ID")) {
			ele = driver.findElement(By.xpath(prop.getProperty(key)));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		}else if(key.endsWith("_CLASSNAME")) {
			ele = driver.findElement(By.className(prop.getProperty(key)));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		}else if(key.endsWith("_PARTIALLINKTEXT")) {
			ele = driver.findElement(By.partialLinkText(prop.getProperty(key)));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		}else if(key.endsWith("_CSSSELECTOR")) {
			ele = driver.findElement(By.cssSelector(prop.getProperty(key)));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		

	}

	/*****************************************************************************************************
	 ****************************** Extends The number of Years Displayed ********************************
	 *****************************************************************************************************/

	public void clicksExtend(String key) {

		List<WebElement> elels = driver.findElements(By.xpath(prop.getProperty(key)));
		Iterator itr = elels.iterator();
		while (itr.hasNext()) {
			((WebElement) itr.next()).click();

		}
		

	}

	/*****************************************************************************************************
	 *********** Gets the Total Number of Months and returns as a string of arrays ***********************
	 *****************************************************************************************************/

	public String[] getData(String key) {

		List<WebElement> elels = driver.findElements(By.xpath(prop.getProperty(key)));
		Iterator itr = elels.iterator();
		//System.out.println(elels.size());
		String[] ls = new String[elels.size()];
		int p = 0;
		while (itr.hasNext()) {
			ls[p] = (((WebElement) itr.next()).getText());
			p++;
		}

		for (int i = 0; i < elels.size(); i++) {
			System.out.println(ls[i]);
			System.out.println("");
		}
		return ls;

	}

	/*****************************************************************************************************
	 ******** Gets the data based on the parameter and returns as a String of ArrayList ******************
	 *****************************************************************************************************/

	public ArrayList<String> sendMonth(String key) {

		List<WebElement> elels = driver.findElements(By.xpath(prop.getProperty(key)));
		Iterator itr = elels.iterator();
		String[] data = new String[elels.size()];
		ArrayList<String> list = new ArrayList<String>();

		int p = 0;
		String[][] ls;
		String[] daa;

		while (itr.hasNext()) {
			data[p] = (((WebElement) itr.next()).getText());
			p++;
		}

		for (int j = 0; j < data.length; j++) {

			ls = new String[data.length + 1][data[j].length() + 1];
			for (int i = 0; i < ls[j].length; i++) {
				ls[j] = data[j].split("%");
				ls[j][i] = ls[j][i].concat("%");
				ls[j][i] = ls[j][i].trim();
				ls[j][i] = ls[j][i].replaceAll("[^A-Za-z0-9.,%()+]", " ");
				ls[j][i] = ls[j][i].replaceAll(" ", "-");
				ls[j][i] = ls[j][i].replaceAll("---", "-");
				list.add(ls[j][i]);
			}
		}
		return list;

	}

	/*****************************************************************************************************
	 *************** Appends Years with the months and returns the value as ArrayList ********************
	 *****************************************************************************************************/

	public ArrayList<String> getYearWithMonths(ArrayList<String> months, ArrayList<String> years) {

		Iterator itrMonths = months.iterator();
		Iterator itrYears = years.iterator();

		ArrayList<String> both = new ArrayList<String>();

		//System.out.println("INSIDE YEARS AND MONTHS  ");

		if (itrYears.hasNext()) {
			both.add((String) itrYears.next());
		}

		while (itrMonths.hasNext()) {
			String temp = (String) itrMonths.next();

			if (temp.contains("Dec")) {
				if (itrYears.hasNext()) {
					both.add(temp);
					both.add((String) itrYears.next());
				}
			} else {
				both.add(temp);
			}
		}
		return both;
	}

	/*****************************************************************************************************
	 ************ return the number of months passed in the parameters and returns ArrayLIst ************* 
	 *****************************************************************************************************/

	public ArrayList<String> numberOfMonths(int months, ArrayList<String> listOfAllMonths) {

		ArrayList<String> listOfMonths = new ArrayList<String>();
		Iterator itrlistOfMonths = listOfAllMonths.iterator();
		int count = 1;
		months++;
		while (itrlistOfMonths.hasNext()) {
			String monthsData = (String) itrlistOfMonths.next();
			if (count < months) {
				listOfMonths.add(monthsData);
				count++;
			}
		}
		return listOfMonths;
	}
	
	/*****************************************************************************************************
	 ******** Gets the data based on the parameter and returns as a String of ArrayList ******************
	 *****************************************************************************************************/

	public  void sendHeading(String key) {
		
		//ArrayList<String>
		List<WebElement> elels = driver.findElements(By.xpath(prop.getProperty(key)));
		Iterator itr = elels.iterator();
		String[] data = new String[elels.size()];
		ArrayList<String> list = new ArrayList<String>();

		int p = 0;
		String[] ls;
		String[] daa;

		while (itr.hasNext()) {
			data[p] = (((WebElement) itr.next()).getText());
			p++;
		}

			for(int i=0; i<data.length;i++) {
				
				//data[i]=data[i].replaceAll("[^(A)(B)(A + B)]", " ");
				data[i]=data[i].trim();
				list.add(data[i]);
				if(data[i].equals("(A)") ||data[i].equals( "(B)" ) || data[i].equals("(A + B)")) {
					//data[i]=data[i].replaceAll("[^(A)(B)(A + B)]", " ");
					list.remove(i);
				}
				System.out.println(data[i]);
		}
		Iterator itrlist = list.iterator();
		while(itrlist.hasNext()) {
			System.out.println((String)itrlist.next());
		}
		//return list;

	}
	 
	
	
	
}
