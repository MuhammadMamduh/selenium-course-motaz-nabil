package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTest extends TestBase
{
	HomePage home;
	ContactUsPage contactPage;

	String email = "test@test.com";
	String fullName = "Test User";
	String enquiry = "Hello Admin , this is for test";

	@Test
	@Severity(SeverityLevel.CRITICAL)
	@Description("Contact Us Test Case")
	@Link( name = "Jira", url = "http://google.com")
	@Epic("MP-6174")
	@Feature("MPF-914")
	@Flaky
	public void UserCanUseContactUs() {
		home = new HomePage(driver);
		home.openContactUsPage();
		contactPage = new ContactUsPage(driver);
		contactPage.ContactUs(fullName, email, enquiry);
		 Assert.assertTrue(contactPage.successMessage.getText()
				 .contains("Your enquiry has been successfully sent to the store owner."));
	}

}
