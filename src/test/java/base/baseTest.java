package base;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.testng.ITestResult;
import org.testng.annotations.*;


import java.time.Duration;

import utils.MyWebDriverListener;
import utils.ReportManager;

public class baseTest {
    protected static WebDriver driver;

    protected static EventFiringWebDriver edriver;

    private MyWebDriverListener listener;



    @BeforeTest
    public void setUp() {
        ReportManager.setExtent();
    }

    @Parameters({"browser","URL"})
    @BeforeClass
    public void openBrowser(String browser,String URL) {
        setupDriver(browser);
        edriver = new EventFiringWebDriver(driver);
        listener = new MyWebDriverListener();
        edriver.register(listener);
        edriver.manage().window().maximize();
        edriver.get(URL);
        //driver.get("https://jupiter.cloud.planittesting.com/#/");
        edriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }


    @AfterMethod
    public void afterMethod(ITestResult result) {

        driver.quit();
    }


    @AfterTest
    public void tearDown() {
        ReportManager.endReport();

    }


    public void setupDriver(String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

    }


}