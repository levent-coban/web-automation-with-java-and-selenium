package pages.petstore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[contains(text(),'Welcome to JPetStore 6')]")
    private WebElement welcomeMessage;

    @FindBy(partialLinkText = "Enter")
    private WebElement enterStoreLink;

    public boolean isHomePageOpened() {
        return welcomeMessage.isDisplayed();
    }

    public void clickEnterStoreLink() {
        enterStoreLink.click();
    }

}