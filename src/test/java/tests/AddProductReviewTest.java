package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductReviewPage;
import pages.SearchPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class AddProductReviewTest extends TestBase
{

	/*
	 * 1- User registration
	 * 2- Search for product
	 * 3- Add reivew 
	 * 4- Logout
	 */

	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchObject ; 
	ProductDetailsPage detailsObject ;
	ProductReviewPage reviewObject ;
	String uniqueEmailForRegisteration;

	public AddProductReviewTest()
	{
		uniqueEmailForRegisteration = Helper.getUniqueEmail();
	}

	// 1- User Registration 
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() 
	{
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver); 
		registerObject.userRegistration("Moataz", "Nabil", uniqueEmailForRegisteration, "12345678");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	@Test(dependsOnMethods= {"UserCanRegisterSuccssfully"})
	public void RegisteredUserCanLogin() 
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver); 
		loginObject.UserLogin(uniqueEmailForRegisteration, "12345678");
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}
	// 2- Search For Product
	@Test(priority=2)
	public void UserCanSearchWithAutoSuggest() 
	{
		try {
			searchObject = new SearchPage(driver); 
			searchObject.ProductSearchUsingAutoSuggest("MacB");
			detailsObject = new ProductDetailsPage(driver); 
			Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);
		} catch (Exception e) {
			System.out.println("Error occurred  " + e.getMessage());
		}
	}

	// 3- Add review
	@Test(priority=3)
	public void RegisteredUserCanReviewProduct() 
	{
		detailsObject.openAddReviewPage();
		reviewObject = new ProductReviewPage(driver); 
		reviewObject.AddProductReview("new reivew", "the product is very good");
		Assert.assertTrue(reviewObject.reviewNotification.getText()
				.contains("Product review is successfully added."));
	}

	// 4- User Logout
	@Test(priority=4)
	public void RegisteredUserCanLogout() 
	{
		registerObject.userLogout();
	}

}
