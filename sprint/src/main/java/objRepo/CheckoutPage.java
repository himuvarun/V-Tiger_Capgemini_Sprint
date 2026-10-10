package objRepo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class CheckoutPage {

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterCustomerDetails(String first, String last, String zip) 
    {
        firstName.sendKeys(first);
        lastName.sendKeys(last);
        postalCode.sendKeys(zip);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean isPageDisplayed() {
        return pageTitle.isDisplayed();
    }
}