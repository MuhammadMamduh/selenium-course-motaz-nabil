package tests;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.remote.DesiredCapabilities;
// import org.openqa.selenium.phantomjs.PhantomJSDriver; // not working
// import org.openqa.selenium.phantomjs.PhantomJSDriverService; // not working
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.time.Duration;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import utilities.Helper;

public class TestBase extends AbstractTestNGCucumberTests
{
	public static WebDriver driver ; 
	public static WebDriverWait wait;
	
	public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";

	public static FirefoxOptions firefoxOption() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}

	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		// If you want to run the browser in the headless mode
		// options.addArguments("--headless");
		// options.addArguments("--window-size=1920,1080");
		options.setAcceptInsecureCerts(true);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		return options;
	}


	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) 
	{
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
			driver = new ChromeDriver(chromeOption()); 
		}

		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			driver = new FirefoxDriver(firefoxOption()); 
		}

		else if (browserName.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(); 
		}

		else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver(); 
		}
		// not working (note that PhantomJS is deprecated)
		// else if (browserName.equalsIgnoreCase("phantomjs")) {
		// 	DesiredCapabilities caps = new DesiredCapabilities();
		// 	caps.setJavascriptEnabled(true) ;
		// 	caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		// 	System.getProperty("user,dir") +"/drivers/phantomjs,exe");
		// String[] phantomJsArgs = {"--web-security=no", "--ignore-ssl-errors=yes"};
		// caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, PhantomJsArgs);
		// 	driver = new PhantomJSDriver();
		// }
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().to("http://demo.nopcommerce.com/");
		System.out.println("Redirected to the HOMEPAGE");
		System.out.println("________________________________________");
	} 

	@AfterSuite
	public void stopDriver() 
	{
		driver.quit();
	}

	@AfterClass
	public void moveToHomePage()
	{
		driver.navigate().to("http://demo.nopcommerce.com/");
		System.out.println("Redirected to the homepageeeeeeeeeeeeeeee");
	}
	// take screenshot when test case fail and add it in the Screenshot folder
	@AfterMethod
	public void screenshotOnFailure(ITestResult result) 
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(driver, result.getName());
		}
	}
}
