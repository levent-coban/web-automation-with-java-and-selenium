package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.petstore.CatalogPage;
import pages.petstore.CatalogSearchResultsPage;
import pages.petstore.CategoriesPage;
import pages.petstore.HomePage;
import utils.ConfigProperties;

import java.util.List;


public class PetStoreTest extends BaseTest {

    HomePage homePage;
    CatalogPage catalogPage;
    CategoriesPage categoriesPage;
    CatalogSearchResultsPage catalogSearchResultsPage;

    String petStoreBaseURL = ConfigProperties.getProperty("petstore.base_url");
    String catalogPageURL = petStoreBaseURL.concat("/actions/Catalog.action");


    @Test(groups = "smoke")
    public void testCase1() {

        homePage = new HomePage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogSearchResultsPage = new CatalogSearchResultsPage(driver);

        driver.get(petStoreBaseURL);
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened.");
        logInfoToReport("Home page opened: " + petStoreBaseURL);

        homePage.clickEnterStoreLink();
        logInfoToReport("Clicked on button [Enter the Store]");

        Assert.assertTrue(catalogPage.isCatalogPageOpened(), "Catalog page is not opened.");
        logInfoToReport("Catalog page is opened.");

        catalogPage.getSearchField().sendKeys("Angelfish");
        logInfoToReport("In search field entered 'Angelfish'.");

        catalogPage.getSearchButton().click();
        logInfoToReport("Clicked 'Search' button");

        Assert.assertEquals(catalogSearchResultsPage.getActualProductId(), "FI-SW-01");
        logInfoToReport("The actual ProductID on the page is: " + catalogSearchResultsPage.getActualProductId());
    }


    @Test(groups = "regression")
    public void testCase3() {

        catalogPage = new CatalogPage(driver);
        categoriesPage = new CategoriesPage(driver);

        driver.get(catalogPageURL);

        Assert.assertTrue(catalogPage.isCatalogPageOpened(), "Catalog page is not opened.");
        logInfoToReport("Catalog page is opened: " + catalogPageURL);

        catalogPage.getFishButton().click();
        logInfoToReport("Clicked on button [Fish]");

        Assert.assertTrue(categoriesPage.isCategoryPageOpened("fish"), "Category page is not opened.");
        logInfoToReport("Category (" + categoriesPage.getTitleOfCategoryPage() + ") page is opened.");


        List<WebElement> items = categoriesPage.getPetTypeNames();

        logInfoToReport("Logs for each item name in [Fish] category: ");

        StringBuilder itemNames = new StringBuilder();

        for (WebElement itemName : items) {
            itemNames.append("- ").append(itemName.getText()).append(" ");
            logInfoToReport("- " + itemName.getText());
        }

        logInfoToReport("Item names in [Fish] category: " + itemNames);

    }
}