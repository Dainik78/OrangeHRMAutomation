
package com.test;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;

public class LeaveAutomationTest {
    WebDriver driver;
    WebDriverWait wait;

    // Cached web elements
    WebElement firstName, middleName, lastName, email, contact, resumeInput, saveBtn;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 1: Login
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));

        // Step 2: Go to Recruitment
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Recruitment']/parent::a"))).click();

        // Step 3: Click "Add" Button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add']"))).click();

        // Step 4: Wait for form to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));

        // Cache form fields
        firstName = driver.findElement(By.name("firstName"));
        middleName = driver.findElement(By.name("middleName"));
        lastName = driver.findElement(By.name("lastName"));
        email = driver.findElement(By.xpath("//label[text()='Email']/following::input[1]"));
        contact = driver.findElement(By.xpath("//label[text()='Contact Number']/following::input[1]"));
        resumeInput = driver.findElement(By.xpath("//input[@type='file']"));
        saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
    }

    @Test
    public void addCandidate() {
        firstName.sendKeys("John");
        middleName.sendKeys("M");
        lastName.sendKeys("Doe");
        email.sendKeys("john.doe@example.com");
        contact.sendKeys("9876543210");

        File file = new File("C:\\Users\\daini\\Downloads\\Dainik_Patel_CV.pdf");
        resumeInput.sendKeys(file.getAbsolutePath());

        saveBtn.click();
        System.out.println("✅ Candidate added successfully.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
