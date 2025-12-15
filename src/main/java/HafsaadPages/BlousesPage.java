package HafsaadPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base_class.Base;

public class BlousesPage 
{
	WebDriver driver;
    WebDriverWait wait;
    Base base;
    
    @FindBy(xpath="//a[text()='Blouses']") WebElement Blouses;

    @FindBy(xpath="(//a[text()='Premium Blouses'])[1]") WebElement PrmBlouses;

    @FindBy(xpath="//a[text()='Bridgerton Stitched Blouses (PREBOOKING)']") WebElement BPrmPrdct1;

    By size = By.xpath("(//button[@aria-haspopup=\"listbox\"])[4]");
    
    By OptnSize = By.xpath("(//button[text()='34- Small'])[2]");

    By style = By.xpath("(//button[@aria-haspopup=\"listbox\"])[5]");
    
    By OptnStyle = By.xpath("(//button[text()='Padded'])[2]");

    By addToCartBtn = By.id("AddToCart");

		
	//constructor
	public BlousesPage (WebDriver driver, Base base)
	{
		this.driver = driver;
        this.base = base;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		 
	}
	
	public void Blouse()
	{

		base.handleControlPopup();

	    Actions actions = new Actions(driver);

	    WebElement blouseMenu =
	            wait.until(ExpectedConditions.elementToBeClickable(Blouses));

	    // Strong hover
	    actions.moveToElement(blouseMenu).perform();

	    // Small wait for animation
	    wait.until(ExpectedConditions.visibilityOf(PrmBlouses));
	}

	public void PremiuimBlouse()
	{
	    WebElement prBlouse =
	            wait.until(ExpectedConditions.elementToBeClickable(PrmBlouses));

	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView({block:'center'});", prBlouse);

	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", prBlouse);
	}

    
    public void BPrmProduct()
    {
        base.handleControlPopup();
        wait.until(ExpectedConditions.elementToBeClickable(BPrmPrdct1)).click();
    }
 
    public void BSize()
    {
        base.handleControlPopup();

        WebElement dropdown = wait.until(
                ExpectedConditions.presenceOfElementLocated(size));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);

        wait.until(ExpectedConditions.elementToBeClickable(size)).click();
    }  
    
    public void SizeBlouse()
    {
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(OptnSize));

        option.click();
    }

    public void BStyle()
    {
        base.handleControlPopup();

        WebElement dropdown = wait.until(
                ExpectedConditions.presenceOfElementLocated(style));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);

        wait.until(ExpectedConditions.elementToBeClickable(style)).click();
    } 
    
    public void StyleBlouse()
    {
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(OptnStyle));

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
