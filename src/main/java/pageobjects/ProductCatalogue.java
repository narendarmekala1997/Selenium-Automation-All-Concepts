package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponets.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container"); 
    public List<WebElement> getProductsList() {
    	
    	
    	waitForElementToAppear(productsBy);
    	return products;
    }
    public WebElement getProductName(String productName) {
    	
    	WebElement prod = getProductsList().stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
    	
    	return prod;
    }
    public void addProductToCart(String productName) {
    	WebElement prod = getProductName(productName);
    	prod.findElement(addToCart).click();
    	waitForElementToAppear(toastMessage);
    	waitForElementToDisappear(spinner);
    }
    
}
