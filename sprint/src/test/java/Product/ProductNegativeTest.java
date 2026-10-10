package Product;

import baseClass.BaseClass;
import objRepo.HomePage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ProductNegativeTest extends BaseClass {

    @Test(groups = "Negative")
    public void verifyUnauthorizedUserCannotAccessProducts() throws Exception {

        HomePage home = new HomePage(driver);
        home.openInventoryDirectly();
        Assert.assertFalse(driver.getCurrentUrl().endsWith("/inventory.html"),"Unauthorized user reached Products");
        Reporter.log("Unauthorized Product access was blocked",true);
    }
}