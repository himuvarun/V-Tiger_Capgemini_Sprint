package baseClass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import genericUtility.FileUtility;
import genericUtility.ScreenshotUtility;
import objectRepository.LoginPage;

public class BaseClass {

    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws IOException {

        FileUtility fileUtility = new FileUtility();
        String url = fileUtility.getDataFromProperties("url");
        String username = fileUtility.getDataFromProperties("username");
        String password = fileUtility.getDataFromProperties("password");
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException(
                    "Unsupported browser: " + browser
            );
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        try {
            String[] groups = result.getMethod().getGroups();
            boolean isNegativeTest = false;
            for (String group : groups) {
                if (group.equalsIgnoreCase("Negative")) {
                    isNegativeTest = true;
                    break;
                }
            }

            if (isNegativeTest && driver != null) {
                String testName =result.getTestClass().getRealClass().getSimpleName()+ "_"+ result.getMethod().getMethodName();
                try {
                    String screenshotPath = ScreenshotUtility.takeScreenshot(driver,testName);
                    System.out.println("Negative Test Screenshot: "+ screenshotPath);
                } catch (Exception e) {
                    System.out.println("Screenshot failed: "+ e.getMessage());
                }
            }

        } finally {

            if (driver != null) {
                System.out.println("Closing browser...");
                driver.quit();
                driver = null;
                System.out.println("Browser closed.");
            }
        }
    }
}