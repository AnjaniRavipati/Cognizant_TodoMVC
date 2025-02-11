package testBase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;

public class BaseClass {
	
public Logger logger;
	
public static WebDriver driver;
public Properties prop;
	
	@BeforeClass(groups = {"Sanity","Regression","Master","Datadriven"})
	@Parameters("browser")
	public void setup(String br) throws Exception {
		
		//Loading configuration file
		FileReader reader = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(reader);
		
		logger =LogManager.getLogger(this.getClass());
		//WebDriverManager.chromedriver().setup();
		
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/vb/Documents/chromedriver");
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/vb/Documents/geckodriver");
			driver = new FirefoxDriver();
		} else {
			System.setProperty("webdriver.safari.driver", "/Users/vb/Documents/safaridriver");
			driver = new SafariDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("appURL")); //reading URL from properties file
		driver.manage().window().maximize();
	}
	
	@AfterMethod (groups = {"Sanity","Regression","Master","Datadriven"})
	@AfterClass(groups = {"Sanity","Regression","Master","Datadriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		@SuppressWarnings("deprecation")
		String generatedString = RandomStringUtils.randomAlphabetic(10);
		return generatedString;
	}
	
	public String randomNumber() {
		@SuppressWarnings("deprecation")
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	@SuppressWarnings("deprecation")
	public String randomAlphNumeric() {
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
        return generatedstring+"@"+ generatednumber;
	}
	
	public String CaptureScreen(String tname) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\Screenshots\\"+tname+"_"+ timeStamp+".png";
		File finalDestination = new File(targetFilePath);
		
		sourceFile.renameTo(finalDestination);
		
		return targetFilePath;
	}

}
