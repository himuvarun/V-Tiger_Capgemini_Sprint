package objRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

    private WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isFooterDisplayed() {
        return !driver.findElements(By.cssSelector("footer.footer")).isEmpty();
    }

    public List<WebElement> getFooterLinks() {
        return driver.findElements(By.cssSelector("footer.footer a"));
    }

    public int getFooterLinkCount() {
        return getFooterLinks().size();
    }

    public boolean hasSocialLink(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        return driver.findElements(
                By.cssSelector("footer.footer a[href*='" + name.trim().toLowerCase() + "']"))
                .size() > 0;
    }

    public void clickFirstFooterLink() {
        List<WebElement> links = getFooterLinks();
        if (links.isEmpty()) {
            throw new NoSuchElementException("No footer link is available");
        }
        links.get(0).click();
    }

    public void clickLastFooterLink() {
        List<WebElement> links = getFooterLinks();
        if (links.isEmpty()) {
            throw new NoSuchElementException("No footer link is available");
        }
        links.get(links.size() - 1).click();
    }

    public void clickLinkContaining(String text) {
        for (WebElement link : getFooterLinks()) {
            if (link.getText().contains(text)) {
                link.click();
                return;
            }
        }
        throw new NoSuchElementException("Footer link not found: " + text);
    }

    public boolean isSauceDemoUrl() {
        return driver.getCurrentUrl().contains("saucedemo.com");
    }
}
