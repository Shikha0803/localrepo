package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/shikha/Downloads/chromedriver-mac-arm64/chromedriver");
        WebDriver driver = new ChromeDriver();
        DriveUtil driveUtil = new DriveUtil(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://www.google.com/");
        WebElement search1 = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
        search1.click();
        List<WebElement> list1 = driver.findElements(By.xpath("(//div[@role='presentation'])[2]"));

        for (int i = 0; i <= list1.size() - 1; i++) {
            System.out.println(list1.get(i).getText());
        }

        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        String mainPage = driver.getWindowHandle();

        //Dynamic Search
        WebElement search = driver.findElement(By.className("Pke_EE"));

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

        //Switch Window from parent to child
        Set<String> allPages = driver.getWindowHandles();
        for (String page : allPages) {
            if (!page.equals(mainPage)) {
                driver.switchTo().window(page);
                break;
            }
        }
        System.out.println(driver.getCurrentUrl());

        //Add to Cart
        addToCart(driver,wait);

        //Place Order
        placeOrder(driver, wait);

        System.out.println("done");

    }

    public static WebElement getElementUsingPath(WebDriver driver, String path) {
        try {
            return driver.findElement(By.xpath(path));
        } catch (NoSuchElementException e) {
            Logger.error(e);
        }

        return null;
    }

    //Place Order
    public static void placeOrder(WebDriver driver, WebDriverWait wait) {
        WebElement placeOrder = getElementUsingPath(driver, "//button[@class='QqFHMw zA2EfJ _7Pd1Fp']");
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

}