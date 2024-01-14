package tests;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import utils.DriverInstance;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tests {
    private WebDriver driver;
    private final String baseUrl = "https://www.demoblaze.com";

    private final String USER_NAME = "user1000p";
    private final String PASSWORD = "user1000p";

    @Before
    public  void setup(){
        DriverInstance.init();
    }

    @Test
    public void test1() throws InterruptedException{
        DriverInstance.driver.get(baseUrl);
        HomePage homePage = new HomePage(DriverInstance.driver);
        WebDriverWait w = new WebDriverWait(DriverInstance.driver, 3);

        //Generate random user and password
        Random rand = new Random();
        int randomPassword = 1000 + rand.nextInt(1000);
        String username = "username_" + randomPassword;
        String password = String.valueOf(randomPassword);

        //Create new user
        homePage.signUp(username,password);
        w.until(ExpectedConditions.alertIsPresent());
        assertEquals("Sign up successful.", DriverInstance.driver.switchTo().alert().getText());
        DriverInstance.driver.switchTo().alert().accept();
        Thread.sleep(1000);

        //Try to create same user
        homePage.signUp(username,password);
        w.until(ExpectedConditions.alertIsPresent());
        assertEquals("This user already exist.", DriverInstance.driver.switchTo().alert().getText());
        DriverInstance.driver.switchTo().alert().accept();
        Thread.sleep(1000);
        homePage.clickCloseSignUp();

        //login
        homePage.login(username, password);
        w.until(ExpectedConditions.presenceOfElementLocated(homePage.LBL_WELCOME_USER));

        //logout
        homePage.clickLogout();
        w.until(ExpectedConditions.invisibilityOfElementLocated(homePage.LBL_WELCOME_USER));

        //login invalid
        homePage.login(username+"_", password);
        w.until(ExpectedConditions.alertIsPresent());
        assertEquals(DriverInstance.driver.switchTo().alert().getText(), "User does not exist.");
        DriverInstance.driver.switchTo().alert().accept();
        homePage.typeLoginUsername(username);
        homePage.typeLoginPassword(password+"_");
        homePage.clickConfirmLogin();
        w.until(ExpectedConditions.alertIsPresent());
        assertEquals(DriverInstance.driver.switchTo().alert().getText(), "Wrong password.");
    }

    @Test
    public void test2() throws InterruptedException{
        DriverInstance.driver.get(baseUrl);
        HomePage homePage = new HomePage(DriverInstance.driver);
        ProductPage productPage = new ProductPage(DriverInstance.driver);
        CartPage cartPage = new CartPage(DriverInstance.driver);
        WebDriverWait w = new WebDriverWait(DriverInstance.driver, 3);

        //login
        homePage.login(USER_NAME, PASSWORD);
        w.until(ExpectedConditions.presenceOfElementLocated(homePage.LBL_WELCOME_USER));

        //Add first product to cart
        addProductToCart(1, homePage, productPage);
        w.until(ExpectedConditions.alertIsPresent());
        assertEquals("Product added.", DriverInstance.driver.switchTo().alert().getText());
        DriverInstance.driver.switchTo().alert().accept();

        homePage.clickHome();

        //Add second product 2 to cart
        addProductToCart(2, homePage, productPage);
        w.until(ExpectedConditions.alertIsPresent());
        assertEquals("Product added.", DriverInstance.driver.switchTo().alert().getText());
        DriverInstance.driver.switchTo().alert().accept();

        homePage.clickCart();

        cartPage.clickDeleteCartProduct(1);
        Thread.sleep(1000);
        cartPage.clickPlaceOrder();

        cartPage.typeName("Darwin");
        cartPage.typeCountry("Colombia");
        cartPage.typeCity("Medellin");
        cartPage.typeCard("0101");
        cartPage.typeMonth("01");
        cartPage.typeYear("2020");
        cartPage.clickPurchase();

        w.until(ExpectedConditions.presenceOfElementLocated(cartPage.LBL_THANK_FOR_PURCHASE));
    }

    @Test
    public void test3() throws InterruptedException{
        DriverInstance.driver.get(baseUrl);
        HomePage homePage = new HomePage(DriverInstance.driver);
        ProductPage productPage = new ProductPage(DriverInstance.driver);
        CartPage cartPage = new CartPage(DriverInstance.driver);
        WebDriverWait w = new WebDriverWait(DriverInstance.driver, 3);

        //login
        homePage.login(USER_NAME, PASSWORD);
        w.until(ExpectedConditions.presenceOfElementLocated(homePage.LBL_WELCOME_USER));

        //Add first product to cart
        addProductToCart(1, homePage, productPage);
        w.until(ExpectedConditions.alertIsPresent());
        assertEquals("Product added.", DriverInstance.driver.switchTo().alert().getText());
        DriverInstance.driver.switchTo().alert().accept();

        homePage.clickHome();

        //Add second product 2 to cart
        addProductToCart(2, homePage, productPage);
        w.until(ExpectedConditions.alertIsPresent());
        assertEquals("Product added.", DriverInstance.driver.switchTo().alert().getText());
        DriverInstance.driver.switchTo().alert().accept();

        homePage.clickCart();

        cartPage.clickDeleteCartProduct(1);
        Thread.sleep(1000);
        String total= cartPage.getTotal();
        cartPage.clickPlaceOrder();

        cartPage.typeName("Darwin");
        cartPage.typeCountry("Colombia");
        cartPage.typeCity("Medellin");
        cartPage.typeCard("0101");
        cartPage.typeMonth("01");
        cartPage.typeYear("2020");
        cartPage.clickPurchase();

        w.until(ExpectedConditions.presenceOfElementLocated(cartPage.LBL_THANK_FOR_PURCHASE));
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(., 'Amount: "+total+" USD')]")));
        w.until(ExpectedConditions.presenceOfElementLocated(cartPage.LBL_PURCHASE_CONFIRM_NAME));
    }

    @Test
    public void test4() throws InterruptedException {
        DriverInstance.driver.get(baseUrl);
        HomePage homePage = new HomePage(DriverInstance.driver);
        WebDriverWait w = new WebDriverWait(DriverInstance.driver, 3);

        //login
        homePage.login(USER_NAME, PASSWORD);
        w.until(ExpectedConditions.presenceOfElementLocated(homePage.LBL_WELCOME_USER));

        List<String> phonesBrandList = new ArrayList<>();
        phonesBrandList.add("Samsung galaxy s6");
        phonesBrandList.add("Nokia lumia 1520");
        phonesBrandList.add("Nexus 6");
        phonesBrandList.add("Samsung galaxy s7");
        phonesBrandList.add("Iphone 6 32gb");
        phonesBrandList.add("Sony xperia z5");
        phonesBrandList.add("HTC One M9");

        List<String> laptopsBrandList = new ArrayList<>();
        laptopsBrandList.add("Sony vaio i5");
        laptopsBrandList.add("Sony vaio i7");
        laptopsBrandList.add("MacBook air");
        laptopsBrandList.add("Dell i7 8gb");
        laptopsBrandList.add("2017 Dell 15.6 Inch");
        laptopsBrandList.add("MacBook Pro");

        List<String> monitorsBrandList = new ArrayList<>();
        monitorsBrandList.add("Apple monitor 24");
        monitorsBrandList.add("ASUS Full HD");

        List<String> categoryProducts;

        homePage.clickMenu("phones");
        Thread.sleep(1000);
        categoryProducts = homePage.getProducts();
        for(int i=0;i<categoryProducts.size();i++){
            assertTrue(phonesBrandList.contains(categoryProducts.get(i)));
        }

        homePage.clickMenu("laptops");
        Thread.sleep(1000);
        categoryProducts = homePage.getProducts();
        for(int i=0;i<categoryProducts.size();i++){
            assertTrue(laptopsBrandList.contains(categoryProducts.get(i)));
        }

        homePage.clickMenu("monitors");
        Thread.sleep(1000);
        categoryProducts = homePage.getProducts();
        for(int i=0;i<categoryProducts.size();i++){
            assertTrue(monitorsBrandList.contains(categoryProducts.get(i)));
        }
    }

    private void addProductToCart(int index, HomePage homePage, ProductPage productPage) throws InterruptedException{
        homePage.clickMenu("phones");
        Thread.sleep(1000);
        homePage.clickProduct(index);
        productPage.clickAddToCart();
    }
    @After
    public void tearDown(){
        DriverInstance.quit();
    }
}
