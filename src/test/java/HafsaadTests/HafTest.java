package HafsaadTests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Base_class.Base;
import HafsaadPages.BestSellersPage;
import HafsaadPages.BlousesPage;
import HafsaadPages.CelebrityPage;
import HafsaadPages.SareesPage;
import HafsaadPages.SignInPage;
import HafsaadPages.SignUpPage;


public class HafTest extends Base
{
	//WebDriver driver;	
	
@Test (enabled=false)	
	public void SignUp()
	{
		
		SignUpPage obj = new SignUpPage(driver);
		obj.SignUp();
		obj.NewAcnt();
		obj.FirstName("Merene");
		obj.LastName("T J");
		obj.Email("t.j.mereee@gmail.com");
		obj.Passwrd("HiMere@2135");
		obj.submit();
		
	}

@Test (enabled=false)	
	public void SignIn()
	{
		
		SignInPage obj = new SignInPage(driver);
		obj.Signin();
		obj.MailId("t.j.mereee@gmail.com");
		obj.Password("HiMere@2135");
		obj.SignInBtn();
		
	}


@Test (priority=0)	
	public void orderBestSellers()
	{
		BestSellersPage obj = new BestSellersPage(driver, this);
		
		obj.BstSeller();
		obj.Product1();
		obj.BlouseList();
		obj.BlouseOptn();
		obj.AddCart();		
	}

@Test (priority=1)
	public void OrderSaree()
	{
		SareesPage obj = new SareesPage(driver, this);
		
		obj.Saari();
		obj.ReadyToWear();
		obj.SPrdct();
		obj.Hip();
		obj.SizeHip();
		obj.AddCart();
	}


@Test (priority=2)
	public void OrderBlouse()
	{
		BlousesPage obj = new BlousesPage(driver, this);
		
		obj.Blouse();
		obj.PremiuimBlouse();
		obj.BPrmProduct();
		obj.BSize();
		obj.SizeBlouse();
		obj.BStyle();
		obj.StyleBlouse();
		obj.AddCart();

	}


@Test(priority=3)
	public void OrderCelebritySaris()
	{
		CelebrityPage obj = new CelebrityPage(driver, this);
		
		obj.CelebritySari();
		obj.CSProduct();
		obj.BustSize();
		obj.SizeOptn();
		obj.QuantityOptn();
		obj.WrapOptn();
		obj.Waist();
		obj.Height();
		obj.AddCart();
		
	}


@Test(priority=4)	
	public void Cart() throws InterruptedException, IOException
	{
	
		WebElement HCart = driver.findElement(By.xpath("//a[@href=\"/cart\"]"));
		HCart.click();
		Thread.sleep(2000);		
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
		FileUtils.copyFile(screenshot, new File(".//screenshot//CartScreen.png"));
				
				
	}

}
