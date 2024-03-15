package tests;



import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import utilities.Helper;

public class UserRegistrationTestWithDDTAndCSV extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 

	CSVReader reader ;
	CSVWriter writer ;

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() throws IOException 
	{
		// get path of CSV file 
		String CSV_file = System.getProperty("user.dir") + "/src/test/java/data/UserData.csv";
		reader = new CSVReader(new FileReader(CSV_file)); 
		String[] csvCell ; 

		try 
		{
			// while loop will be executed till the lastvalue in CSV file . 
			while((csvCell = reader.readNext()) != null) 
			{
				String firstname = csvCell[0]; 
				String lastName = csvCell[1];
				String email = csvCell[2];

				int index = email.indexOf("@");
				email = email.substring(0, index) +
						System.currentTimeMillis() +
						email.substring(index);

				String password = csvCell[3]; 

				homeObject = new HomePage(driver); 
				homeObject.openRegistrationPage();
				registerObject = new UserRegistrationPage(driver); 
				registerObject.userRegistration(firstname, lastName, email, password);
				Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
				// registerObject.userLogout();
				homeObject.openLoginPage();
				loginObject = new LoginPage(driver); 
				loginObject.UserLogin(email,password);
				Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
				registerObject.userLogout();
			}
		}
		catch (Exception e)
		{
			System.out.println("Exceptionnnnnnnnnnnnn: " + e.getMessage());
		}
	}
}
