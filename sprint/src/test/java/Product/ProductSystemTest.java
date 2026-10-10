package Product;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.CartPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ProductSystemTest extends BaseClass {

    @Test(groups = "System")
    public void verifyCompleteProductFlow() throws Exception {
        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login","LOGIN-SMOKE-001","Username");
        String password = excel.getDataByTestId("Login","LOGIN-SMOKE-001","Password");
        String productName = excel.getDataByTestId("Product","PRODUCT-SYS-001","ProductName");
        
        new LoginPage(driver).login(username, password);
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.getProductCount() > 0,"Products are not displayed after login");
        productPage.addProduct(productName);
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(cartPage.getCartItemCount(),1,"Product was not transferred to Cart");
        Assert.assertTrue(cartPage.containsProduct(productName),"Selected product is missing from Cart");
        Reporter.log("Complete Product system flow passed",true);
    }
}