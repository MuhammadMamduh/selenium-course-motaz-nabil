package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class MyAccountTest extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 
	MyAccountPage myAccountObject ; 
	String oldPassword = "12345678" ; 
	String newPassword = "123456" ; 
	String firstName = "Moataz" ; 
	String lastName = "Nabil" ; 
	String uniqueEmailForRegisteration; 
	
	public MyAccountTest()
	{
		uniqueEmailForRegisteration = Helper.getUniqueEmail();
	}

	@Test(priority=1)
	public void UserCanRegisterSuccssfully() 
	{
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver); 
		registerObject.userRegistration(firstName, lastName, uniqueEmailForRegisteration, oldPassword);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	@Test(dependsOnMethods= {"UserCanRegisterSuccssfully"})
	public void RegisteredUserMustLogin() 
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver); 
		loginObject.UserLogin(uniqueEmailForRegisteration, "12345678");
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}

	@Test(priority=2)
	public void RegisteredUserCanChangePassword() 
	{
		myAccountObject = new MyAccountPage(driver) ; 
		registerObject.openMyAccountPage();
		myAccountObject.openChangePasswordPage();
		myAccountObject.ChangePassword(oldPassword, newPassword);
		Assert.assertTrue(myAccountObject.resultLbl.getText().contains("Password was changed"));
	}
	@Test(priority=3)
	public void RegisteredUserCanLogin() 
	{
		driver.navigate().to("http://demo.nopcommerce.com/");
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver); 
		loginObject.UserLogin(uniqueEmailForRegisteration, newPassword);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}
	@Test(priority=4)
	public void RegisteredUserCanLogout() 
	{
		registerObject.userLogout();
	}
}
