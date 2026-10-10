package baseClass;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import genericUtility.FileUtility;
import genericUtility.ScreenshotUtility;
import genericUtility.WebDriverUtility;

public class BaseClass {
    protected WebDriver driver;
    protected FileUtility fileUtility = new FileUtility();
    protected WebDriverUtility webDriverUtility = new WebDriverUtility();
    protected ScreenshotUtility screenshotUtility = new ScreenshotUtility();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() { 
    	Reporter.log("Swag Labs test suite started", true); 
    	}

    @BeforeTest(alwaysRun = true)
    public void beforeTest() { 
    	Reporter.log("Test execution started", true); 
    	}

    @BeforeClass(alwaysRun = true)
    public void beforeClass() { 
    	Reporter.log("Test class: " + getClass().getSimpleName(), true); 
    	}

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void launchApplication(@Optional("chrome") String browser) throws IOException {
        String configured = fileUtility.getDataFromProperties("browser");
        String selected = browser == null || browser.isBlank() ? configured : browser;
        switch (selected.toLowerCase()) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + selected);
        }
        webDriverUtility.maximizeWindow(driver);
        webDriverUtility.implicitWait(driver);
        driver.get(fileUtility.getDataFromProperties("swaglabs.url"));
        Reporter.log("Browser launched: " + selected, true);
        Reporter.log("Application opened: " + driver.getCurrentUrl(), true);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getMethod().getGroups() != null) {
            for (String group : result.getMethod().getGroups()) {
                if (group.equalsIgnoreCase("Negative")){
                    if (driver != null) {
                        String path = screenshotUtility.capture(driver, result.getMethod().getMethodName());
                        Reporter.log("Negative test screenshot: " + path, true);
                    }
                    break;
                }
            }
        }
        webDriverUtility.closeBrowser(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() { 
    	Reporter.log("Test class completed: " + getClass().getSimpleName(), true); 
    	}

    @AfterTest(alwaysRun = true)
    public void afterTest() { 
    	Reporter.log("Test execution completed", true); 
    	}

    @AfterSuite(alwaysRun = true)
    public void afterSuite() { 
    	Reporter.log("=== Swag Labs test suite completed ===", true); 
    	}
}
