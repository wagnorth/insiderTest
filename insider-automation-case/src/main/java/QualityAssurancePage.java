import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QualityAssurancePage{
    protected WebDriver driver;
    private final By seeAllQAJobsButton = By.xpath("//*[text()='See all QA jobs']");

    public QualityAssurancePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAllQAJobsButton(){
        WebElement allQAJobsBtN = driver.findElement(seeAllQAJobsButton);
        allQAJobsBtN.click();
    }

}