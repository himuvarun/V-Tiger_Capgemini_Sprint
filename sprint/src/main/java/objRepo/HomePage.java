package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    @FindBy(className = "login_logo")
    private WebElement logo;

    @FindBy(id = "user-name")
    private WebElement username;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLoginPageDisplayed() {
        return logo.isDisplayed() && username.isDisplayed();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void openInventoryDirectly() {
        driver.get(driver.getCurrentUrl().replaceAll("/$", "") + "/inventory.html");
    }
}
