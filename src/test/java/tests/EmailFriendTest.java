package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.EmailPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class EmailFriendTest extends TestBase
{
	HomePage homeObject; 
	UserRegistrationPage registerObject; 
	LoginPage loginObject; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchObject; 
	ProductDetailsPage detailsObject;
	EmailPage emailObject;

	String uniqueEmailForRegisteration;

	public EmailFriendTest()
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
			searchObject.ProductSearchUsingAutoSuggest("MacBo");
			detailsObject = new ProductDetailsPage(driver); 
			Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);
		} catch (Exception e) {
			System.out.println("Error occurred  " + e.getMessage());
		}
	}
	
	// 3- Email to Friend
	@Test(priority=3)
	public void RegisteredUserCanSendProductToFriend() 
	{
		detailsObject.openSendEmail();
		emailObject = new EmailPage(driver);
		System.out.println("Mail:  " + uniqueEmailForRegisteration);
		emailObject.SendEmailToFriend("aaa@tte.com", uniqueEmailForRegisteration, "Hello my friend , check this product");
		Assert.assertTrue(emailObject.messageNotification.getText().contains("Your message has been sent."));
	}
	
	// 4- User Lgoout
	@Test(priority=4)
	public void RegisteredUserCanLogout() 
	{
		registerObject.userLogout();
	}
	
}
