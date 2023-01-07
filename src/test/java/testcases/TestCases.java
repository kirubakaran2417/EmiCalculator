package testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pageObject.LandingPage;
import resources.ExtentReportManager;
import resources.base;
import resources.excelUtils;

public class TestCases extends base {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	
	/******************************************** Test-1 ************************************************
	 *********************************** Initialize the Browser ******************************************
	 *****************************************************************************************************/

	@BeforeSuite
	public void initialize() throws IOException {

		ExtentTest reporter1 = report.createTest("TestOne");

		try {
			reporter1.log(Status.INFO, "Starting the 1st Testcase-browser");
			log.info("Running TestCase-1");
			log.info("Driver is initialized");
			reporter1.log(Status.INFO, "Opening browser");
			driver = initializeDriver();
			reporter1.log(Status.INFO, "Opening URL");
			driver.get(prop.getProperty("websiteURL"));
			log.info("website is opened");
			reporter1.log(Status.PASS, "PASSED");
			log.info("passed the testCase-1");

		} catch (Exception e) {
			reporter1.log(Status.FAIL, "FAILED");
			log.info("failed to launch the chrome");
			log.info("failed the testCase-1");
		}

	}
	
	/******************************************** Test-2 ************************************************
	 ******************************* validating the Title of the page ***********************************
	 ****************************************************************************************************/

	@Test(priority = 0)

