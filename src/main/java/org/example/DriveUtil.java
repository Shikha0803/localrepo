package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriveUtil {
    private final WebDriver driver;

    public DriveUtil(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElementUsingPath(String path) {
        try {
            return driver.findElement(By.xpath(path));
        } catch (NoSuchElementException e) {
            Logger.error(e);
        }

        return null;
    }
}
