package pages.petstore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;


public class CatalogPage extends BasePage {

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "MainImageContent")
    private WebElement CatalogPageMainImageContent;

    @FindBy(name="keyword")
    private WebElement searchField;

    @FindBy(name="searchProducts")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@id='QuickLinks']/a[contains(@href, 'categoryId=FISH')]/img")
    private WebElement fishButton;

    public boolean isCatalogPageOpened() {
        return CatalogPageMainImageContent.isDisplayed();
    }

    public WebElement getSearchField(){
        return searchField;
    }

    public WebElement getSearchButton(){
        return searchButton;
    }

    public WebElement getFishButton(){
        return fishButton;
    }
}