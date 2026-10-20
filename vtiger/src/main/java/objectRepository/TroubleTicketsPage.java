package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TroubleTicketsPage {

    WebDriver driver;

    public TroubleTicketsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(normalize-space(.),'More')]")
    private WebElement moreMenu;

    @FindBy(xpath = "//a[normalize-space(.)='Trouble Tickets']")
    private WebElement troubleTicketsLink;

    @FindBy(xpath = "//img[contains(@title,'Create') or contains(@alt,'Create')]")
    private WebElement createTicketButton;

    @FindBy(name = "ticket_title")
    private WebElement ticketTitle;

    @FindBy(name = "assigned_user_id")
    private WebElement assignedTo;

    @FindBy(name = "ticketpriorities")
    private WebElement priority;

    @FindBy(name = "ticketseverities")
    private WebElement severity;

    @FindBy(name = "ticketstatus")
    private WebElement status;

    @FindBy(name = "ticketcategories")
    private WebElement category;

    @FindBy(name = "description")
    private WebElement description;

    @FindBy(css = "input.crmbutton.small.save")
    private WebElement saveButton;


    public void clickTroubleTickets() {
        moreMenu.click();
        troubleTicketsLink.click();
    }

    public void clickCreateTicket() {
        createTicketButton.click();
    }

    public void enterTicketTitle(String value) {
        ticketTitle.sendKeys(value);
    }

    public void selectAssignedTo(String value) {
        new Select(assignedTo).selectByVisibleText(value);
    }

    public void selectPriority(String value) {
        new Select(priority).selectByVisibleText(value);
    }

    public void selectSeverity(String value) {
        new Select(severity).selectByVisibleText(value);
    }

    public void selectStatus(String value) {
        new Select(status).selectByVisibleText(value);
    }

    public void selectCategory(String value) {
        new Select(category).selectByVisibleText(value);
    }

    public void enterDescription(String value) {
        description.sendKeys(value);
    }

    public void clickSave() {
        saveButton.click();
    }
}