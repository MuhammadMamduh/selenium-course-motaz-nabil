package tests;




import org.testng.Assert;
import org.testng.annotations.Test;

import data.LoadProperties;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationWithDDTAndPropertiesFile extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 
	String firtname = LoadProperties.userData.getProperty("firstname");
	String lastname = LoadProperties.userData.getProperty("lastname"); 
	String email = LoadProperties.userData.getProperty("email"); 
	String Password = LoadProperties.userData.getProperty("password"); 

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() 
	{
		System.out.println("============================");
		System.out.println("============================");
		System.out.println(firtname);
		System.out.println(lastname);
		System.out.println(email);
		System.out.println(Password);
		System.out.println("============================");
		System.out.println("============================");
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firtname,lastname,email,Password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	
	@Test(dependsOnMethods= {"UserCanRegisterSuccssfully"})
	public void RegisteredUserCanLogin() 
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver); 
		loginObject.UserLogin(email,Password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}

	@Test(dependsOnMethods= {"RegisteredUserCanLogin"})
	public void RegisteredUserCanLogout() 
	{
		registerObject.userLogout();
	}
}
