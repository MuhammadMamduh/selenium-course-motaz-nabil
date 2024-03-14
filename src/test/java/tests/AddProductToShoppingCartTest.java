package tests;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class AddProductToShoppingCartTest extends TestBase 
{
	SearchPage searchPage;
	ProductDetailsPage productDetails;
	ShoppingCartPage cartPage ; 
	String productName = "Apple MacBook Pro 13-inch";
	String productURL;

	@Test(priority=1)
	public void UserCanSearchForProductsWithAutoSuggest() throws InterruptedException {
		searchPage = new SearchPage(driver);
		searchPage.ProductSearchUsingAutoSuggest("MacBoo");
		System.out.println(driver.getCurrentUrl());
		productDetails = new ProductDetailsPage(driver);
		System.out.println(driver.getCurrentUrl());
		productURL = driver.getCurrentUrl();
		Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}

	@Test(priority=2)
	public void UserCanAddProductToShoppingCart() throws InterruptedException {
		String currentURL = driver.getCurrentUrl();

		if (currentURL != productURL)
		{
			System.out.println("Sometimes for an unknown reason, the browser gets redirected to the homepage");
			System.out.println("Current URL: " + currentURL);
			System.out.println("This code is to handle this issue ...");
			driver.navigate().to(productURL);
			System.out.println("Navigating to the Product URL: " + driver.getCurrentUrl());
		}

		productDetails = new ProductDetailsPage(driver);
		
		productDetails.AddToCart();
		Thread.sleep(1000);
		driver.navigate().to("http://demo.nopcommerce.com" + "/cart");
		// Thread.sleep(1000);
		cartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600"));
	}

	@Test(priority=3)
	public void UserCanRemoveProductFromCart() {
		cartPage = new ShoppingCartPage(driver); 
		cartPage.RemoveProductFromCart();
	}
}
