package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;

public class insiderPageEnv {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://useinsider.com");

        final By clmor = By.id("wt-cli-accept-all-btn");
        final By clos = By.id("close-button-1454703945249");

        {
            WebElement clickmore = driver.findElement(clmor);
            clickmore.click();
            sleep(1000);
            WebElement clos1 = driver.findElement(clos);
            clos1.click();
        }
    }
}
