package testcases;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon0710 {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		//1. Launch URL: https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//2. Type "oneplus 7 pro mobiles" in Search Box and Enter
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 7 pro mobiles",Keys.ENTER);
		Thread.sleep(2000);
		//3. Print the price of the first resulting mobile
		String price = driver.findElement(By.xpath("//span[text()='38,368']")).getText();
		System.out.println(price);
		//4. Click on the Mobile (First resulting) image
		driver.findElement(By.xpath("//img[@alt='(Renewed) OnePlus 7 Pro (Mirror Grey, 8GB RAM, 256GB Storage)']")).click();
		//5. Switch to the new window
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();
		for (String each : allWindow) {
			if(!each.equals(parentWindow)) {
				driver.switchTo().window(each);
			}
		}
		
		//6. Print the number of customer ratings
		String ratings = driver.findElement(By.xpath("//span[text()='56 ratings']")).getText();
		System.out.println(ratings);
		//7. Click 'Add to Cart'
		driver.findElement(By.id("add-to-cart-button")).click();
		//8. Confirm "Added to Cart" text message appeared
		String original="Added to Cart";
		String addedToCart = driver.findElement(By.xpath("//h1[@class='a-size-medium a-text-bold']")).getText();
		if(original.equals(addedToCart)) {
			System.out.println("checked msg is correct");
		}
		//9. Click on Proceed to checkout
		driver.findElement(By.id("hlb-ptc-btn-native")).click();
		//10. Confirm the title is "Amazon Sign In"
		int count=0;
		Set<String> allWindow1 = driver.getWindowHandles();
		for (String each : allWindow1) {
			if(!each.equals(parentWindow)) {
				driver.switchTo().window(each);
			}
		}
		String originalTitle="Amazon Sign In";
		String title = driver.getTitle();
		if(originalTitle.equals(title)) {
			System.out.println("verified title is correct");
		}else {
			System.out.println("Incorrect title");
		}
		
		//11. Click Continue (without entering mobile number / email)
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		//12. Verify the error message: Enter your email or mobile phone number
		String enterYourEmail = driver.findElement(By.xpath("//span[@class='a-list-item']")).getText();
		System.out.println(enterYourEmail);
		//13. Close both browsers

	}

}
