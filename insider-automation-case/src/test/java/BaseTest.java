import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

import static java.lang.Thread.sleep;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected WebDriver driver;
    MainPage mainPage;
    CareersPage careersPage;
    QualityAssurancePage qualityAssurancePage;
    OpenPositionsPage openPositionsPage;

    @BeforeAll
    public void setUp() throws IOException {
        System.out.println("Setup Method Initiated");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.get("https://useinsider.com");
        mainPage = new MainPage(driver);
        careersPage = new CareersPage(driver);
        qualityAssurancePage = new QualityAssurancePage(driver);
        openPositionsPage = new OpenPositionsPage(driver);
    }

    @AfterAll
    public void tearDown() throws InterruptedException {
        sleep(2000);
        driver.quit();
        System.out.println("Test Finished");
    }

}