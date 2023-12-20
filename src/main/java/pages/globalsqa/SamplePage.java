package pages.globalsqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class SamplePage extends BasePage {

    public SamplePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(),'Sample Page Test')]")
    private WebElement titleOfSamplePage;

    @FindBy(id = "g2599-experienceinyears")
    private WebElement experienceInYearsDropdownMenu;

    @FindBy(id = "g2599-name")
    private WebElement nameField;

    @FindBy(id = "g2599-email")
    private WebElement emailField;

    public boolean isSamplePageOpened() {
        return titleOfSamplePage.isDisplayed();
    }

    public void selectExperienceInYears(String optionValue) {
        Select select = new Select(experienceInYearsDropdownMenu);
        select.selectByValue(optionValue);
    }

    public String getActualValueOfExperienceInYearsDropdownMenu(){
        Select select = new Select(experienceInYearsDropdownMenu);
        return select.getFirstSelectedOption().getText();
    }

    public void fillNameField(String name) {
        nameField.sendKeys(name);
    }

    public void fillEmailField(String email) {
        emailField.sendKeys(email);
    }

    public void clearNameAndEmailFields() {
        nameField.clear();
        emailField.clear();
    }

}