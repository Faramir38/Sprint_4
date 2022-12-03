import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseWebTest {

    protected WebDriver driver;

    public void initWebDriver(String driverName, String url) {
        switch (driverName) {
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            default:                          //задел на будущее, пока считаем, что это Хром ("Chrome")
                driver = new ChromeDriver();
        }
        driver.get("https://qa-scooter.praktikum-services.ru/"); //открываем страницу самоката
    }
}
