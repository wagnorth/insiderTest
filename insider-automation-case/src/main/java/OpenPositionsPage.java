import org.openqa.selenium.*;
import java.util.List;
import static java.lang.Thread.sleep;

public class OpenPositionsPage{
    protected WebDriver driver;
    private final By locationDropDownList = new By.ByCssSelector("#top-filter-form > div:nth-child(1) > span");
    private final By filterLocationResults = By.id("select2-filter-by-location-results");
    private final By positionList = By.id("career-position-list");
    private final By positionQAEngineer = By.xpath("//p[contains(text(), 'Quality Assurance')]");

    public OpenPositionsPage(WebDriver driver){
        this.driver = driver;
    }

    public void locationFilter() throws InterruptedException {
        WebElement locDDL = driver.findElement(locationDropDownList);
        sleep(2000);
        locDDL.click();
        sleep(1000);

        WebElement locRes = driver.findElement(filterLocationResults);
        List<WebElement> li_All = locRes.findElements(By.tagName("li"));
        for (WebElement elem : li_All) {
            String sltLoc = elem.getText();
            if (sltLoc.contains("Istanbul, Turkey")) {
                elem.click();
                break;
            }
        }
    }

    public void selectQAEngineer(){
        WebElement scrollPOSNList = driver.findElement(positionList);
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("arguments[0].scrollIntoView(true)", scrollPOSNList);

        WebElement posnQAEngr = driver.findElement(positionQAEngineer);
        WebElement parentElem = posnQAEngr.findElement(By.xpath("./.."));
        List<WebElement> a_All = parentElem.findElements(By.xpath("a"));
        for (WebElement elem : a_All) {
            String getAccName = elem.getAccessibleName();
            String qaEngrLink = elem.getAttribute("href");
            if(getAccName.contains("Apply Now")){
                driver.get(qaEngrLink);
            }
        }
    }

}