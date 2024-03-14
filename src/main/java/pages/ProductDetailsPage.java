package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage extends PageBase
{
	public static WebDriverWait wait;

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(css="strong.current-item")
	public WebElement productNamebreadCrumb; 

	@FindBy(css="button.button-2.email-a-friend-button")
	WebElement emailFriendBtn ; 

	@FindBy(id="price-value-4")
	public WebElement productPricelbl;

	@FindBy(id="add-to-wishlist-button-4")
	WebElement addToWishlistBtn ; 

	@FindBy(linkText="Add your review")
	WebElement addReviewLink; 

	@FindBy(xpath="//div[@class='overview-buttons']//child::button[contains(text(), 'Add to compare list')]")
	WebElement addToCompareBtn; 
	
	@FindBy(css="button.button-1.add-to-cart-button")
	WebElement addToCartBtn;

	@FindBy(xpath="//*[@id='bar-notification']//child::a[contains(text(), 'wishlist')]")
	public WebElement addedToWishListSuccessMsg;

	public void openSendEmail() 
	{
		clickButton(emailFriendBtn);
	}

	public void openAddReviewPage() 
	{
		clickButton(addReviewLink);
	}

	public void AddProductToWishlist() 
	{
		clickButton(addToWishlistBtn);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bar-notification']//child::a[contains(text(), 'wishlist')]")));
	}

	public void AddProductToCompare() 
	{
		clickButton(addToCompareBtn);
	}

	public void AddToCart() 
	{
		clickButton(addToCartBtn);
	}

}
