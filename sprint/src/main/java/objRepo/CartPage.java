package objRepo;

import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class CartPage {
    private WebDriver driver;
    
    @FindBy(className="title") 
    private WebElement title;
    
    @FindBy(id="checkout") 
    private WebElement checkoutButton;
    
    public CartPage(WebDriver driver)
    { 
    	this.driver=driver; 
    	PageFactory.initElements(driver,this); 
    	
    }
    
    public String getPageTitle() 
    { 
    	return title.getText(); 
    }
    
    public int getCartItemCount()
    { 
    	return driver.findElements(By.className("cart_item")).size(); 
    }
    
    public void clickCheckout()
    { 
    	checkoutButton.click(); 
    }
    
    public boolean containsProduct(String name)
    {
        List<WebElement> products =
                driver.findElements(By.className("inventory_item_name"));
        for (WebElement product : products) {
            if (product.getText().trim().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    public void removeProduct(String name) {
        String xpath ="//div[text()='" + name + "']/ancestor::div[@class='cart_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }
}
