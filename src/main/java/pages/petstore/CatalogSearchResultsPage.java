package pages.petstore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class CatalogSearchResultsPage extends BasePage {

    @FindBy(xpath = "//div[@id='Catalog']//tr[2]/td[2]/b/a")
    private WebElement productId;

    public CatalogSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public String getActualProductId() {
        return productId.getText();
    }

}