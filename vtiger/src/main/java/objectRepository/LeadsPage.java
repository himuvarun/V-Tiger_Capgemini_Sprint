package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LeadsPage {

    WebDriver driver;

    public LeadsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@title='Create Lead...']")
    private WebElement createLeadButton;

    @FindBy(name = "firstname")
    private WebElement firstName;

    @FindBy(name = "lastname")
    private WebElement lastName;

    @FindBy(name = "company")
    private WebElement company;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(name = "assigned_user_id")
    private WebElement assignedTo;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "state")
    private WebElement state;

    @FindBy(name = "code")
    private WebElement postalCode;

    @FindBy(name = "country")
    private WebElement country;

    @FindBy(css = "input.crmbutton.small.save")
    private WebElement saveButton;


    public void clickCreateLead() {
        createLeadButton.click();
    }

    public void enterFirstName(String value) {
        firstName.sendKeys(value);
    }

    public void enterLastName(String value) {
        lastName.sendKeys(value);
    }

    public void enterCompany(String value) {
        company.sendKeys(value);
    }

    public void enterPhone(String value) {
        phone.sendKeys(value);
    }

    public void enterEmail(String value) {
        email.sendKeys(value);
    }

    public void selectAssignedTo(String value) {
        new Select(assignedTo).selectByVisibleText(value);
    }

    public void enterCity(String value) {
        city.sendKeys(value);
    }

    public void enterState(String value) {
        state.sendKeys(value);
    }

    public void enterPostalCode(String value) {
        postalCode.sendKeys(value);
    }

    public void enterCountry(String value) {
        country.sendKeys(value);
    }

    public void clickSave() {
        saveButton.click();
    }

    public void clearAssignedTo() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "var element = document.getElementsByName('assigned_user_id')[0];" +
            "element.value = '';" +
            "element.dispatchEvent(new Event('change'));"
        );
    }
}