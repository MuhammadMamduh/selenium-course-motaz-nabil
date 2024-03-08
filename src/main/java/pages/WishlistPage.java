package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistPage extends PageBase
{
	public WishlistPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "td.product")
    public WebElement productCell;
    
    @FindBy(css = "h1")
    public WebElement wishlistHeader;
   
    @FindBy(id = "updatecart")
    private WebElement updateWishlistBtn;

    @FindBy(css = "button.remove-btn")
    private WebElement removefromCartCheck;
   
    @FindBy(css = "div.no-data")
    public WebElement EmptyWishListLbl;
   
    public void removeProductFromWishlist() {
        clickButton(updateWishlistBtn); // not necessary step
        clickButton(removefromCartCheck);
    }
}
