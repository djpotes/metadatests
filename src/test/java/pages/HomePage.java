package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manipulate Home page
 */
public class HomePage {

    public static final By LBL_WELCOME_USER = By.xpath("//a[@id='nameofuser' and contains(text(),'Welcome ')]");

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Home')]")
    WebElement btnHome;

    @FindBy(id = "cartur")
    WebElement btnCart;

    @FindBy(id = "login2")
    WebElement btnLogin;

    @FindBy(id = "logout2")
    WebElement btnLogout;

    @FindBy(id = "signin2")
    WebElement btnSignUp;

    @FindBy(id = "sign-username")
    WebElement txtSignUpUsername;

    @FindBy(id = "sign-password")
    WebElement txtSignUpPassword;

    @FindBy(id = "loginusername")
    WebElement txtLoginUsername;

    @FindBy(id = "loginpassword")
    WebElement txtLoginPassword;

    @FindBy(xpath = "//div[@id='logInModal']//button[text()='Log in']")
    WebElement btnConfirmLogin;

    @FindBy(xpath = "//div[@id='signInModal']//button[text()='Sign up']")
    WebElement btnConfirmSignUp;

    @FindBy(xpath = "//div[@id='signInModal']//button[text()='Close']")
    WebElement btnCloseSignUp;

    @FindBy(xpath = "//a[text()='Phones']")
    WebElement menuPhones;

    @FindBy(xpath = "//a[text()='Laptops']")
    WebElement menuLaptops;

    @FindBy(xpath = "//a[text()='Monitors']")
    WebElement menuMonitors;

    @FindBy(xpath = "//a[@class='hrefch']")
    List<WebElement> btnProducts;



    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickHome(){
        btnHome.click();
    }

    public void clickCart(){
        btnCart.click();
    }

    public void clickSignUp(){
        btnSignUp.click();
    }

    public void clickLogin(){
        btnLogin.click();
    }

    public void clickLogout(){
        btnLogout.click();
    }

    /**
     * Search items
     * @param value to input
     */
    public void typeSignUpUsername(String value){
        txtSignUpUsername.clear();
        txtSignUpUsername.sendKeys(value);
    }

    /**
     * Search items
     * @param value to input
     */
    public void typeSignUpPassword(String value){
        txtSignUpPassword.clear();
        txtSignUpPassword.sendKeys(value);
    }

    /**
     * Search items
     * @param value to input
     */
    public void typeLoginUsername(String value){
        txtLoginUsername.clear();
        txtLoginUsername.sendKeys(value);
    }

    /**
     * Search items
     * @param value to input
     */
    public void typeLoginPassword(String value){
        txtLoginPassword.clear();
        txtLoginPassword.sendKeys(value);
    }

    public void clickConfirmLogin(){
        btnConfirmLogin.click();
    }

    public void clickConfirmSignUp(){
        btnConfirmSignUp.click();
    }

    public void clickCloseSignUp(){
        btnCloseSignUp.click();
    }

    public void clickMenu(String menu){
        switch (menu){
            case "phones":
                menuPhones.click();
                break;
            case "laptops":
                menuLaptops.click();
                break;
            case "monitors":
                menuMonitors.click();
                break;
        }
    }

    public void clickProduct(int index){
        btnProducts.get(index-1).click();
    }

    public List<String> getProducts(){
        List<String> products = new ArrayList<>();
        for(int i=0;i<btnProducts.size();i++){
            products.add(btnProducts.get(i).getText());
        }
        return products;
    }

    public void login(String username, String password){
        this.clickLogin();
        this.typeLoginUsername(username);
        this.typeLoginPassword(password);
        this.clickConfirmLogin();
    }

    public void signUp(String username, String password){
        this.clickSignUp();
        this.typeSignUpUsername(username);
        this.typeSignUpPassword(password);
        this.clickConfirmSignUp();
    }

}
