package org.example;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.example.Main.getElementUsingPath;

public class FlipkartFunctions {
    private final WebDriver driver;

    public FlipkartFunctions(WebDriver driver) {
        this.driver = driver;
    }

    public static void login(WebDriver driver, WebDriverWait wait){
        WebElement userName = getElementUsingPath(driver, "(//input[@class='r4vIwl BV+Dqf'])[1]");
        userName.click();
        userName.sendKeys("9760522274");
        WebElement requestOTPButton = getElementUsingPath(driver, "(//button[normalize-space()='Request OTP'])[1]");
        requestOTPButton.click();
    }

    //Place Order
    public static void placeOrder(WebDriver driver, WebDriverWait wait) {
        WebElement placeOrder = getElementUsingPath(driver, "(//button[@class='QqFHMw zA2EfJ _7Pd1Fp'])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
        placeOrder.click();
    }

    //Add to Cart
    public static void addToCart(WebDriver driver, WebDriverWait wait) {
        WebElement cartCTA = getElementUsingPath(driver, "//button[normalize-space()='Add to cart']");
        WebElement addToCart;
        if (cartCTA != null && cartCTA.isDisplayed()) {
            addToCart = cartCTA;
            System.out.println("if is executed");
        } else {
            System.out.println("else is executed");
            addToCart = getElementUsingPath(driver, "//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button");
        }
        System.out.println(addToCart);

        wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();
    }

    public static void searchLaptop(WebDriver driver, WebDriverWait wait) throws InterruptedException{
        WebElement search = getElementUsingPath(driver, "//input[@placeholder='Search for Products, Brands and More']");

        Actions actions = new Actions(driver);
        Actions searchBarAction = actions.moveToElement(search).click();
        searchBarAction.sendKeys("macbook m2").build().perform();
        Thread.sleep(Duration.ofSeconds(2));
        searchBarAction.sendKeys(" air").build().perform();

        Thread.sleep(Duration.ofSeconds(2));
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[1]/div[2]/form/ul"));

        for (WebElement ele : list) {
            String currentOption = ele.getText();
            Logger.debug("LIST ITEM: ", currentOption);
            if (currentOption.contains("macbook")) {
                ele.click();
                break;
            }
        }

        WebElement laptop = getElementUsingPath(driver, "(//div[contains(text(),'Apple MacBook AIR Apple M2 - (16 GB/256 GB SSD/macOS Sequoia) MC7X4HN/A')])");
        wait.until(ExpectedConditions.elementToBeClickable(laptop));
        laptop.click();
    }



    public static void switchWindow(WebDriver driver){
        String mainPage = driver.getWindowHandle();
        Set<String> allPages = driver.getWindowHandles();
        Iterator<String> iterator = allPages.iterator();

        while (iterator.hasNext()){
            String childWindow = iterator.next();
            if (!childWindow.equals(mainPage)) {
                driver.switchTo().window(childWindow);
                System.out.println("Switched to window: " + driver.getCurrentUrl());
                System.out.println("Switched to window: " + driver.getTitle());
            }
        }
    }

    public static void buyNow(WebDriver driver, WebDriverWait wait){
        WebElement buyNowButton = getElementUsingPath(driver, "//button[normalize-space()='Buy Now']");
        wait.until(ExpectedConditions.elementToBeClickable(buyNowButton));
        buyNowButton.click();
    }

    public static void addToWishlist(WebDriver driver, WebDriverWait wait){
        WebElement addToWishlist = getElementUsingPath(driver, "(//div[@class='+7E521'])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(addToWishlist));
        addToWishlist.click();
    }

    public static void wishlistPage(WebDriver driver, WebDriverWait wait){
        WebElement shikhaLogin = getElementUsingPath(driver, "(//div[@class='Ja1j8k'])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(shikhaLogin));

        Actions action = new Actions(driver);
        action.moveToElement(shikhaLogin);

        WebElement wishlistButton = getElementUsingPath(driver, "(//div[@class='Ja1j8k'])[1]");
        wishlistButton.click();
    }

    public static void goingToCart(WebDriver driver, WebDriverWait wait){
        WebElement clickOnCart = getElementUsingPath(driver, "(//a[@class='_3RX0a-'])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(clickOnCart));
        clickOnCart.click();
    }

    public static void removeFromCart(WebDriver driver, WebDriverWait wait){
        WebElement removeButton = getElementUsingPath(driver, "//div[normalize-space()='Remove'][1]");
        wait.until(ExpectedConditions.elementToBeClickable(removeButton));
        removeButton.click();

        WebElement confirmRemove = getElementUsingPath(driver, "(//div[@class='sBxzFz fF30ZI A0MXnh'])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(confirmRemove));
        confirmRemove.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'Successfully removed')]"))));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Successfully removed";

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

}
