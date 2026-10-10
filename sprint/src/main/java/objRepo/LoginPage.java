package objRepo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage
{
    @FindBy(id="user-name")
    private WebElement username;
    
    @FindBy(id="password") 
    private WebElement password;
    
    @FindBy(id="login-button") 
    private WebElement loginButton;
    
    @FindBy(css="h3[data-test='error']")
    private WebElement errorMessage;
    
    public LoginPage(WebDriver driver)
    { 
    	PageFactory.initElements(driver,this); 
    }
    
    public void enterUsername(String value) 
    { 
    	username.clear(); 
    	username.sendKeys(value); 
    }
    
    public void enterPassword(String value) 
    { 
    	password.clear(); 
    	password.sendKeys(value); 
    }
    
    public void clickLogin() 
    { 
    	loginButton.click(); 
    }
    
    public void login(String user,String pass) 
    { 
    	enterUsername(user); 
    	enterPassword(pass); 
    	clickLogin(); 
    }
    
    public boolean isPasswordMasked() 
    { 
    	return "password".equals(password.getAttribute("type")); 
    }
    
    public boolean isErrorDisplayed() 
    { 
    	return !driverSafeText().isEmpty(); 
    }
    
    public String getErrorMessage() 
    { 
    	return errorMessage.getText(); 
    }
    
    private String driverSafeText() 
    { 
    	try { 
    		return errorMessage.getText(); 
    		} 
    	catch (Exception e) { 
    		return ""; 
    		} 
    }
}
