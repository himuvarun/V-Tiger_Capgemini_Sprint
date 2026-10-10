package Product;

import baseClass.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

public class ProductBVATest extends BaseClass {

    @Test(groups = "BVA")
    public void verifyPriceSortingBoundary() throws Exception {

        ExcelUtility excel = new ExcelUtility();
        String username = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Username");
        String password = excel.getDataByTestId("Login", "LOGIN-SMOKE-001", "Password");
        String lowToHigh = excel.getDataByTestId("Product", "PRODUCT-BVA-001", "LowToHighSort");
        String highToLow = excel.getDataByTestId("Product", "PRODUCT-BVA-001", "HighToLowSort");
        double lowestPrice = Double.parseDouble(excel.getDataByTestId("Product", "PRODUCT-BVA-001", "LowestPrice"));
        double highestPrice = Double.parseDouble(excel.getDataByTestId("Product", "PRODUCT-BVA-001", "HighestPrice"));

        new LoginPage(driver).login(username, password);
        ProductPage productPage = new ProductPage(driver);

        productPage.sortBy(lowToHigh);
        List<Double> lowToHighPrices = productPage.getDisplayedPrices();

        Assert.assertEquals(lowToHighPrices.get(0), lowestPrice, "Lowest price boundary is incorrect");
        Assert.assertEquals(lowToHighPrices.get(lowToHighPrices.size() - 1), highestPrice, "Highest price boundary is incorrect");

        productPage.sortBy(highToLow);
        List<Double> highToLowPrices = productPage.getDisplayedPrices();

        Assert.assertEquals(highToLowPrices.get(0), highestPrice, "Highest price boundary is not first");
        Assert.assertEquals(highToLowPrices.get(highToLowPrices.size() - 1), lowestPrice, "Lowest price boundary is not last");

        Reporter.log("Product price boundary values and sorting were verified", true);
    }
}
