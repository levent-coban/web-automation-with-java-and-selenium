package tests;

import dataobjects.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.globalsqa.AutoCompletePage;
import pages.globalsqa.SamplePage;
import utils.ConfigProperties;


public class GlobalSqaTest extends BaseTest {

    AutoCompletePage autoCompletePage;
    SamplePage samplePage;

    String GlobalSqaBaseURL = ConfigProperties.getProperty("globalsqa.base_url");
    String samplePageURL = GlobalSqaBaseURL.concat("/samplepagetest/");
    String autoCompletePageURL = GlobalSqaBaseURL.concat("/demo-site/auto-complete/");


    @Test(groups = "Smoke")
    public void testCase2() {

        samplePage = new SamplePage(driver);

        driver.get(samplePageURL);
        Assert.assertTrue(samplePage.isSamplePageOpened(), "Sample page is not opened.");
        logInfoToReport("Sample page is opened: " + samplePageURL);

        samplePage.selectExperienceInYears("5-7");
        Assert.assertEquals(samplePage.getActualValueOfExperienceInYearsDropdownMenu(), "5-7",
                "Actual value of the 'Experience (In Years)' in dropdown menu is not '5-7'.");

        logInfoToReport("In dropdown 'Experience (In Years)', '5-7' is selected.");

        logScreenshotToReport();
    }



    @Test(groups = "Regression", dataProvider = "InputDataProvider", dataProviderClass = data.UserData.class)
    public void testCase4(User user) {

        samplePage = new SamplePage(driver);

        logInfoToReport("Test data. NAME: " + user.getName() + ", EMAIL: " + user.getEmail());

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

        autoCompletePage = new AutoCompletePage(driver);

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