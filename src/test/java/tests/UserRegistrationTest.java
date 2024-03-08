package tests;



import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class UserRegistrationTest extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ;

	String uniqueEmail;

	public UserRegistrationTest()
	{
		uniqueEmail = Helper.getUniqueEmail();
	}

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() 
	{
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		
		System.out.println(uniqueEmail);
		registerObject.userRegistration("Moataz", "Nabil", uniqueEmail, "12345678");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	
	@Test(dependsOnMethods= {"UserCanRegisterSuccssfully"})
	public void RegisteredUserCanLogin() 
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver); 
		loginObject.UserLogin(uniqueEmail, "12345678");
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}

	@Test(dependsOnMethods= {"RegisteredUserCanLogin"})
	public void RegisteredUserCanLogout() 
	{
		registerObject.userLogout();
	}
}
