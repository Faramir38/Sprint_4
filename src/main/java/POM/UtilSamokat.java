package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilSamokat {          //отличная идея: сделать отдельный модуль утилит
    public static void waitForVisibility (WebDriver driver, By element) {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(
                driver.findElement(element)));
    }

    public static void scrollTo(WebDriver driver, By element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(element));
    }
}
