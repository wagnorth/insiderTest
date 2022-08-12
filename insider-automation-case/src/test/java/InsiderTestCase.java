import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.collect.Range.open;
import static java.lang.Thread.sleep;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InsiderTestCase extends BaseTest{

    @Test
    @Order(1)
    public void test_clickAccept() throws InterruptedException, IOException {
        try{
            mainPage.clickAccept();
        } catch (Exception e) {
            mainPage.cookieDisplayValue();
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Date d = new Date();
            System.out.println(d.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("_dd_MM_yyyy");
            FileUtils.copyFile(scrFile, new File("screenshots\\Failed\\cookie_value_not_none"+sdf.format(d)+".png"));
            Assertions.assertEquals("none",mainPage.cookieDisplayValue(), "Cookie Display Value is not 'none'");;
        }
        sleep(1000);
    }

    @Test
    @Order(2)
    public void test_clickMore() throws InterruptedException {
        mainPage.clickMore();
        sleep(1000);
    }

    @Test
    @Order(3)
    public void test_clickCareers() throws InterruptedException, IOException {
        try{
            mainPage.clickCareers();
            String expectedCareersUrl="https://useinsider.com/careers/";
            String actualUrl = driver.getCurrentUrl();
            if(!expectedCareersUrl.equals(actualUrl)) {
                sleep(2000);
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                Date d = new Date();
                System.out.println(d.toString());
                SimpleDateFormat sdf = new SimpleDateFormat("_dd_MM_yyyy");
                FileUtils.copyFile(scrFile, new File("screenshots\\Failed\\careers_wrong_url"+sdf.format(d)+".png"));
                Assertions.assertEquals(expectedCareersUrl,actualUrl, "Redirect to the Wrong URL");
            }
        } catch (Exception e) {
            String expectedCareersUrl="https://useinsider.com/careers/";
            String actualUrl = driver.getCurrentUrl();
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Date d = new Date();
            System.out.println(d.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("_dd_MM_yyyy");
            FileUtils.copyFile(scrFile, new File("screenshots\\Failed\\careers_couldn't_click"+sdf.format(d)+".png"));
            Assertions.assertEquals(expectedCareersUrl,actualUrl, "Could Not Click on the Careers Row");
        }
        sleep(1000);
    }

    @Test
    @Order(4)
    public void scrollLocationBlock() throws InterruptedException {
        careersPage.scrollLocation();
        sleep(1000);

    }

    @Test
    @Order(5)
    public void slideLocationBlock() throws InterruptedException, IOException {
        careersPage.sliderLocation();
    }

    @Test
    @Order(6)
    public void scrollTeamsBlock(){
        careersPage.sectionTeams();
    }



}
