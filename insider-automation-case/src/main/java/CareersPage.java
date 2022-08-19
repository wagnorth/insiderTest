import org.openqa.selenium.*;
import java.io.*;
import java.util.List;
import static java.lang.Thread.sleep;

public class CareersPage{

    protected WebDriver driver;
    /* --- TEAMS --- */
    private final By teamsSection = By.id("career-find-our-calling");
    private final By teamsContentWoMore = By.xpath("//*[@id=\"career-find-our-calling\"]/div/div/div[2]");
    private final By seeAllTeamsButton = By.xpath("//*[text()='See all teams']");
    private final By qualityAssuranceCol = By.xpath("//*[text()='Quality Assurance']");
    private final By qualityAssuranceLink = By.partialLinkText("Quality Assurance");
    public final File testedTeamsFile;
    private final FileWriter testedTeamsContent;
    private final File diffTeamsContentFile;
    private final FileWriter diffTeamsContent;

    /* --- LOCATIONS --- */
    private final By locationSection = By.id("career-our-location");
    private final By locationsSlider = By.xpath("//*[@id=\"location-slider\"]/div/ul");
    private final By rightArrowLocationSlider = By.xpath("/html/body/div[1]/div/div/section[3]/div/div/div/div/div/div/div/section/div/div/div/div[2]/a[2]");
    public final File testedLocationsFile;
    private final FileWriter testedLocationsContent;
    private final File diffLocationsContentFile;
    private final FileWriter diffLocationsContent;

    /* --- LIFE AT INSIDER --- */
    private final By lifeAtInsiderSection = By.xpath("//*[text()='Life at Insider']");
    private final By clickLifeAtInsiderSwiper = By.xpath("/html/body/div[1]/div/div/section[4]/div/div/div/div/div/div[3]/div/div/div");
    public final File testedLifeAtInsiderFile;
    private final FileWriter testedLifeAtInsiderContent;
    private final File diffLifeAtInsiderFile;
    private final FileWriter diffLifeAtInsiderContent;


    public CareersPage(WebDriver driver) throws IOException {

        this.driver = driver;

        /* --- TEAMS --- */
        new File("file/teams/test_teams_content.txt").delete();
        this.testedTeamsFile = new File("file/teams/test_teams_content.txt");
        this.testedTeamsContent = new FileWriter(testedTeamsFile, true);

        new File("file/teams/diff_teams_content.txt").delete();
        this.diffTeamsContentFile = new File("file/teams/diff_teams_content.txt");
        this.diffTeamsContent = new FileWriter(diffTeamsContentFile, true);

        /* --- LOCATIONS --- */
        new File("file/locations/test_locations_content.txt").delete();
        this.testedLocationsFile = new File("file/locations/test_locations_content.txt");
        this.testedLocationsContent = new FileWriter(testedLocationsFile, true);

        new File("file/locations/diff_locations_content.txt").delete();
        this.diffLocationsContentFile = new File("file/locations/diff_locations_content.txt");
        this.diffLocationsContent = new FileWriter(diffLocationsContentFile, true);

        /* --- LIFE AT INSIDER --- */
        new File("file/lifeatinsider/test_lifeatinsider_content.txt").delete();
        this.testedLifeAtInsiderFile = new File("file/lifeatinsider/test_lifeatinsider_content.txt");
        this.testedLifeAtInsiderContent = new FileWriter(testedLifeAtInsiderFile, true);

        new File("file/lifeatinsider/diff_lifeatinsider_content.txt").delete();
        this.diffLifeAtInsiderFile = new File("file/lifeatinsider/diff_lifeatinsider_content.txt");
        this.diffLifeAtInsiderContent = new FileWriter(diffLifeAtInsiderFile, true);

    }

    /* --- TEAMS SECTION --- */
    public void checkTeamsSection() throws InterruptedException, IOException {

        WebElement teamsSec = driver.findElement(teamsSection);
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("arguments[0].scrollIntoView(true)", teamsSec);

        WebElement checkWoMore = driver.findElement(teamsContentWoMore);
        List<WebElement> h3_All = checkWoMore.findElements(By.tagName("h3"));
        for (WebElement elem : h3_All) {
            elem.getText();
            sleep(500);
            testedTeamsContent.write(elem.getText());
            testedTeamsContent.write("\n");
        }
        testedTeamsContent.close();
    }

    public void compareToTeams() throws IOException {

        BufferedReader readTestedTeams = new BufferedReader(new FileReader("file/teams/test_teams_content.txt"));
        BufferedReader readExpectedTeams = new BufferedReader(new FileReader("file/teams/exp_teams_content.txt"));
        String testedTeamsLine = readTestedTeams.readLine();
        String expectedTeamsLine = readExpectedTeams.readLine();

        boolean areEqualTeams = true;
        int lineNumTeams = 1;

        while (testedTeamsLine != null || expectedTeamsLine != null) {
            if (testedTeamsLine == null || expectedTeamsLine == null) {
                areEqualTeams = false;
                break;
            } else if (!testedTeamsLine.equalsIgnoreCase(expectedTeamsLine)) {
                areEqualTeams = false;
                break;
            }
            testedTeamsLine = readTestedTeams.readLine();
            expectedTeamsLine = readExpectedTeams.readLine();
            lineNumTeams++;
        }

        if (areEqualTeams) {
            diffTeamsContent.write("Two files have same content.");
            diffTeamsContent.close();
        } else {
            diffTeamsContent.write("Two files have different content. Tested Teams has " + testedTeamsLine + " and Expected Teams has " + expectedTeamsLine + " at line " + lineNumTeams + ".");
            diffTeamsContent.close();
        }
        readTestedTeams.close();
        readExpectedTeams.close();
    }

