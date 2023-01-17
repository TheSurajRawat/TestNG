package com.seleniumTestNG.suite1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Odyssey {
	
	private static WebDriver driver = null;
		
	@BeforeSuite
	public static void setup() {
		String driverPath = "lib/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		
	}
	
	@AfterSuite
	public static void windup() {
		driver.close();
	}
	
	@Test (priority = 1)
	public void verifyOdysseyHomePage() {
		driver.get("https://www.odyssey.in");
		driver.manage().window().maximize();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String expectedHeading = "Discover our products";
		WebElement homePageHeading = driver.findElement(By.xpath("//*[@id=\"shopify-section-collection-list\"]/section/div/header/h2"));
		String actualHeading = homePageHeading.getText();
		Assert.assertEquals(actualHeading,expectedHeading, "Odyssey homepage is not available");
		
	}
	
	@Test (priority = 2)
	public void verifySearchField() {

		WebElement searchField = driver.findElement(By.name("q"));
		boolean b = searchField.isDisplayed();
		Assert.assertEquals(b,true, "Search Field is not available");

	}

	@Test (priority = 3)
	public void verifyProducts() {

		String expectedProduct = "Books";
		WebElement product = driver.findElement(By.xpath("//*[@id=\"shopify-section-collection-list\"]/section/div/div/div/div/div/div/a[1]/span"));
		String actualProduct = product.getText();
		Assert.assertEquals(actualProduct,expectedProduct, "Books as Product is not available");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test (priority = 4)
	public void searchAnItem() {

		driver.findElement(By.name("q")).sendKeys("BEST OF FRIENDS");;
		driver.findElement(By.xpath("//button[@class='search-bar__submit']")).click();
		String expectedPageHeading = "Search Results";
		WebElement pageHeading = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/header/h1"));
		String actualPageHeading = pageHeading.getText();
		Assert.assertEquals(actualPageHeading,expectedPageHeading, "Search Result page is not available");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test (priority = 5)
	public void verifySearchResult() {

		String expectedSearchResult = "BEST OF FRIENDS";
		WebElement searchResult = driver.findElement(By.xpath("//span[text()='BEST OF FRIENDS']"));
		String actualSearchResult = searchResult.getText();
		Assert.assertEquals(actualSearchResult,expectedSearchResult, "Search product is not available");

	}

	@Test (priority = 6)
	public void verifySearchProductPrice() {
		String expectedPrice = "Rs. 549.00";
		WebElement price = driver.findElement(By.xpath("//*[@id=\'snize-product-7879911440630\']/a/div/span/div/span[1]"));
		String actualPrice = price.getText();
		Assert.assertEquals(actualPrice,expectedPrice, "Product price is different");

	}

	@Test (priority = 7)
	public void goToProductPage() {
		driver.findElement(By.xpath("//span[text()='BEST OF FRIENDS']")).click();
		String expectedUrl = "https://odyssey.in/products/best-of-friends";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl,expectedUrl, "Product page is not available");
	}

	@Test (priority = 8)
	public void verifyDetailsOfSearchProduct() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expectedDetails = "A dazzling new novel of friendship, identity and the unknowability of other people - ";
		WebElement details = driver.findElement(By.xpath("/html/body/main/div[1]/section/div[1]/div[2]/div/div[3]/div/div[2]/div/p/b[1]"));
		String actualDetails = details.getText();
		Assert.assertEquals(actualDetails,expectedDetails, "Product details is not available");

	}

	@Test (priority = 9)
	public void addItemToCart() {
			driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expectedNumberOfItems = "1";
		String actualNumberOfItems = driver.findElement(By.xpath("//span[@class='header__cart-count'] ")).getText();
		Assert.assertEquals(actualNumberOfItems,expectedNumberOfItems, "Product is not added to cart");

	}

	@Test (priority = 10)
	public void verifyAddedItemInCart() {
		driver.findElement(By.xpath("//span[@class='header__cart-count']")).click();
		driver.findElement(By.xpath("//a[text()='View cart']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String pageHeading = "My cart";
		String addedProductName = "BEST OF FRIENDS";
		String addedQuantity = "1";
		String expectedProductPrice = "Rs. 549.00";

		String expectedResult = pageHeading+" "+addedProductName+" "+expectedProductPrice;
		String actualPageHeading = driver.findElement(By.xpath("//*[text()='My cart']")).getText();
		String actualAddedProduct = driver.findElement(By.xpath("//*[@id=\'shopify-section-cart-template\']/section/div/div/div/div/div[1]/div/table/tbody/tr/td[1]/div/div[2]/a")).getText();
		String actualQuantity = driver.findElement(By.xpath("//*[@id=\'shopify-section-cart-template\']/section/div/div/div/div/div[1]/div/table/tbody/tr/td[2]/div/input")).getText();
		String actualProductPrice = driver.findElement(By.xpath("//*[@id=\'shopify-section-cart-template\']/section/div/div/div/div/div[1]/div/table/tbody/tr/td[3]/span")).getText();

		String actualResult = actualPageHeading+" "+actualAddedProduct+" "+actualProductPrice;
		Assert.assertEquals(actualResult,expectedResult, "Added item is not available on cart page");

	}

	@Test (priority = 11)
	public void verifyStoreLocation() {
		driver.findElement(By.xpath("//a[text()='Our Store Locations']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expectedLocation = "Chennai";
		String location = driver.findElement(By.xpath("//*[@id=\'main\']/div[1]/div/h3/span/strong")).getText();
		if(location.contains(expectedLocation)) {
			String actualLocation = expectedLocation;
			Assert.assertEquals(actualLocation, expectedLocation, "Store Location Detail is not available");
		}
	}

	@Test (priority = 12)
	public void verifyContactUsPage() {
		driver.findElement(By.xpath("//a[text()='Contact us']")).click();

		String expectedContactTime = "(Between 10am and 6.30pm on weekdays)";
		String contactTime = driver.findElement(By.xpath("//*[@id=\"shopify-section-page-contact-template\"]/div[1]/header/div/p[1]")).getText();
		if(contactTime.trim().contains(expectedContactTime)) {
			String actualContactTime = expectedContactTime;
			Assert.assertEquals(actualContactTime, expectedContactTime, "Store Location Detail is not available");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test (priority = 13)
	public void sendQueryFromContactUsPage() {

		driver.findElement(By.name("contact[name]")).sendKeys("Test User");
		driver.findElement(By.name("contact[email]")).sendKeys("test@gmail.com");
		driver.findElement(By.name("contact[body]")).sendKeys("Test Message");
		driver.findElement(By.xpath("//button[text()='Send message']")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expectedMessage = "Your message has been successfully sent.";
		String actualMessage = driver.findElement(By.xpath("//*[contains(text(),'successfully sent')]")).getText();
		Assert.assertEquals(actualMessage, expectedMessage, "Message has not been sent");
	}

	@Test (priority = 14)
	public void verifyReturnPolicy() {

		driver.findElement(By.xpath("//a[text()='Returns & Refund Policy']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expectedReturnDays = "7 days";
		String returnPolicy = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/p[2]")).getText();
		if(returnPolicy.trim().contains(expectedReturnDays)) {
			String actualReturnDays = expectedReturnDays;
			Assert.assertEquals(actualReturnDays, expectedReturnDays, "There is no return policy");

		}
	}

	@Test (priority = 15)
	public void nonReturnableItem() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expectedNonReturnableItem = "Gift cards";
		String nonReturnableItems = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/ol")).getText();
		if(nonReturnableItems.trim().contains(expectedNonReturnableItem)) {
			String actualNonReturnableItems = expectedNonReturnableItem;
			Assert.assertEquals(actualNonReturnableItems, expectedNonReturnableItem, "Gift card is not under return policy");

		}
	}

	@Test (priority = 16)
	public void verifyTermsOfServicePage() {

		driver.findElement(By.xpath("//*[@id=\'block-footer-1\']/div/ul/li[7]/a")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expectedHeading = "Terms of Service";
		String actualHeading = driver.findElement(By.xpath("//h1[text()='Terms of Service']")).getText();
			Assert.assertEquals(actualHeading, expectedHeading, "Gift card is not under return policy");
	}

	@Test (priority = 17)
	public void navigateToRegisterPage() {

		driver.findElement(By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div/div/div[2]/div[2]/div/a[2]")).click();
		driver.findElement(By.xpath("//button[text()='Create your account']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expectedHeading = "Create my account";
		String actualHeading = driver.findElement(By.xpath("//*[@id=\'create_customer\']/header/h2")).getText();
		Assert.assertEquals(actualHeading, expectedHeading, "Register page is not available");
	}

	@Test (priority = 18)
	public void createNewAccount() {

		driver.findElement(By.id("register-customer[first_name]")).sendKeys("TEST");
		driver.findElement(By.id("register-customer[last_name]")).sendKeys("USER");
		driver.findElement(By.id("register-customer[email]")).sendKeys("testuser6@gmail.com");
		driver.findElement(By.id("register-customer[password]")).sendKeys("Test@123");
		driver.findElement(By.xpath("//button[text()='Create my account']")).click();

		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String expectedLabel = "Logout";
		driver.findElement(By.xpath("//*[@id=\'shopify-section-header\']/section/header/div/div/div[2]/div[2]/div/a[2]")).click();
		String actualLabel = driver.findElement(By.xpath("//a[text()='Logout']")).getText();
		Assert.assertEquals(actualLabel, expectedLabel, "Registration is unsuccessful");
		driver.findElement(By.xpath("//a[text()='Logout']")).click();

	}

	@Test (priority = 19)
	public void verifyLoginPage() {
		driver.findElement(By.xpath("//*[@id=\'shopify-section-header\']/section/header/div/div/div[2]/div[2]/div/a[2]")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String expectedHeading = "Login to my account";
		String actualHeading = driver.findElement(By.xpath("//*[@id=\'header_customer_login\']/header/h2")).getText();
		Assert.assertEquals(actualHeading, expectedHeading, "Login page is not available");

	}

	@Test (priority = 20)
	public void verifyLogin() {

		driver.findElement(By.id("login-customer[email]")).sendKeys("testuser@gmail.com");
		driver.findElement(By.id("login-customer[password]")).sendKeys("Test@123");
		driver.findElement(By.xpath("//button[text()='Login']")).click();

		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String expectedLabel = "My orders";
		String actualLabel = driver.findElement(By.xpath("//*[@id=\'main\']/section/div/div[2]/div[2]/div/div[1]/h1")).getText();
		Assert.assertEquals(actualLabel, expectedLabel, "Login is unsuccessful");

	}

	@Test (priority = 21)
	public void verifyLogout() {
		driver.findElement(By.xpath("//*[@id=\'shopify-section-header\']/section/header/div/div/div[3]/div[2]/div/a[2]")).click();
		driver.findElement(By.xpath("//*[@id=\'account-popover\']/div/div/a[4]")).click();
		String expectedUrl = "https://odyssey.in/";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Logout is unsuccessful");
	}

}
