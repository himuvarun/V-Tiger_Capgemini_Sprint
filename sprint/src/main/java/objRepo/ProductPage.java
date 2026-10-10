package objRepo;

import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class ProductPage 
{
    private WebDriver driver;
    
    @FindBy(className="title") 
    private WebElement pageTitle;
    
    @FindBy(className="shopping_cart_link")
    private WebElement cartIcon;
    
    @FindBy(className="inventory_item")
    private List<WebElement> products;
    
    @FindBy(className="product_sort_container")
    private WebElement sortDropdown;
    
    public ProductPage(WebDriver driver)
    {
    	this.driver=driver; 
    	PageFactory.initElements(driver,this); 
    }
    
    public String getPageTitle() { 
    	return pageTitle.getText(); 
    	}
    public int getProductCount() { 
    	return products.size(); 
    	}
    
    public void addProduct(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }
    public void openCart() 
    { 
    	cartIcon.click(); 
    }
    
    public void sortBy(String value) 
    { 
    	new org.openqa.selenium.support.ui.Select(sortDropdown).selectByValue(value); 
    }
    
    public List<Double> getDisplayedPrices() 
    {
        List<Double> prices = new ArrayList<>();
        for (WebElement price : driver.findElements(By.className("inventory_item_price"))) {
            prices.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        return prices;
    }
}
