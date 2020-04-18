import net.bytebuddy.description.type.TypeList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Main {

    WebDriver driver;
    String name ="janusztestowy";
    String password ="***";

    @BeforeEach
    public void Inizialize()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Test
    public void GoToMyAccount()
    {
        Login(name,password);
    }

    public void Login(String name, String password)
    {
        driver.navigate().to("https://www.zooniverse.org");
        WebElement signIn = driver.findElement(By.xpath("//button[@value='sign-in']"));
        signIn.click();
        WebElement nameField = driver.findElement(By.xpath("//input[@name='login']"));
        nameField.sendKeys(name);
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys(password);
        WebElement sumbit =driver.findElement(By.xpath("//button[@type='submit']"));
        sumbit.click();
        String nameOnAccount  =driver.findElement(By.xpath("//span[@class='account-bar']/button/span/strong")).getText().toLowerCase();
        System.out.println(nameOnAccount);
        Assertions.assertTrue(nameOnAccount.contains(name),"Login: " + name+ " NIE został zalogowany prawidłowo");
    }
}