
package com.test;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.UUID;

public class DemoblazeTest {
    WebDriver driver;
    WebDriverWait wait;

    String username = "test_" + UUID.randomUUID().toString().substring(0, 5);
    String password = "Test@123";

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://www.demoblaze.com");
    }

    @Test(priority = 1)
    public void signup() throws InterruptedException {
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username"))).sendKeys(username);
        driver.findElement(By.id("sign-password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        System.out.println("✅ Signed up successfully");
    }

    @Test(priority = 2)
    public void login() throws InterruptedException {
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        System.out.println("✅ Logged in successfully");
    }

    @Test(priority = 3)
    public void addToCartAndPlaceOrder() throws InterruptedException {
        driver.findElement(By.linkText("Samsung galaxy s6")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        driver.findElement(By.id("cartur")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();
        driver.findElement(By.id("name")).sendKeys("Dainik");
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("city")).sendKeys("Mumbai");
        driver.findElement(By.id("card")).sendKeys("1234123412341234");
        driver.findElement(By.id("month")).sendKeys("06");
        driver.findElement(By.id("year")).sendKeys("2025");
        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));
        System.out.println("✅ Order Placed:\n" + confirmation.getText());
        driver.findElement(By.xpath("//button[text()='OK']")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
