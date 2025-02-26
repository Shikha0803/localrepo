package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.example.FlipkartTestCases.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/shikha/Downloads/chromedriver-mac-arm64/chromedriver");
        WebDriver driver = new ChromeDriver();
        DriveUtil driveUtil = new DriveUtil(driver);
        FlipkartTestCases flipkartTestCases = new FlipkartTestCases(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //testCase1(driver, wait);
        //testCase2(driver, wait);
        //testCase3(driver,wait);
        testCase4(driver,wait);

        System.out.println("done");
        //driver.quit();

    }

    public static WebElement getElementUsingPath(WebDriver driver, String path) {
        try {
            return driver.findElement(By.xpath(path));
        } catch (NoSuchElementException e) {
            Logger.error(e);
        }

        return null;
    }


}