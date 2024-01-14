package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    public static final By LBL_THANK_FOR_PURCHASE = By.xpath("//h2[text()='Thank you for your purchase!']");

    public static final By LBL_PURCHASE_CONFIRM_NAME = By.xpath("//p[contains(., 'Name: Darwin')]");

    private WebDriver driver;

    @FindBy(id = "totalp")
    WebElement lblTotal;

    @FindBy(xpath = "//a[text()='Delete']")
    List<WebElement> btnDeleteCartProducts;

    @FindBy(xpath = "//button[text()='Place Order']")
    WebElement btnPlaceOrder;

    @FindBy(xpath = "//button[text()='Purchase']")
    WebElement btnPurchase;

    @FindBy(id = "name")
    WebElement txtName;

    @FindBy(id = "country")
    WebElement txtCountry;

    @FindBy(id = "city")
    WebElement txtCity;

    @FindBy(id = "card")
    WebElement txtCard;

    @FindBy(id = "month")
    WebElement txtMonth;

    @FindBy(id = "year")
    WebElement txtYear;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickDeleteCartProduct(int index){
        btnDeleteCartProducts.get(index-1).click();
    }

    public String getTotal(){
       return lblTotal.getText();
    }

    public void clickPlaceOrder(){
        btnPlaceOrder.click();
    }


    public void typeName(String value){
        txtName.clear();
        txtName.sendKeys(value);
    }

    public void typeCountry(String value){
        txtCountry.clear();
        txtCountry.sendKeys(value);
    }

    public void typeCity(String value){
        txtCity.clear();
        txtCity.sendKeys(value);
    }

    public void typeCard(String value){
        txtCard.clear();
        txtCard.sendKeys(value);
    }

    public void typeMonth(String value){
        txtMonth.clear();
        txtMonth.sendKeys(value);
    }

    public void typeYear(String value){
        txtYear.clear();
        txtYear.sendKeys(value);
    }

    public void clickPurchase(){
        btnPurchase.click();
    }
}
