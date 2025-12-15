package Base_class;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseurl = "https://hafsaad.com/";

    //  Extent Report objects
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected static ExtentHtmlReporter htmlReporter;

  
    @BeforeClass
    public void setup() 
    {
        // Extent report config (ONLY ONCE)
        if (extent == null)
        {
            htmlReporter = new ExtentHtmlReporter("./Report/AutomationReport.html");
            htmlReporter.config().setDocumentTitle("Automation Testing");
            htmlReporter.config().setReportName("Hafsaad UI Automation");
            htmlReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            extent.setSystemInfo("OS", "Windows");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Tester", "Merene");
        }

        // Browser setup
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseurl);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    @BeforeMethod
    public void beforeEachTest(Method method) 
    {
        test = extent.createTest(method.getName());
        handleControlPopup();
    }


    @AfterMethod
    public void afterEachTest(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, result.getThrowable());
        }
        else if (result.getStatus() == ITestResult.SKIP)
        {
            test.log(Status.SKIP, "Test skipped");
        }
        else
        {
            test.log(Status.PASS, "Test passed");
        }
    }


    @AfterClass
    public void tearDown() 
    {
        if (driver != null)
        {
            driver.quit();
        }

        if (extent != null)
        {
            extent.flush();
        }
    }


    public void handleControlPopup() 
    {
        try 
        {
            WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(3));

            if (driver.findElements(By.id("cmessage_form_iframe")).size() > 0) 
            {
                driver.switchTo().frame("cmessage_form_iframe");
            } 
            else if (driver.findElements(By.id("cmessage_abandoned_iframe")).size() > 0) 
            {
                driver.switchTo().frame("cmessage_abandoned_iframe");
            } 
            else 
            {
                return;
            }

            WebElement closeBtn = popupWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("svg")));

            closeBtn.click();
            driver.switchTo().defaultContent();

            test.info("Contlo popup closed");
        } 
        catch (Exception e) 
        {
            driver.switchTo().defaultContent();
        }
    }
}
