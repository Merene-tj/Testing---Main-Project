package HafsaadPages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import Base_class.Base;

public class BestSellersPage 
{
    WebDriver driver;
    WebDriverWait wait;
    Base base;

    @FindBy(xpath="(//a[text()='Best Sellers'])[1]") 
    WebElement BSellr;

    @FindBy(xpath="(//a[@href='/products/deepika-pure-viscose-saree-ruby-creme'])[1]") 
    WebElement Prdct1;

    By blouseDropdown = By.xpath("(//button[@aria-haspopup=\"listbox\"])[2]");

    By blouseOption32 = By.xpath(
        "(//button[text()='32- Padded'])[2]"
    );

    By addToCartBtn = By.id("AddToCart");

    By cartCloseBtn = By.xpath("//*[@id='mini-cart']/header/button");

    public BestSellersPage(WebDriver driver, Base base)
    {
        this.driver = driver;
        this.base = base;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void BstSeller()
    {
        base.handleControlPopup();
        wait.until(ExpectedConditions.elementToBeClickable(BSellr)).click();
    }

    public void Product1()
    {
        base.handleControlPopup();
        wait.until(ExpectedConditions.elementToBeClickable(Prdct1)).click();
    }

    public void BlouseList()
    {
        base.handleControlPopup();

        WebElement dropdown = wait.until(
                ExpectedConditions.presenceOfElementLocated(blouseDropdown));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);

        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
    }

    public void BlouseOptn()
    {
        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(blouseOption32));

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
