package objRepo;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
public class CheckoutCompletePage {
	
    @FindBy(className="complete-header")
    private WebElement message;
    
    public CheckoutCompletePage(WebDriver driver)
    {
    	PageFactory.initElements(driver,this);
    }
    
    public String getConfirmationMessage(){
    	return message.getText();
    	}
}
