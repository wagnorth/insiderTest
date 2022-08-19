import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InsiderTestCase extends BaseTest {
    private File ssFile;
    private Date cd;
    private SimpleDateFormat sdf;
    private String expCareersUrl;
    private String actUrl;

    /* --- MAIN PAGE TEST --- */
    @Test
    @Order(1)
    public void test_clickAccept() throws InterruptedException, IOException {

        this.ssFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        this.cd = new Date();
        this.sdf = new SimpleDateFormat("_dd_MM_yyyy");
        this.expCareersUrl = "https://useinsider.com/careers/";

        try {
            mainPage.cookieAccept();
        } catch (Exception e) {
            mainPage.cookieValue();
            FileUtils.copyFile(ssFile, new File("screenshots\\Failed\\cookie_value_not_none" + new SimpleDateFormat("_dd_MM_yyyy").format(cd) + ".png"));
            Assertions.assertEquals("none", mainPage.cookieValue(), "Cookie Display Value is not 'none'");;
        }
        sleep(1000);
    }

    @Test
    @Order(2)
    public void test_clickMore() throws InterruptedException {
        mainPage.moreNavItem();
        sleep(1000);
    }

    @Test
    @Order(3)
    public void test_clickCareers() throws InterruptedException, IOException {

        try {
            mainPage.clickCareers();
            this.actUrl = driver.getCurrentUrl();
            if (!expCareersUrl.equals(actUrl)) {
                sleep(2000);
                FileUtils.copyFile(ssFile, new File("screenshots\\Failed\\careers_wrong_url" + sdf.format(cd) + ".png"));
                Assertions.assertEquals(expCareersUrl, actUrl, "Redirect to the Wrong URL");
            }
        } catch (Exception e) {
            FileUtils.copyFile(ssFile, new File("screenshots\\Failed\\careers_couldn't_click" + sdf.format(cd) + ".png"));
            Assertions.assertEquals(expCareersUrl, actUrl, "Could Not Click on the Careers Row");
        }
        sleep(1000);
    }

    /* --- TEAMS SECTION TEST --- */
    @Test
    @Order(4)
    public void scrollAndCheckTeams() throws IOException, InterruptedException {
        careersPage.checkTeamsSection();
    }

    @Test
    @Order(5)
    public void compareToTeams() throws IOException {
        careersPage.compareToTeams();
        BufferedReader readDifferentTeamsFile = new BufferedReader(new FileReader("file/teams/diff_teams_content.txt"));
        String differentContentTeams = readDifferentTeamsFile.readLine();
        Assertions.assertEquals("Two files have same content.", differentContentTeams, "Location have expected and tested different content.");
    }

    /* --- LOCATIONS SECTION TEST --- */
    @Test
    @Order(6)
    public void scrollAndCheckLocations() throws InterruptedException, IOException {
        careersPage.checkLocationsSection();
        sleep(1000);
    }

    @Test
    @Order(7)
    public void compareToLocations() throws IOException {
        careersPage.compareToLocations();
        BufferedReader readDifferentLocationsFile = new BufferedReader(new FileReader("file/locations/diff_locations_content.txt"));
        String differentContent = readDifferentLocationsFile.readLine();
        Assertions.assertEquals("Two files have same content.", differentContent, "Location have expected and tested different content.");
    }

    /* --- LIFE AT INSIDER SECTION TEST --- */
    @Test
    @Order(8)
    public void scrollAndCheckLifeAtInsider() throws InterruptedException, IOException {
        careersPage.checkLifeAtInsiderSection();
        sleep(1000);
    }

    @Test
    @Order(9)
    public void compareToLifeAtInsider() throws IOException {
        careersPage.compareToLifeAtInsider();
        BufferedReader readDifferentLaIFile = new BufferedReader(new FileReader("file/lifeatinsider/diff_lifeatinsider_content.txt"));
        String differentContent = readDifferentLaIFile.readLine();
        Assertions.assertEquals("Two files have same content.", differentContent, "Location have expected and tested different content.");
    }

    @Test
    @Order(10)
    public void clickSeeAllTeamsButton() throws IOException, InterruptedException {
        careersPage.seeAllTeamsButton();
        sleep(1000);
    }

    /* --- SEE ALL QA JOBS TEST --- */
    @Test
    @Order(11)
    public void clickSeeAllQAJobsButton(){
        qualityAssurancePage.clickAllQAJobsButton();
    }

    /* --- OPEN POSITIONS TEST --- */
    @Test
    @Order(12)
    public void selectLocation() throws InterruptedException {
        openPositionsPage.locationFilter();
    }

    @Test
    @Order(13)
    public void selectQAEngineer() {
        openPositionsPage.selectQAEngineer();
        Assertions.assertEquals("Insider. - Software Quality Assurance Engineer",driver.getTitle());
    }

}