	public void validateAppTitle() throws IOException {

		LandingPage l = new LandingPage(driver, prop);
		ExtentTest reporter1 = report.createTest("TestTwo");
		reporter1.log(Status.INFO, "Starting the 2nd Testcase-title");
		log.info("Testing the title of the page");
		try {
			log.info("Running TestCase-2");
			reporter1.log(Status.INFO, "checking title");
			log.info("checking the page title");
			Assert.assertEquals(l.getTitles(), "EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
			reporter1.log(Status.PASS, "Test Passed");
			log.info("passed the testCase-2");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_2");

		}
	}
	
	/******************************************** Test-3 ************************************************
	 ************** carloan by giving 1500000 as loan amount for 1year with 9.5% interest ***************
	 ****************************************************************************************************/

	@Test(priority = 1)

	public void carLoan() throws IOException {

		ExtentTest reporter1 = report.createTest("TestThree");
		reporter1.log(Status.INFO, "Starting the 3rd Testcase-carloan");
		log.info("Testing carloan by giving 1500000 as loan amount for 1year with 9.5% interest ");
		reporter1.log(Status.INFO, "checking carLoan with 1500000 as loan amount for 1year with 9.5% interest ");
		try {
			log.info("Running TestCase-3");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending the required values");
			obb2.eleToClick("carLoan_LINKTEXT");
			obb2.inputData("loanAmountInput_XPATH", "1500000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "9.5", "percentage_XPATH");
			obb2.inputData("loanTensure_XPATH", "1", "YearWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfGivenMonths = obb2.numberOfMonths(1, listOfMonths);
			obb3.writeExcelAllDEtails("Interest1", listOfGivenMonths);
			reporter1.log(Status.PASS, "Test Passed");
			log.info("passed the testCase-3");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_3");
		}
	}

	
	/******************************************** Test-4 ************************************************
	 ***************** homeloan with 2000000 as loan amount for 1year with 8.5% interest ****************
	 ****************************************************************************************************/
	
	@Test(priority = 2)

	public void homeLoan() throws IOException {

		ExtentTest reporter1 = report.createTest("TestFour");
		reporter1.log(Status.INFO, "Starting the 4th Testcase");
		log.info("Testing homeloan by giving 2000000 as loan amount for 1year with 8.5% interest");
		reporter1.log(Status.INFO, "checking homeloan by giving 2000000 as loan amount for 1year with 8.5% interest");
		try {
			log.info("Running TestCase-4");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending the required values to input boxes");
			reporter1.log(Status.INFO, "Sending the required values to input boxes");
			obb2.inputData("loanAmountInput_XPATH", "2000000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "8.5", "percentage_XPATH");
			obb2.inputData("loanTensure_XPATH", "2", "YearWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest2", listOfAllDetails);
			log.info("passed the testCase-4");
			reporter1.log(Status.PASS, "Test Passed");

		} catch (Exception e) {
			log.info("failed the testCase_4");
		}
	}

	/******************************************** Test-5 ************************************************
	 ****** Testing personalloan by giving 200000 as loan amount for 3months with 9.5% interest  ********
	 ****************************************************************************************************/
	
	@Test(priority = 3)

	public void personalLoan() throws IOException {

		ExtentTest reporter1 = report.createTest("TestFive");
		reporter1.log(Status.INFO, "Starting the 5th Testcase");
		log.info("Testing personalloan by giving 200000 as loan amount for 3months with 9.5% interest ");
		reporter1.log(Status.INFO, "checking personal Loan");
		try {
			log.info("Running TestCase-5");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending the required values to input boxes");
			reporter1.log(Status.INFO, "Sending the required values to input boxes");
			obb2.eleToClick("personalLoan_LINKTEXT");
			obb2.inputData("loanAmountInput_XPATH", "200000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "8.5", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "3", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest3", listOfAllDetails);
			log.info("passed the testCase-5");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_5");
		}
	}
	
	
	/******************************************** Test-6 ************************************************
	 *************** Testing by giving special characters to Loan Amount under HomeLoan *****************
	 ****************************************************************************************************/

	@Test(priority = 4)

	public void speacialCharLoanAmount() throws IOException {

		ExtentTest reporter1 = report.createTest("TestSix");
		reporter1.log(Status.INFO, "Starting the 6th Testcase");
		log.info("Testing by giving special characters to Loan Amount");
		reporter1.log(Status.INFO, "checking special char in loan");
		try {
			log.info("Running TestCase-6");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending special char to loan");
			reporter1.log(Status.INFO, "Sending special char to loan");
			obb2.eleToClick("personalLoan_LINKTEXT");
			obb2.inputData("loanAmountInput_XPATH", "**@'\\", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "8.5", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "3", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest4", listOfAllDetails);
			log.info("passed the testCase-6");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_6");
		}
	}

	
	/******************************************** Test-7 ************************************************
	 ************* Testing by giving special characters to Interest under personal loan *****************
	 ****************************************************************************************************/
	
	@Test(priority = 5)

	public void speacialCharInterest() throws IOException {

		ExtentTest reporter1 = report.createTest("Testseven");
		reporter1.log(Status.INFO, "Starting the 7th Testcase");
		log.info("Testing by giving special characters to Interest");
		reporter1.log(Status.INFO, "checking special char in interest");
		try {
			log.info("Running TestCase-7");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			obb2.eleToClick("personalLoan_LINKTEXT");
			log.info("Sending special char to loan");
			reporter1.log(Status.INFO, "Sending special char to loan");
			obb2.inputData("loanAmountInput_XPATH", "2000000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "**@'\\", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "3", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest5", listOfAllDetails);
			log.info("passed the testCase-7");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_7");
		}
	}

	
	/******************************************** Test-8 ************************************************
	 ************* Testing by giving special characters to years under personal loan ********************
	 ****************************************************************************************************/
	
	@Test(priority = 6)

	public void speacialCharYear() throws IOException {

		ExtentTest reporter1 = report.createTest("TestEight");
		reporter1.log(Status.INFO, "Starting the 8th Testcase");
		log.info("Testing by giving special characters to years");
		reporter1.log(Status.INFO, "checking special char in years");
		try {
			log.info("Running TestCase-8");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			obb2.eleToClick("personalLoan_LINKTEXT");
			log.info("Sending special char to years");
			reporter1.log(Status.INFO, "Sending special char to years");
			obb2.inputData("loanAmountInput_XPATH", "2000000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "**@/", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest6", listOfAllDetails);
			log.info("passed the testCase-8");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_8");
		}
	}

	
	/******************************************** Test-9 ************************************************
	 ************* Testing by null characters to Loan Amount under personal loan ************************
	 ****************************************************************************************************/
	
	@Test(priority = 7)

	public void nullLoanAmount() throws IOException {

		ExtentTest reporter1 = report.createTest("TestNine");
		reporter1.log(Status.INFO, "Starting the 9th Testcase");
		log.info("Testing by null characters to Loan Amount");
		reporter1.log(Status.INFO, "checking null char in loan");
		try {
			log.info("Running TestCase-9");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			obb2.eleToClick("personalLoan_LINKTEXT");
			log.info("Sending null char to loan");
			reporter1.log(Status.INFO, "Sending null char to loan");
			obb2.inputData("loanAmountInput_XPATH", " ", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "8.6", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "3", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest7", listOfAllDetails);
			log.info("passed the testCase-9");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_9");
		}
	}

	
	/******************************************** Test-10 ***********************************************
	 **************** Testing by null characters to Interest under personal loan *********************
	 ****************************************************************************************************/
	
	@Test(priority = 8)

	public void nullInterest() throws IOException {

		ExtentTest reporter1 = report.createTest("TestTen");
		reporter1.log(Status.INFO, "Starting the 10th Testcase");
		log.info("Testing by null characters to Interest");
		reporter1.log(Status.INFO, "checking null char in interest");
		try {
			log.info("Running TestCase-10");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			obb2.eleToClick("personalLoan_LINKTEXT");
			log.info("Sending null char to interest");
			reporter1.log(Status.INFO, "Sending null char to interest");
			obb2.inputData("loanAmountInput_XPATH", "2000000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", " ", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "3", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest8", listOfAllDetails);
			log.info("passed the testCase-10");
			reporter1.log(Status.PASS, "Test Passed");
		}  catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_10");
		}
	}

	
	/******************************************** Test-11 ***********************************************
	 ************************* checking null char in years under personal Loan***************************
	 ****************************************************************************************************/
	
	@Test(priority = 9)

	public void nullYears() throws IOException {

		ExtentTest reporter1 = report.createTest("TestEleven");
		reporter1.log(Status.INFO, "Starting the 11th Testcase");
		log.info("Testing by null characters to years");
		reporter1.log(Status.INFO, "checking null char in years");
		try {
			log.info("Running TestCase-11");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			obb2.eleToClick("personalLoan_LINKTEXT");
			log.info("Sending null char to years");
			reporter1.log(Status.INFO, "Sending null char to years");
			obb2.inputData("loanAmountInput_XPATH", "2000000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "8.5", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", " ", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest9", listOfAllDetails);
			log.info("passed the testCase-11");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_11");
		}
	}

	
	/******************************************** Test-12 ***********************************************
	 ********************* checking float data in loanAmount under personal Loan ************************
	 ****************************************************************************************************/
	
	@Test(priority = 10)

	public void floatLoans() throws IOException {

		ExtentTest reporter1 = report.createTest("Testtwelve");
		reporter1.log(Status.INFO, "Starting the 12th Testcase");
		log.info("Testing by float in Loans");
		reporter1.log(Status.INFO, "float data in loanAmount");
		try {
			log.info("Running TestCase-12");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			obb2.eleToClick("personalLoan_LINKTEXT");
			log.info("Sending float data to loanAmount");
			reporter1.log(Status.INFO, "Sending float data to loanAmount");
			obb2.inputData("loanAmountInput_XPATH", "8.6", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "8.5", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "2", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest10", listOfAllDetails);
			log.info("passed the testCase-12");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("failed the testCase_12");
		}
	}

	
	/******************************************** Test-13 ***********************************************
	 ********************* checking float data in years under personal Loan *****************************
	 ****************************************************************************************************/
	
	@Test(priority = 11)

	public void floatYears() throws IOException {

		ExtentTest reporter1 = report.createTest("TestThirteen");
		reporter1.log(Status.INFO, "Starting the 13th Testcase");
		log.info("Testing by float in years");
		reporter1.log(Status.INFO, "float data in years");
		try {
			log.info("Running TestCase-13");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			obb2.eleToClick("personalLoan_LINKTEXT");
			log.info("Sending float data to years");
			reporter1.log(Status.INFO, "Sending float data to years");
			obb2.inputData("loanAmountInput_XPATH", "2600000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "8.5", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "8.6", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest11", listOfAllDetails);
			log.info("passed the testCase-13");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("passed the testCase-13");
		}
	}
	
	/******************************************** Test-14 ***********************************************
	 ********************* Testing by Integer value to Interest under Home Loan ****************************
	 ****************************************************************************************************/
	

	@Test(priority = 12)

	public void intInterst() throws IOException {

		ExtentTest reporter1 = report.createTest("TestFourteen");
		reporter1.log(Status.INFO, "Starting the 14th Testcase");
		log.info("Testing by Integer value to years");
		reporter1.log(Status.INFO, "Integer value to years");
		try {
			log.info("Running TestCase-14");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending Integer value to Interest");
			reporter1.log(Status.INFO, "Sending Integer value to Interest");
			obb2.inputData("loanAmountInput_XPATH", "2600000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "85", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "2", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest12", listOfAllDetails);
			log.info("passed the testCase-14");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("passed the testCase-14");
		}
	}
	
	/******************************************** Test-15 ***********************************************
	 ***************** Testing by characters to Loan Amount under Home Loan ***************************
	 ****************************************************************************************************/

	@Test(priority = 13)

	public void charLoan() throws IOException {

		ExtentTest reporter1 = report.createTest("TestFifteen");
		reporter1.log(Status.INFO, "Starting the 15th Testcase");
		log.info("Testing by characters to Loan Amount");
		reporter1.log(Status.INFO, "char in Loan Amount");
		try {
			log.info("Running TestCase-15");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending characters to Loan Amount");
			reporter1.log(Status.INFO, "Sending characters to Loan Amount");
			obb2.inputData("loanAmountInput_XPATH", "sdffx", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "9.5", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "2", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest13", listOfAllDetails);
			log.info("passed the testCase-15");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("passed the testCase-15");
		}
	}

	
	/******************************************** Test-16 ***********************************************
	 ********************** Testing by characters to Interest under Home Loan ***************************
	 ****************************************************************************************************/
	
	@Test(priority = 14)

	public void charInterest() throws IOException {

		ExtentTest reporter1 = report.createTest("Testsixteen");
		reporter1.log(Status.INFO, "Starting the 16th Testcase");
		log.info("Testing by null characters to Interest");
		reporter1.log(Status.INFO, "char in interst");
		try {
			log.info("Running TestCase-16");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending characters to Interest");
			reporter1.log(Status.INFO, "Sending characters to Interest");
			obb2.inputData("loanAmountInput_XPATH", "300000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "hhf", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "2", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest14", listOfAllDetails);
			log.info("passed the testCase-16");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("passed the testCase-16");
		}
	}

	
	/******************************************** Test-17 ***********************************************
	 ************************* Testing by characters to months under Home Loan **************************
	 ****************************************************************************************************/
	
	@Test(priority = 15)

	public void charYearsMonths() throws IOException {

		ExtentTest reporter1 = report.createTest("Testseventeen");
		reporter1.log(Status.INFO, "Starting the 17th Testcase");
		log.info("Testing by characters to months");
		reporter1.log(Status.INFO, "characters in months");
		try {
			log.info("Running TestCase-17");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending characters to months");
			reporter1.log(Status.INFO, "Sending characters to months");
			obb2.inputData("loanAmountInput_XPATH", "300000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "3.4", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "ldj", "MonthWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest15", listOfAllDetails);
			log.info("passed the testCase-17");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("passed the testCase-17");
		}
	}

	
	/******************************************** Test-18 ***********************************************
	 ************************* Testing by characters to years under Home Loan ***************************
	 ****************************************************************************************************/
	
	@Test(priority = 16)

	public void charyears() throws IOException {

		ExtentTest reporter1 = report.createTest("Testeighteen");
		reporter1.log(Status.INFO, "Starting the 18th Testcase");
		log.info("Testing by characters to years");
		reporter1.log(Status.INFO, "characters to years");
		try {
			log.info("Running TestCase-18");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending characters to years");
			reporter1.log(Status.INFO, "Sending characters to years");
			obb2.inputData("loanAmountInput_XPATH", "300000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "3.4", "percentage_XPATH");
			//obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "ldj", "YearWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest16", listOfAllDetails);
			log.info("passed the testCase-18");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("passed the testCase-18");
		}
	}

	
	/******************************************** Test-19 ***********************************************
	 *************** Testing by Exceeding limit of slider of Interest under Home Loan *******************
	 ****************************************************************************************************/
	
	@Test(priority = 17)

	public void exceedInterest() throws IOException {

		ExtentTest reporter1 = report.createTest("TestNinteen");
		reporter1.log(Status.INFO, "Starting the 19th Testcase");
		log.info("Testing by Exceeding limit of slider of Interest");
		reporter1.log(Status.INFO, "float data in years");
		try {
			log.info("Running TestCase-19");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending Exceeding limit of slider of Interest");
			reporter1.log(Status.INFO, "Sending Exceeding limit of slider of Interest");
			obb2.inputData("loanAmountInput_XPATH", "300000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "25", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "2", "YearWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest17", listOfAllDetails);
			log.info("passed the testCase-19");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("passed the testCase-19");
		}
	}

	/******************************************** Test-20 ***********************************************
	 ********** Testing by Exceeding limit of slider of Loan Amount under Home Loan *********************
	 ****************************************************************************************************/
	
	@Test(priority = 18)

	public void exceedLoan() throws IOException {

		ExtentTest reporter1 = report.createTest("TestTwenty");
		reporter1.log(Status.INFO, "Starting the 20th Testcase");
		log.info("Testing by Exceeding limit of slider of Loan Amount");
		reporter1.log(Status.INFO, "Exceeding limit of slider of Loan Amount");
		try {
			log.info("Running TestCase-20");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending Exceeding limit of slider of Loan Amount");
			reporter1.log(Status.INFO, "Sending Exceeding limit of slider of Loan Amount");
			obb2.inputData("loanAmountInput_XPATH", "50000000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "2.5", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "2", "YearWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest18", listOfAllDetails);
			log.info("passed the testCase-20");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("passed the testCase-20");
		}
	}

	
	/******************************************** Test-21 ***********************************************
	 ***************** Testing by Exceeding limit of slider of years under Home Loan ********************
	 ****************************************************************************************************/
	
	@Test(priority = 19)

	public void exceedYears() throws IOException {

		ExtentTest reporter1 = report.createTest("TestTwentyOne");
		reporter1.log(Status.INFO, "Starting the 21th Testcase");
		log.info("Testing by Exceeding limit of slider of years");
		reporter1.log(Status.INFO, "Testing by Exceeding limit of slider of years");
		try {
			log.info("Running TestCase-21");
			LandingPage obb2 = new LandingPage(driver, prop);
			excelUtils obb3 = new excelUtils();
			log.info("Sending Exceeding limit of slider of years");
			reporter1.log(Status.INFO, "Sending Exceeding limit of slider of years");
			obb2.inputData("loanAmountInput_XPATH", "2000000", "rupeeClick_XPATH");
			obb2.inputData("interestRate_XPATH", "2.5", "percentage_XPATH");
			obb2.eleToClick("MonthWise_XPATH");
			obb2.inputData("loanTensure_XPATH", "40", "YearWise_XPATH");
			obb2.scrollToEle("dataIncrease_XPATH");
			obb2.clicksExtend("toExtend_XPATH");
			log.info("retrieving the required data to print to excel");
			ArrayList<String> listOfMonths = obb2.sendMonth("allMonth_XPATH");
			ArrayList<String> listOfYears = obb2.sendMonth("yearlyDetails");
			ArrayList<String> listOfAllDetails = obb2.getYearWithMonths(listOfMonths, listOfYears);
			obb3.writeExcelAllDEtails("Interest19", listOfAllDetails);
			log.info("passed the testCase-21");
			reporter1.log(Status.PASS, "Test Passed");
		} catch (Exception e) {
			reporter1.log(Status.FAIL, "Test failed");
			log.info("passed the testCase-2");
		}
	}

	@AfterSuite
	public void teardown() {
		report.flush();
		driver.close();
	}

}
