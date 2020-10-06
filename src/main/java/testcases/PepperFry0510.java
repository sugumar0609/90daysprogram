package testcases;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PepperFry0510 {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(8000);
		driver.findElementByXPath("(//div[@id='regPopUp']//a)[1]").click();
		
		
		WebElement furniture = driver.findElementByXPath("//div[@id='menu_wrapper']//a");
		//a[@class='level-top active']
		
		Actions act = new Actions(driver);
		act.clickAndHold(furniture).perform();
		WebElement officeChair = driver.findElementByXPath("//div[@id='meta-furniture']/div[1]/div[3]/div[2]/div[12]/a[1]");
		officeChair.click();
		
		driver.findElementByXPath("(//div[@class='cat-wrap-img'])[2]").click();
		WebElement minHeight = driver.findElementByXPath("(//input[@type='number'])[1]");
		minHeight.clear();
		minHeight.sendKeys("50",Keys.ENTER);
		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
		driver.findElementByXPath("//div[@class='close']").click();
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-productname='Alpha Executive Chair in Dark Tan Colour']")));
		Thread.sleep(3000);
		driver.findElementByXPath("//a[@data-productname='Alpha Executive Chair in Dark Tan Colour']").click();
		WebElement bedroom = driver.findElementByXPath("(//div[@id='menu_wrapper']//a)[3]");
		Actions act2 = new Actions(driver);
		act2.clickAndHold(bedroom).perform();
		driver.findElementByXPath("//div[@id='meta-bedroom']/div[1]/div[4]/div[2]/div[3]/a[1]").click();
		driver.findElementByXPath("//label[text()='Spacewood']").click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='price7000-8000']")));
		try {
		driver.findElementByXPath("//label[@for='price7000-8000']").click();
		}catch(StaleElementReferenceException e) {
			System.out.println("StaleElementReference exception occured - trying with other xpath");
			driver.findElementByXPath("(//input[@data-key='price']/following-sibling::label)[3]").click();
		}catch(ElementClickInterceptedException e1) {
			System.out.println("ElementClickIntercepted exception occured - trying once again");
			driver.findElementByXPath("//label[@for='price7000-8000']").click();
		}
		try {
		driver.findElementByXPath("//a[@data-productname='Winner Hutch Table in Rigato Walnut Finish']").click();
		}catch(StaleElementReferenceException e) {
			System.out.println("StaleElementReference exception occured - trying with other xpath");
			Thread.sleep(3000);
			driver.findElementByXPath("(//a[@id='clip_wishlist_'])[1]").click();
		}
		
		driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();
		Thread.sleep(2000);
		String getWishlistCount = driver.findElementByXPath("(//span[@class='item_count'])[2]").getText();
		//System.out.println("getWishlistCount" + getWishlistCount);
		if(getWishlistCount.equals("2")) {
			System.out.println("Wishlist count: "+getWishlistCount);
		}else {
			System.out.println("Wishlist count is not match with exact count : "+getWishlistCount);
		}
		driver.findElementByXPath("(//a[@class='addtocart_icon'])[1]").click();
		driver.findElementByXPath("//div[@class='minicart_footer']//a").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@placeholder='Enter Your Pincode']").sendKeys("600028");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='button'])[1]")));
		driver.findElementByXPath("(//input[@type='button'])[1]").click();
		driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
		WebElement orderSummmary = driver.findElementByXPath("//div[@id='ordersummary_accordian_header']/div[2]/span[1]");
		orderSummmary.click();
		File snap = driver.getScreenshotAs(OutputType.FILE);
		File img = new File("./snaps/img.jpg");
		FileUtils.copyFile(snap, img);
		//driver.close();


	}

}
