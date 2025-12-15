package HafsaadPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage 
{
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="(//a[@href=\"/account/login\"])[1]") WebElement SgnIn;
	@FindBy(xpath="//input[@id='customer[email]']") WebElement Email;
	@FindBy(xpath="//input[@id='customer[password]']") WebElement Passwrd;
	@FindBy(xpath="(//button[@class='form__submit button button--primary button--full'])[1]") WebElement SignBtn;
	
	
	//constructor
	public SignInPage (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
			 
	}
	
	
	public void Signin()
	{
		
		SgnIn.click();
	}
	
	public void MailId(String MailId)
	{
		Email.sendKeys(MailId);
	}
	
	public void Password(String Password)
	{
		Passwrd.sendKeys(Password);
	}
	
	public void SignInBtn()
	{
	   

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

		
		SignBtn.click();
		System.out.println("signedin successfully");
		//driver.navigate().to("https://hafsaad.com/account");
	}

}
