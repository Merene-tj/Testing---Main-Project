package HafsaadPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage 
{
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="(//a[@href=\"/account/login\"])[1]") WebElement Sgnup;
	@FindBy(xpath="//a[text()='Create an account']") WebElement NewReg;
	@FindBy(xpath="//input[@id='customer[first_name]']") WebElement FrstName;
	@FindBy(xpath="//input[@id='customer[last_name]']") WebElement LstName;
	@FindBy(id="customer[email]") WebElement EmailId;
	@FindBy(id="customer[password]") WebElement Password;
	@FindBy(xpath="//button[@class='form__submit button button--primary button--full']") WebElement RegstrSubmt;
	
	
	//constructor
	public SignUpPage (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
			 
	}
	
	
	public void SignUp()
	{
		Sgnup.click();
	}
	
	public void NewAcnt()
	{
		
		NewReg.click();
	}
	
	public void FirstName(String FirstName)
	{
		FrstName.sendKeys(FirstName);
	}
	
	public void LastName(String LastName) 
	{
		LstName.sendKeys(LastName);
		
	}
	
	public void Email(String mail)
	{
		EmailId.sendKeys(mail);
	}
	
	public void Passwrd(String Passwrd)
	{
		Password.sendKeys(Passwrd);
	}
	
	public void submit()
	{
		//wait.until(ExpectedConditions.elementToBeClickable(RegstrSubmt));
		//RegstrSubmt.click();
		//System.out.println("signedup successfully");
		
		
		try 
		{
	        driver.switchTo().frame("cmessage_form_iframe");
	        WebElement closeBtn = driver.findElement(By.cssSelector("svg")); // the X button
	        closeBtn.click();
	        driver.switchTo().defaultContent();
	        System.out.println("Popup closed successfully");
	    } 
		catch (Exception e) 
		{
	        System.out.println("Popup not present or already closed");
	    }

		
		wait.until(ExpectedConditions.elementToBeClickable(RegstrSubmt));
	    RegstrSubmt.click();
	    System.out.println("Clicked signup submit");

	    try {
	        WebElement msg = driver.findElement(By.cssSelector(".form__message"));
	        String error = msg.getText();

	        if(!error.isEmpty()) {
	            System.out.println("Signup FAILED → " + error);
	        } else {
	            System.out.println("Signup SUCCESS → No error message found");
	        }
	    } 
	    catch (Exception e) {
	        System.out.println("Signup SUCCESS → No error element found");
	    }
	}
	
	

}
