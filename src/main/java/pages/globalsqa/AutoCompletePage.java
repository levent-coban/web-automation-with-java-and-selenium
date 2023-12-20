package pages.globalsqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;


public class AutoCompletePage extends BasePage {

    public AutoCompletePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(),\"Auto Complete\")]")
    private WebElement titleAutoCompletePage;

    @FindBy(xpath = "//iframe[contains(@class, 'demo-frame')]")
    private WebElement frameOfTheSearchField;

    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy(xpath = "//ul[@id='ui-id-1']")
    private WebElement listOfSearchField;


    public boolean isAutoCompletePageOpened() {
        return titleAutoCompletePage.isDisplayed();
    }

    public WebElement frameOfTheSearchField() {
        return frameOfTheSearchField;
    }

    public void fillSearchField(String searchText) {
        searchField.sendKeys(searchText);
    }

    public void selectName(String name) {
        String xpathStr = "//div[contains(text(),'" + name + "')]";
        listOfSearchField.findElement(By.xpath(xpathStr)).click();
    }

    public String getValueOfSearchField(){
        return searchField.getAttribute("value");
    }

}