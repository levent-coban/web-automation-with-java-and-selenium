package tests;

import dataobjects.User;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.globalsqa.AutoCompletePage;
import pages.globalsqa.SamplePage;
import pages.petstore.CatalogPage;
import pages.petstore.CatalogSearchResultsPage;
import pages.petstore.CategoriesPage;
import pages.petstore.HomePage;
import utils.ConfigProperties;

import java.util.List;


public class ALLTests extends BaseTest {

    @Test(groups = "smoke")
    public void testCase1() {

        HomePage homePage = new HomePage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        CatalogSearchResultsPage catalogSearchResultsPage = new CatalogSearchResultsPage(driver);

        String petStoreBaseURL = ConfigProperties.getProperty("petstore.base_url");

        driver.get(petStoreBaseURL);
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page is not opened.");
        logInfoToReport("Home page opened: " + petStoreBaseURL );

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


    @Test(groups="Smoke")
    public void testCase2() {

        SamplePage samplePage = new SamplePage(driver);
        String GlobalSqaBaseURL = ConfigProperties.getProperty("globalsqa.base_url");
        String samplePageURL = GlobalSqaBaseURL.concat("/samplepagetest/");

        driver.get(samplePageURL);
        Assert.assertTrue(samplePage.isSamplePageOpened(), "Sample page is not opened.");
        logInfoToReport("Sample page is opened: " + samplePageURL);

        samplePage.selectExperienceInYears("5-7");
        Assert.assertEquals(samplePage.getActualValueOfExperienceInYearsDropdownMenu(), "5-7",
                "Actual value of the 'Experience (In Years)' in dropdown menu is not '5-7'.");

        logInfoToReport("In dropdown 'Experience (In Years)', '5-7' is selected.");

        logScreenshotToReport();
    }


    @Test(groups="regression")
    public void testCase3() {

        CatalogPage catalogPage = new CatalogPage(driver);
        CategoriesPage categoriesPage = new CategoriesPage(driver);

        String petStoreBaseURL = ConfigProperties.getProperty("petstore.base_url");
        String catalogPageURL = petStoreBaseURL.concat("/actions/Catalog.action");

        driver.get(catalogPageURL);

        Assert.assertTrue(catalogPage.isCatalogPageOpened(), "Catalog page is not opened.");
        logInfoToReport("Catalog page is opened: " + catalogPageURL);

        catalogPage.getFishButton().click();
        logInfoToReport("Clicked on button [Fish]");

        Assert.assertTrue(categoriesPage.isCategoryPageOpened("fish"), "Category page is not opened.");
        logInfoToReport("Category ("+ categoriesPage.getTitleOfCategoryPage() + ") page is opened.");


        List<WebElement> items = categoriesPage.getPetTypeNames();

        logInfoToReport("Logs for each item name in [Fish] category: ");

        StringBuilder itemNames = new StringBuilder();

        for (WebElement itemName : items) {
            itemNames.append("- ").append(itemName.getText()).append(" ");
            logInfoToReport( "- " + itemName.getText());
        }

        logInfoToReport("Item names in [Fish] category: " + itemNames);
    }


    @Test(groups="regression", dataProvider = "InputDataProvider", dataProviderClass = data.UserData.class)
    public void testCase4(User user) {

        SamplePage samplePage = new SamplePage(driver);
        String GlobalSqaBaseURL = ConfigProperties.getProperty("globalsqa.base_url");
        String samplePageURL = GlobalSqaBaseURL.concat("/samplepagetest/");

        logInfoToReport("Test data. NAME: " + user.getName() +", EMAIL: "+ user.getEmail());

        driver.get(samplePageURL);
        Assert.assertTrue(samplePage.isSamplePageOpened(), "Sample page is not opened.");
        logInfoToReport("Sample page is opened." + samplePageURL);

        samplePage.fillNameField(user.getName());
        logInfoToReport("The NAME field is filled with the following: " + user.getName());

        samplePage.fillEmailField(user.getEmail());
        logInfoToReport("The EMAIL field is filled with the following: " + user.getEmail());

        logScreenshotToReport();

        samplePage.clearNameAndEmailFields();
        logInfoToReport("The NAME and EMAIL fields are cleared.");

        logScreenshotToReport();
    }


    @Test(groups = "smoke")
    public void testCase5() {

        AutoCompletePage autoCompletePage = new AutoCompletePage(driver);
        String GlobalSqaBaseURL = ConfigProperties.getProperty("globalsqa.base_url");
        String autoCompletePageURL = GlobalSqaBaseURL.concat("/demo-site/auto-complete/");

        String valueToBeSelected = "anders andersson";

        driver.get(autoCompletePageURL);
        Assert.assertTrue(autoCompletePage.isAutoCompletePageOpened(), "Auto Complete page is not opened.");
        logInfoToReport("Auto Complete page is opened: " + autoCompletePageURL);

        driver.switchTo().frame(autoCompletePage.frameOfTheSearchField());

        autoCompletePage.fillSearchField("and");
        logInfoToReport("'Search' field is filled with the following: 'and'");

        autoCompletePage.selectName(valueToBeSelected);
        Assert.assertEquals(autoCompletePage.getValueOfSearchField(), valueToBeSelected,
                "There is a discrepancy between the expected and actual values!");
        logInfoToReport("In drop down menu 'anders andersson' is selected.");
    }
}