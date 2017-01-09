package test.java.SetUp;

import main.java.Core.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * Created by Abraham on 1/9/2017.
 */
public class SetUp extends PageObject {
    private String homePage;

    @BeforeSuite
    public void startDriver() {
        System.out.println("startDriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeTest
    private void openHomePage() throws InterruptedException {
        homePage = System.getProperty("hostname");
        System.out.println(homePage);
        driver.get(homePage);
    }
    @AfterSuite
    public void tearDown() {
        System.out.println("tearDown");
        driver.close();
        driver.quit();
    }
}
