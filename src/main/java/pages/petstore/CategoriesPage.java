package pages.petstore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class CategoriesPage extends BasePage {

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id=\"Catalog\"]/h2")
    private WebElement titleOfCategoryPage;

    @FindBy(xpath = "//div[@id='Catalog']/table/tbody/tr/td[2]")
    private List<WebElement> petTypeNames;

    public String getTitleOfCategoryPage(){
        return titleOfCategoryPage.getText();
    }

    public boolean isCategoryPageOpened(String categoryTitle) {
        return categoryTitle.equalsIgnoreCase(titleOfCategoryPage.getText());
    }

    public List<WebElement> getPetTypeNames() {
        return petTypeNames;
    }

}
