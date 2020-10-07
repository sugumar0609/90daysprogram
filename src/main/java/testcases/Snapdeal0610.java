package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal0610 {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement category = driver.findElementByXPath("//span[contains(text(),'Toys, Kids')]");
		Actions act = new Actions(driver);
		act.clickAndHold(category).perform();
		driver.findElementByXPath("//span[text()='Toys']").click();
		driver.findElementByXPath("//div[text()='Educational Toys']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//input[@type='radio']/following-sibling::label)[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//div[@data-name='discount']//div)[5]").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class='sd-input'])[2]")));
		driver.findElementByXPath("(//input[@class='sd-input'])[2]").sendKeys("600028");
		driver.findElementByXPath("//button[text()='Check']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='product-title'])[1]")));
		WebElement productTitle = driver.findElementByXPath("(//p[@class='product-title'])[1]");
		Actions act1 = new Actions(driver);
		act1.moveToElement(productTitle);
		act1.clickAndHold().perform();
		Thread.sleep(1000);
		driver.findElementByXPath("(//div[contains(text(),'Quick View')])[1]").click();
		
		driver.findElementByXPath("//a[contains(text(),'view details')]").click();
		
		String price = driver.findElementByXPath("//span[@class='payBlkBig']").getText();
		System.out.println("Price of the product : "+price);
		int priceOfProduct;
		if(price.contains(",")) {
			priceOfProduct = Integer.parseInt(price.trim().replace(",", ""));
		}else {
			priceOfProduct = Integer.parseInt(price.trim());
		}
		
		String deliveryCharge = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		System.out.println("Delivery charge of the product : "+deliveryCharge);
		int priceWthDeliveryChrge = 0;
		if(deliveryCharge.trim().equals("Free")) {
			 priceWthDeliveryChrge = priceOfProduct + 0;
			 System.out.println("priceWthDeliveryChrge : "+priceWthDeliveryChrge);
		}else {
			String[] splitDeliveryChrge = deliveryCharge.split(" ");
			int length = splitDeliveryChrge.length;
			int deliveryChrgeInInt = Integer.parseInt(splitDeliveryChrge[length-1]);
			 priceWthDeliveryChrge = priceOfProduct + deliveryChrgeInInt;
			 System.out.println("priceWthDeliveryChrge : "+priceWthDeliveryChrge);
		}
		driver.findElementByXPath("(//span[@class='intialtext'])[1]").click();
		String finalPrice = driver.findElementByXPath("(//span[@class='price'])[2]").getText();
		System.out.println("Final price of first product: "+finalPrice);
		String[] splitedFinalPrice = finalPrice.split(" ");
		int intFinalPrice;
		if(splitedFinalPrice[1].trim().contains(",")) {
		intFinalPrice = Integer.parseInt(splitedFinalPrice[1].trim().replace(",", ""));
		}else {
		intFinalPrice = Integer.parseInt(splitedFinalPrice[1].trim());
		}
		if(priceWthDeliveryChrge == intFinalPrice) {
			 System.out.println("Final price is equal with product price and delivery charge");
		 }else {
				 System.out.println("Final price is not equal with product price and delivery charge");
		 }
		driver.findElementByXPath("(//input[@name='keyword'])[1]").sendKeys("Sanitizer",Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//p[@class='product-title']"))));
		List<WebElement> sanitizers = driver.findElementsByXPath("//p[@class='product-title']");
		for(int i = 0;i<sanitizers.size();i++) {
			if(sanitizers.get(i).getText().trim().equals("Vedic Valley Hand Sanitizer 5000 mL Pack of 1")) {
				sanitizers.get(i).click();
				break;
			}/*else {
				System.out.println("There is no such product");
				//throw new RuntimeException("There is no expected product available");
			}*/
		}
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowRefs = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowRefs.get(1));
		
		String sanitizerPrice = driver.findElementByXPath("//span[@class='payBlkBig']").getText();
		System.out.println("Price of the Sanitizer : "+sanitizerPrice);
		int sanitizerPriceInInt;
		if(price.contains(",")) {
			sanitizerPriceInInt = Integer.parseInt(sanitizerPrice.trim().replace(",", ""));
		}else {
			sanitizerPriceInInt = Integer.parseInt(sanitizerPrice.trim());
		}
		
		String deliveryChargeOfSanitizer = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		System.out.println("Delivery charge of the product : "+deliveryChargeOfSanitizer);
		int sanitizerPriceWthDeliveryChrge = 0;
		if(deliveryChargeOfSanitizer.trim().equals("Free")) {
			sanitizerPriceWthDeliveryChrge = sanitizerPriceInInt + 0;
			 System.out.println("sanitizerPriceWthDeliveryChrge : "+sanitizerPriceWthDeliveryChrge);
		}else {
			String[] splitDeliveryChrgeOfSanitizer = deliveryChargeOfSanitizer.split(" ");
			int length = splitDeliveryChrgeOfSanitizer.length;
			int deliveryChrgeOfSanitizerInInt = Integer.parseInt(splitDeliveryChrgeOfSanitizer[length-1]);
			sanitizerPriceWthDeliveryChrge = sanitizerPriceInInt + deliveryChrgeOfSanitizerInInt;
			 System.out.println("sanitizerPriceWthDeliveryChrge : "+sanitizerPriceWthDeliveryChrge);
		}
		
		driver.findElementByXPath("(//div[@id='add-cart-button-id'])[1]").click();
		Thread.sleep(4000);
		driver.findElementByXPath("//span[@class='cartTextSpan']").click();
		String totalPrice = driver.findElementByXPath("//input[@type='button']").getAttribute("value");
		System.out.println(totalPrice);
		String[] splitedTotalPrice = totalPrice.split("\\.");
		
		int intTotalPrice;
		if(splitedTotalPrice[1].trim().contains(",")) {
			intTotalPrice = Integer.parseInt(splitedTotalPrice[1].trim().replace(",", ""));
		}else {
			intTotalPrice = Integer.parseInt(splitedTotalPrice[1].trim());
		}
		int totalPriceOfBothProduct = sanitizerPriceWthDeliveryChrge + priceWthDeliveryChrge;
		
		
		if(totalPriceOfBothProduct == intTotalPrice) {
			System.out.println("Total amount is equal to sum of both products");
		 }else {
			 System.out.println("Total amount is not equal to sum of both products");
		 }		
		//driver.quit();
	}

}
