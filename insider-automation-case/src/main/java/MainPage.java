import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private final WebDriver driver;

    private final By acceptId = By.id("wt-cli-accept-all-btn");
    private final By cookieValue = By.cssSelector("#cookie-law-info-bar");
    private final By moreXpath = By.xpath("//span[contains(text(),'More')]");
    private final By careersLink = By.partialLinkText("Careers");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickAccept(){
        WebElement clickAccept = driver.findElement(acceptId);
        clickAccept.click();
    }

    public String cookieDisplayValue(){
        WebElement cookieDisplayValue = driver.findElement(cookieValue);
        return cookieDisplayValue.getCssValue("display");
    }

    public void clickMore(){
        WebElement clickMore = driver.findElement(moreXpath);
        clickMore.click();
    }

    public void clickCareers(){
        WebElement clickCareers = driver.findElement(careersLink);
        clickCareers.click();
    }


}
