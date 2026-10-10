package genericUtility;

import java.time.Duration;
import org.openqa.selenium.WebDriver;

public class WebDriverUtility {
    public void maximizeWindow(WebDriver driver) { 
    	driver.manage().window().maximize(); 
    	}
    public void implicitWait(WebDriver driver) { 
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
    	}
    public void openApplication(WebDriver driver, String url) { 
    	driver.get(url); 
    	}
    public void closeBrowser(WebDriver driver) { 
    	if (driver != null) driver.quit(); 
    	}
}
