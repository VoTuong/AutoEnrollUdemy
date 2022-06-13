package testcase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AutoGetUdemyCourse {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://web.telegram.org/z/#-1467024442");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


        // Click Channel
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"LeftColumn-main\"]//h3[contains(text(),'Free Courses With Certificates!')]/ancestor::div[@class='ListItem-button']"))).click();

        // Find Unread Messages tag and scroll it to top
        WebElement unreadMessage = driver.findElement(By.xpath("//*[@id=\"MiddleColumn\"]//div[@class='unread-divider local-action-message']"));
        JavascriptExecutor j = (JavascriptExecutor)driver;
        j.executeScript("arguments[0].scrollIntoView({block: 'start', inline: 'nearest'});", unreadMessage);


        // Locate unread messages > locate each message > click the first link
        List<WebElement> unreadMessages = driver.findElements(By.xpath("//*[@id=\"MiddleColumn\"]//div[@class='unread-divider local-action-message']/following-sibling::div"));

        System.out.println(unreadMessages.size());

        for (int i = 0; i < unreadMessages.size(); i++) {
            WebElement enRollLink = driver.findElement(By.xpath("//*[@id=\"MiddleColumn\"]//div[@class='unread-divider local-action-message']/following-sibling::div[" + Math.abs(i+1) +"]//a[1]"));
            String link = enRollLink.getAttribute("href");
            System.out.println(link);
            enRollLink.click();
            ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            // //a[@class='enroll_btn']
            //WebElement enrollBtn = driver.findElement(By.className("enroll_btn"));
            j.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("enroll_btn")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("enroll_btn"))).click();
        }
    }
}
