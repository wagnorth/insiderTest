import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {

    protected WebDriver driver;
    private final By cookieAccept = By.id("wt-cli-accept-all-btn");
    private final By cookieValue = By.cssSelector("#cookie-law-info-bar");
    private final By moreNavItem = By.xpath("//span[contains(text(),'More')]");
    private final By careersLink = By.partialLinkText("Careers");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void cookieAccept() {
        WebElement clickAccept = driver.findElement(cookieAccept);
        clickAccept.click();
    }

    public String cookieValue() {
        WebElement cookieDSPVal = driver.findElement(cookieValue);
        return cookieDSPVal.getCssValue("display");
    }

    public void moreNavItem() {
        WebElement clickMore = driver.findElement(moreNavItem);
        clickMore.click();
    }

    public void clickCareers() {
        WebElement clickCareers = driver.findElement(careersLink);
        clickCareers.click();
    }

}