    /* --- LOCATIONS SECTION --- */
    public void checkLocationsSection() throws InterruptedException, IOException {

        WebElement locationSec = driver.findElement(locationSection);
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("arguments[0].scrollIntoView(true)", locationSec);

        WebElement checkLocations = driver.findElement(locationsSlider);
        WebElement clickSlider = driver.findElement(rightArrowLocationSlider);
        List < WebElement > li_All = checkLocations.findElements(By.tagName("li"));
        for (WebElement elem: li_All) {
            elem.getText();
            sleep(500);
            testedLocationsContent.write(elem.getText());
            testedLocationsContent.write("\n");
            clickSlider.click();
        }
        testedLocationsContent.close();
    }

    public void compareToLocations() throws IOException {

        BufferedReader readTestedLocations = new BufferedReader(new FileReader("file/locations/test_locations_content.txt"));
        BufferedReader readExpectedLocations = new BufferedReader(new FileReader("file/locations/exp_locations_content.txt"));
        String testedLocationsLine = readTestedLocations.readLine();
        String expectedLocationsLine = readExpectedLocations.readLine();

        boolean areEqualLocations = true;
        int lineNumLocations = 1;

        while (testedLocationsLine != null || expectedLocationsLine != null) {
            if (testedLocationsLine == null || expectedLocationsLine == null) {
                areEqualLocations = false;
                break;
            } else if (!testedLocationsLine.equalsIgnoreCase(expectedLocationsLine)) {
                areEqualLocations = false;
                break;
            }
            testedLocationsLine = readTestedLocations.readLine();
            expectedLocationsLine = readExpectedLocations.readLine();
            lineNumLocations++;
        }

        if (areEqualLocations) {
            diffLocationsContent.write("Two files have same content.");
            diffLocationsContent.close();
        } else {
            diffLocationsContent.write("Two files have different content. Tested Location has " + testedLocationsLine + " and Expected Location has " + expectedLocationsLine + " at line " + lineNumLocations + ".");
            diffLocationsContent.close();
        }
        readTestedLocations.close();
        readExpectedLocations.close();
    }

    /* --- LIFE AT INSIDER SECTION --- */
    public void checkLifeAtInsiderSection() throws InterruptedException, IOException {

        WebElement laiSec = driver.findElement(lifeAtInsiderSection);
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("arguments[0].scrollIntoView(true)", laiSec);

        WebElement checkLaI= driver.findElement(clickLifeAtInsiderSwiper);
        List < WebElement > div_All = checkLaI.findElements(By.className("elementor-carousel-image"));
        for (WebElement elem: div_All) {
            sleep(500);
            testedLifeAtInsiderContent.write(elem.getAttribute("style"));
            testedLifeAtInsiderContent.write("\n");
            checkLaI.click();
        }
        testedLifeAtInsiderContent.close();
    }

    public void compareToLifeAtInsider() throws IOException {

        BufferedReader readTestedLaI = new BufferedReader(new FileReader("file/lifeatinsider/test_lifeatinsider_content.txt"));
        BufferedReader readExpectedLaI = new BufferedReader(new FileReader("file/lifeatinsider/exp_lifeatinsider_content.txt"));
        String testedLaiLine = readTestedLaI.readLine();
        String expectedLaiLine = readExpectedLaI.readLine();

        boolean areEqualLaI = true;
        int lineNumLaI = 1;

        while (testedLaiLine != null || expectedLaiLine != null) {
            if (testedLaiLine == null || expectedLaiLine == null) {
                areEqualLaI = false;
                break;
            } else if (!testedLaiLine.equalsIgnoreCase(expectedLaiLine)) {
                areEqualLaI = false;
                break;
            }
            testedLaiLine = readTestedLaI.readLine();
            expectedLaiLine = readExpectedLaI.readLine();
            lineNumLaI++;
        }

        if (areEqualLaI) {
            diffLifeAtInsiderContent.write("Two files have same content.");
            diffLifeAtInsiderContent.close();
        } else {
            diffLifeAtInsiderContent.write("Two files have different content. Tested Life At Insider has " + testedLaiLine + " and Expected Life At Insider has " + expectedLaiLine + " at line " + lineNumLaI + ".");
            diffLifeAtInsiderContent.close();
        }
        readTestedLaI.close();
        readExpectedLaI.close();
    }

    /* --- SEE ALL TEAMS BUTTON --- */
    public void seeAllTeamsButton() throws InterruptedException {

        WebElement allTeamsBtN = driver.findElement(seeAllTeamsButton);
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("arguments[0].scrollIntoView(false)", allTeamsBtN);

        sleep(1000);
        WebElement clickSaTBtn = driver.findElement(seeAllTeamsButton);
        clickSaTBtn.click();

        sleep(1000);
        WebElement qaCol = driver.findElement(qualityAssuranceCol);
        JavascriptExecutor jsxa = (JavascriptExecutor) driver;
        jsxa.executeScript("arguments[0].scrollIntoView(false)", qaCol);

        sleep(1000);
        WebElement qaLink = driver.findElement(qualityAssuranceLink);
        qaLink.click();
    }

}