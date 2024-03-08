package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends PageBase
{
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "input.button-1.checkout-as-guest-button")
	private WebElement guestBtn;

	@FindBy(id = "BillingNewAddress_FirstName")
	private WebElement fnTxt;

	@FindBy(id = "BillingNewAddress_LastName")
	private WebElement lnTxt;

	@FindBy(id = "BillingNewAddress_Email")
	private WebElement emailTxt;

	@FindBy(id = "BillingNewAddress_CountryId")
	private WebElement countryList;

	@FindBy(id = "BillingNewAddress_PhoneNumber")
	private WebElement phoneTxt;

	@FindBy(id = "BillingNewAddress_City")
	private WebElement cityTxt;

	@FindBy(id = "BillingNewAddress_Address1")
	private WebElement addressTxt;

	@FindBy(id = "BillingNewAddress_ZipPostalCode")
	private WebElement postCodeTxt;

	@FindBy(xpath = "//div[@id='billing-buttons-container']//child::button[contains(text(), 'Continue')]")
	private WebElement addressContinueBtn;

	@FindBy(id = "shippingoption_1")
	private WebElement shippingMethodRdo;

	@FindBy(css = "button.button-1.shipping-method-next-step-button")
	private WebElement shippingContinueBtn;

	@FindBy(css = "button.button-1.payment-method-next-step-button")
	private WebElement paymentContinueBtn;

	@FindBy(css = "button.button-1.payment-info-next-step-button")
	private WebElement paymentInfoContinueBtn;

	@FindBy(css = "button.button-1.confirm-order-next-step-button")
	private WebElement confirmBtn;

	@FindBy(css = "a.product-name")
	public WebElement productName;



	@FindBy(css = "h1")
	public WebElement ThankYoulbl;

	@FindBy(css = "div.title")
	private WebElement successMessage;

	@FindBy(linkText = "Click here for order details.")
	private WebElement orderDetailsLink;


	public void RegisteredUserCheckoutProduct(String email, String countryName, String address, 
			String postcode, String phone, String city, String productName) throws InterruptedException {
		
		setTextElementText(fnTxt, "Moataz");
		setTextElementText(lnTxt, "Nabil");
		clearText(emailTxt);
		setTextElementText(emailTxt, email);

		select = new Select(countryList);
		select.selectByVisibleText(countryName);
		setTextElementText(cityTxt, city);
		setTextElementText(addressTxt, address);
		setTextElementText(postCodeTxt, postcode);
		setTextElementText(phoneTxt, phone);
		clickButton(addressContinueBtn);
		Thread.sleep(1000);
		clickButton(shippingContinueBtn);
		Thread.sleep(1000);
		clickButton(paymentContinueBtn);
		Thread.sleep(1000);
		clickButton(paymentInfoContinueBtn);
	}

	public void confirmOrder() throws InterruptedException 
	{
		clickButton(confirmBtn);
		Thread.sleep(2000);
	}

	public void viewOrderDetails() {
		clickButton(orderDetailsLink);
	}


	public void CheckoutProduct(String firstName, String lastName, String countryName,
			String email, String address, String postcode, 
			String phone, String city, String productName) throws InterruptedException {
		setTextElementText(fnTxt, firstName);
		setTextElementText(lnTxt, lastName);
		setTextElementText(emailTxt, email);
		select = new Select(countryList);
		select.selectByVisibleText(countryName);
		setTextElementText(cityTxt, city);
		setTextElementText(addressTxt, address);
		setTextElementText(postCodeTxt, postcode);
		setTextElementText(phoneTxt, phone);
		clickButton(addressContinueBtn);
		clickButton(shippingMethodRdo);
		clickButton(shippingContinueBtn);
		Thread.sleep(2000);
		clickButton(paymentContinueBtn);
		Thread.sleep(2000);
		clickButton(paymentInfoContinueBtn);
		Thread.sleep(2000);
		clickButton(confirmBtn);
	}
}
