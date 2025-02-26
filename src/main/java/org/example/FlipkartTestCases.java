package org.example;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.FlipkartFunctions.*;
import static org.example.Main.getElementUsingPath;

public class FlipkartTestCases {
    private final WebDriver driver;

    public FlipkartTestCases(WebDriver driver) {
        this.driver = driver;
    }

    public static void testCase1(WebDriver driver, WebDriverWait wait) throws InterruptedException{
        //Dynamic Search
        searchLaptop(driver, wait);

        //Switch Window from parent to child
        switchWindow(driver);

        //Add to Cart
        addToCart(driver,wait);

        //Place Order
        placeOrder(driver, wait);
    }

    public static void testCase2(WebDriver driver, WebDriverWait wait) throws InterruptedException{
        //Dynamic Search
        searchLaptop(driver, wait);

        //Switch Window from parent to child
        switchWindow(driver);

        //Buy now button
        buyNow(driver,wait);
    }

    public static void testCase3(WebDriver driver, WebDriverWait wait) throws InterruptedException{
        //Dynamic Search
        searchLaptop(driver, wait);

        //Switch Window from parent to child
        switchWindow(driver);

        //Add to Wishlist
        addToWishlist(driver,wait);

        //Login to wishlist
        login(driver, wait);

        //load to Wishlist page
        wishlistPage(driver, wait);
    }

    public static void testCase4(WebDriver driver, WebDriverWait wait) throws InterruptedException{
        //Dynamic Search
        searchLaptop(driver, wait);

        //Switch Window from parent to child
        switchWindow(driver);

        //Add to Cart
        addToCart(driver,wait);

        //remove from cart
        removeFromCart(driver, wait);

    }

}
