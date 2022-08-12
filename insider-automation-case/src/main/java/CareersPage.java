import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static java.lang.Thread.sleep;

public class CareersPage {

    private final WebDriver driver;

    private final By locationId = By.id("career-our-location");
    private final By locationSlider = By.xpath("//*[@id=\"location-slider\"]/div/ul");
    private final By clickSliderPath = By.xpath("/html/body/div[1]/div/div/section[3]/div/div/div/div/div/div/div/section/div/div/div/div[2]/a[2]");
    private final File locationFile;
    private final FileWriter locationResult;
    private final By teamsId = By.id("career-find-our-calling");

    public CareersPage(WebDriver driver) throws IOException {
        File deleteLocationTxt= new File("file/locations.txt");
        deleteLocationTxt.delete();
        this.driver = driver;
        this.locationFile = new File("file/locations.txt");
        this.locationResult = new FileWriter(locationFile,true);
    }

    public void scrollLocation(){
        WebElement scrollLocation = driver.findElement(locationId);
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("arguments[0].scrollIntoView(false)", scrollLocation);
    }

    public void sliderLocation () throws InterruptedException, IOException {
        WebElement sliderLocation = driver.findElement(locationSlider);
        WebElement clickSlider = driver.findElement(clickSliderPath);
        List<WebElement> li_All = sliderLocation.findElements(By.tagName("li"));
        for (WebElement elem : li_All){
            elem.getText();
            sleep(500);
            locationResult.write("\n");
            locationResult.write(elem.getText());
            locationResult.write("\n");
            clickSlider.click();
        }
        locationResult.close();
    }
    
    public void sectionTeams(){
        WebElement scrollTeams = driver.findElement(teamsId);
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("arguments[0].scrollIntoView(false)", scrollTeams);
    }
}
