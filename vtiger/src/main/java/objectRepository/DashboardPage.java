package objectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(normalize-space(.),'Dashboard')]")
    private WebElement dashboardLink;

    @FindBy(xpath = "//*[normalize-space(.)='Dashboard Home']")
    private WebElement dashboardHeading;

    @FindBy(xpath = "//a[normalize-space(.)='Edit']")
    private WebElement editButton;

    @FindBy(xpath = "//*[contains(normalize-space(.),'Pipeline By Sales Stage') "
            + "or contains(normalize-space(.),'Pipeline By Month By Outcome') "
            + "or contains(normalize-space(.),'All Opportunities By Lead Source')]")
    private List<WebElement> dashboardWidgets;

    @FindBy(xpath = "//*[contains(normalize-space(.),'Horizontal Bar Chart') "
            + "or contains(normalize-space(.),'Vertical Bar Chart') "
            + "or contains(normalize-space(.),'Pie Chart')]")
    private List<WebElement> chartElements;


    public void openDashboard() {
        dashboardLink.click();
    }

    public boolean isDashboardPageDisplayed() {
        return driver.getCurrentUrl().contains("module=Dashboard");
    }

    public String getDashboardPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getDashboardPageText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    public boolean isAddWidgetAvailable() {

        for (WebElement element : dashboardWidgets) {
            if (element.isDisplayed()) {
                return true;
            }
        }

        return false;
    }

    public void clickEdit() {
        editButton.click();
    }

    public int getDashboardWidgetCount() {

        int count = 0;

        for (WebElement element : dashboardWidgets) {
            if (element.isDisplayed()) {
                count++;
            }
        }

        return count;
    }

    public boolean areChartsPresent() {

        for (WebElement element : chartElements) {
            if (element.isDisplayed()) {
                return true;
            }
        }

        return false;
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public long getCurrentScrollPosition() {
        return ((Number) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;")).longValue();
    }

    public long getDashboardPageHeight() {
        return ((Number) ((JavascriptExecutor) driver).executeScript("return document.documentElement.scrollHeight;")).longValue();
    }

    public void waitForDashboardToLoad() {

        while (!"complete".equals(
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState"))) {
        }

        if (!driver.getCurrentUrl().contains("module=Dashboard")) {
            throw new RuntimeException(
                    "Dashboard page is not loaded"
            );
        }
    }
    public void refreshDashboard() {
        driver.navigate().refresh();
    }
}