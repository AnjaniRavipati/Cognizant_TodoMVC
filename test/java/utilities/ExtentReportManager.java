package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/* SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date date = new Date();
		String currentDateTime = sdf.format(date);*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName = "Test-Report-" + timeStamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); 
		
		sparkReporter.config().setDocumentTitle("Automation Test Report"); // Title of the report
		sparkReporter.config().setReportName("Automation Functional Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "TodoMVC");
		extent.setSystemInfo("Module", "Search");
		extent.setSystemInfo("Sub Module", "Radio Button");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display the groups in the report

		test.log(Status.PASS, result.getName() + "got Sucessfully executed");
	}

	public void onTestFail(ITestResult result){
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"got Failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().CaptureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP, result.getName() + "got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport= new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
            e.printStackTrace();
	}
		
		/*try { 
			@SuppressWarnings("deprecation")
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		
		// Create the email message
		
		ImageHtmlEmail email = new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("anjiravipati@gmail.com", "password"));
		email.setSSLOnConnect(true);
		email.setFrom("anjiravipati@gmail.com");//sender
		email.setSubject("Test Results");
		email.setMsg("Please find the attached test results");
		email.addTo("vihs.anjani@gmail.com"); //receiver
		email.attach(url, "extentReport", "Please Check the Report");
		email.send(); //send the email
        }
        catch(Exception e) {
            e.printStackTrace();
		
        }*/
	}
}
