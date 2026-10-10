package objRepo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class HamburgerMenuPage 
{
    @FindBy(id="react-burger-menu-btn")
    private WebElement menuButton;
    
    @FindBy(id="logout_sidebar_link") 
    private WebElement logout;
    
    public HamburgerMenuPage(WebDriver driver) 
    {
    	PageFactory.initElements(driver,this); 
    }
    
    public void openMenu() 
    { 
    	menuButton.click(); 
    }
    public void logout() 
    { 
    	logout.click(); 
    }
}
