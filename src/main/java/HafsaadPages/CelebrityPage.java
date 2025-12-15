package HafsaadPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base_class.Base;

public class CelebrityPage 
{
	 WebDriver driver;
	 WebDriverWait wait;
	 Base base;

	 @FindBy(xpath="(//a[text()=\"Celebrity Diaries\"])[1]") WebElement Celebrity;

	 @FindBy(xpath="//a[text()='Soundariya in Sinful saree']") WebElement CDPrdct1;

	 By BlsSize = By.xpath("(//button[@aria-haspopup=\"listbox\"])[3]");
	   
	 By BlsOptn = By.xpath("(//button[text()=\"34 Padded\"])[2]");

	 By Qunatity = By.xpath("(//button[@class=\"quantity-selector__button\"])[2]");

	 By Wrap = By.xpath("//input[@type=\"checkbox\"]");
	 
	 By addToCartBtn = By.xpath("//button[@id=\"addon_submit-8987211628779\"]");
	 
	 
	//constructor
	public CelebrityPage (WebDriver driver, Base base)
	{
		this.driver = driver;
	    this.base = base;
	    PageFactory.initElements(driver, this);
	    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			 
	}
	
	
	public void CelebritySari()
	{
		base.handleControlPopup();

	    WebElement CSMenu = wait.until(ExpectedConditions.visibilityOf(Celebrity));
	    CSMenu.click();

	 }

	
    public void CSProduct()
    {
        base.handleControlPopup();
        wait.until(ExpectedConditions.elementToBeClickable(CDPrdct1)).click();
    }

    public void BustSize()
    {
        base.handleControlPopup();

        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(BlsSize));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);

        wait.until(ExpectedConditions.elementToBeClickable(BlsSize)).click();
    }  
  

    public void SizeOptn()
    {
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(BlsOptn));

        option.click();
    }
   
    
    public void QuantityOptn()
    {
        WebElement QOption = wait.until(ExpectedConditions.elementToBeClickable(Qunatity));

        QOption.click();
    }   
     

    public void WrapOptn()
    {
        base.handleControlPopup();

        By wrapCheckbox = By.id("checkbox-8987211628779");

        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(wrapCheckbox));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }


    
    public void Waist()
    {
        By waistDropdown = By.xpath("//select[@name='properties[Waist]']");

        WebElement waist = wait.until(ExpectedConditions.visibilityOfElementLocated(waistDropdown));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", waist);

        new Select(waist).selectByValue("34");
    }


    
    public void Height()
    {
        By heightDropdown = By.xpath("//select[@name='properties[Height]']");

        WebElement height = wait.until(ExpectedConditions.visibilityOfElementLocated(heightDropdown));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", height);

        new Select(height).selectByVisibleText("Regular (5.2 to 5.4 feet in height)");
    }


    
    public void AddCart()
    {
        base.handleControlPopup();

        By addonBtn = By.xpath("//button[starts-with(@id,'addon_submit-')]");
        By cartCloseBtn = By.cssSelector("button.drawer__close-button[data-action='close']");

        WebElement addBtn = wait.until(driver ->
        {
            // Priority: Add-on button (Celebrity Saree)
            if (!driver.findElements(addonBtn).isEmpty())
            {
                WebElement btn = driver.findElement(addonBtn);
                return btn.isEnabled() ? btn : null;
            }

            // Fallback: Normal Add to Cart
            if (!driver.findElements(addToCartBtn).isEmpty())
            {
                WebElement btn = driver.findElement(addToCartBtn);
                return btn.isEnabled() ? btn : null;
            }

            return null;
        });

        // Scroll to view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);
        
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", addBtn);

        // Wait for the mini cart drawer
        wait.until(ExpectedConditions.presenceOfElementLocated(cartCloseBtn));
      
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(cartCloseBtn));
        
        closeBtn.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("header.drawer__header")));
    }

    
}
