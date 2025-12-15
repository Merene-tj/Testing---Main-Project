package HafsaadPages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import Base_class.Base;

public class SareesPage 
{
    WebDriver driver;
    WebDriverWait wait;
    Base base;

    @FindBy(xpath="//a[text()='Sarees']") WebElement Sarees;

    @FindBy(xpath="(//a[@href='/collections/ready-to-wear'])[1]") WebElement RToW;

    @FindBy(xpath="(//a[@href='/products/roop-ready-to-wear-maroon'])[1]") WebElement RWPrdct1;

    By hipDropdown = By.xpath("(//button[@aria-haspopup=\"listbox\"])[4]");

    By hipOption = By.xpath("(//button[text()='34 inches'])[2]");

    By addToCartBtn = By.id("AddToCart");

    public SareesPage(WebDriver driver, Base base)
    {
        this.driver = driver;
        this.base = base;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void Saari()
    {
    	base.handleControlPopup();

        WebElement sareeMenu = wait.until(ExpectedConditions.visibilityOf(Sarees));
        Actions actions = new Actions(driver);
        actions.moveToElement(sareeMenu).pause(Duration.ofMillis(800)).perform();
        
        wait.until(ExpectedConditions.visibilityOf(RToW));

    }

    public void ReadyToWear()
    {
    	WebElement rtw = wait.until(ExpectedConditions.elementToBeClickable(RToW));

        rtw.click();
    }

    public void SPrdct()
    {
        base.handleControlPopup();
        wait.until(ExpectedConditions.elementToBeClickable(RWPrdct1)).click();
    }

    public void Hip()
    {
        base.handleControlPopup();

        WebElement dropdown = wait.until(
                ExpectedConditions.presenceOfElementLocated(hipDropdown));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);

        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
    }

    public void SizeHip()
    {
        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(hipOption));

        option.click();
    }

    public void AddCart()
    {
        base.handleControlPopup();

        By cartCloseBtn = By.cssSelector("button.drawer__close-button[data-action='close']");
        for (int i = 0; i < 2; i++)
        {
            try
            {
                WebElement addBtn = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartBtn));

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",addBtn);

                wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();

                // 🕒 Keep cart open for 3 seconds
                Thread.sleep(3000);

                // Close cart
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(cartCloseBtn));

                closeBtn.click();
                return;
            }
            catch (StaleElementReferenceException e)
            {
                // retry once
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
