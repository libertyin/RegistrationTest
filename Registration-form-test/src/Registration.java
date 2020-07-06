import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class Registration {
	
	WebDriver driver;
	
	public void launchBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); //chrome driver connection
		driver = new ChromeDriver();
		driver.manage().window().maximize();//maximize window 
		driver.get("https://www.1000000bet.com/"); //get the URL 
		
	}
	
	public void RegisterPositiveOne() throws InterruptedException{	
		
		Thread.sleep(2000); //Wait 2s
		
		driver.findElement(By.cssSelector(".cookie-message-holder .btn-accept-cookie")).click();//accept cookie
		driver.findElement(By.cssSelector("#header .header-register-button")).click(); //chick Register Now!
		
//----------registration 1st step------------
		Thread.sleep(1000);
		
		// create random class 
        Random rand = new Random(); 
  
        // Generate random integers in range 0 to 999 
        int rand_int1 = rand.nextInt(1000); 
        int rand_int2 = rand.nextInt(1000); 
		
		//write email,name.pwd
		driver.findElement(By.id("reg_form_email")).sendKeys("anna.test" + rand_int1 + "@gmail.com");
		driver.findElement(By.id("reg_form_username")).sendKeys("anna_test" + rand_int2);
		driver.findElement(By.id("reg_form_password")).sendKeys("Ti1234");
		
		//verify buttons Pay and Play, Facebook, Google in the form is visible
		//pay and play 
		if(driver.findElement(By.id("quickDepositButton")).isDisplayed()){
			System.out.println("Pay and Play is visible");
		} else {
			System.out.println("Pay and Play is hidden");
		}
		//facebook
		if(driver.findElement(By.cssSelector(".btn-facebook-login")).isDisplayed()){
		System.out.println("Facebook is visible");
		} else {
			System.out.println("Facebook is hidden");
		}
		//google 
		if(driver.findElement(By.cssSelector(".btn-google-login")).isDisplayed()){
		System.out.println("Google is visible");
		} else {
		System.out.println("Google is hidden");
		}
		
		driver.findElement(By.id("next-registration-step")).click(); //chick Next
	}
	
	public void RegisterPositiveTwo() throws InterruptedException {
		
//-----------registration 2nd step-----------
		Thread.sleep(2000);//wait 2s 
				
		//check gender selected 
		if (driver.findElement(By.id("reg_form_gender_male")).isSelected()) {
			System.out.println("Gender is selected");
		}else System.out.println("Gender is NOT selected");
			
		//write name
		driver.findElement(By.id("reg_form_fname")).sendKeys("Anna");
		driver.findElement(By.id("reg_form_lname")).sendKeys("Kyriienko");
				
		//check country selected 
		Select menuCountry = new Select(driver.findElement(By.id("reg_form_country_id")));
		WebElement optionCountry = menuCountry.getFirstSelectedOption();
		String contentCountry = optionCountry.getText();
		System.out.println("Country is " + contentCountry);
		
		//check currency selected 
		Select menuCurrency = new Select(driver.findElement(By.id("reg_form_currency_id")));
		WebElement optionCurrency = menuCurrency.getFirstSelectedOption();
		String contentCurrency= optionCurrency.getText();
		System.out.println("Currency is " + contentCurrency);
		
		//check phone prefix
		Select menuPhone = new Select(driver.findElement(By.id("reg_form_phone_prefix")));
		WebElement optionPhone = menuPhone.getFirstSelectedOption();
		String contentPhone= optionPhone.getText();
		System.out.println("Comparing phone prefix " + contentPhone + " and country " + contentCountry 
                + " : " + contentPhone.equals(contentCountry)); //check if phone prefix equals county
		
		//write phone
		driver.findElement(By.id("reg_form_cell_phone")).sendKeys("892004564");
				
		//choose day, month ,year of birth
		Select menuDay = new Select(driver.findElement(By.id("reg_form_birthday_day")));
		menuDay.selectByIndex(1);
		Select menuMonth = new Select(driver.findElement(By.id("reg_form_birthday_month")));
		menuMonth.selectByIndex(1);
		Select menuYear = new Select(driver.findElement(By.id("reg_form_birthday_year")));
		menuYear.selectByIndex(1);

		//check Terms & Conditions selected 
		if (driver.findElement(By.id("reg_form_terms_accepted")).isSelected()) {
			System.out.println("Terms & Conditions is selected");
		}else System.out.println("Terms & Conditions is NOT selected");
				
		//check Newsletters selected 
		if (driver.findElement(By.id("reg_form_newsletter")).isSelected()) {
			System.out.println("Newsletters is selected");
		}else System.out.println("Newsletters is NOT selected");
	
		//chick Next
		driver.findElement(By.id("submit-reg-form")).click(); 
			
	}
	
	public void RegisterPositiveFinish() throws InterruptedException, IOException {
		Thread.sleep(2000);//wait 2s 
	
		//take screenshot into the file
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File ("./Screenshot/screenshot.png");
		Files.copy(source, dest);
		
		//close window and end process 
		driver.close();
		System.exit(0);
	}
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Registration obj = new Registration();
		obj.launchBrowser();
		obj.RegisterPositiveOne();
		obj.RegisterPositiveTwo();
		obj.RegisterPositiveFinish();
		
	}

}
