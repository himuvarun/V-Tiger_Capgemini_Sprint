package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CampaignPage {

    WebDriver driver;

    public CampaignPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'More')]")
    private WebElement moreButton;

    @FindBy(xpath = "//a[normalize-space()='Campaigns']")
    private WebElement campaignsLink;

    @FindBy(xpath = "//img[@title='Create Campaign...']")
    private WebElement createCampaignButton;

    @FindBy(name = "campaignname")
    private WebElement campaignName;

    @FindBy(name = "assigned_user_id")
    private WebElement assignedTo;

    @FindBy(name = "campaignstatus")
    private WebElement campaignStatus;

    @FindBy(name = "campaigntype")
    private WebElement campaignType;

    @FindBy(name = "closingdate")
    private WebElement closingDate;

    @FindBy(css = "input.crmbutton.small.save")
    private WebElement saveButton;

    public void clickCampaigns() {
        moreButton.click();
        campaignsLink.click();
    }

    public void clickCreateCampaign() {
        createCampaignButton.click();
    }

    public void enterCampaignName(String name) {
        campaignName.sendKeys(name);
    }

    public void selectAssignedTo(String value) {
        new Select(assignedTo).selectByVisibleText(value);
    }

    public void selectCampaignStatus(String value) {
        new Select(campaignStatus).selectByVisibleText(value);
    }

    public void selectCampaignType(String value) {
        new Select(campaignType).selectByVisibleText(value);
    }

    public void enterClosingDate(String date) {
        closingDate.sendKeys(date);
    }

    public void clickSave() {
        saveButton.click();
    }

    public void clearAssignedTo() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "var element = document.getElementsByName('assigned_user_id')[0];" +
            "element.value = '';" +
            "element.dispatchEvent(new Event('change'));");
    }
}