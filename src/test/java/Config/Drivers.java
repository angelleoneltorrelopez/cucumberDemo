package Config;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

public class Drivers {
    /* macOS */
    public static WebDriver macOSChrome() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/macOS/Chrome/chromedriver");
        ChromeOptions options = new ChromeOptions();
        //  options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "/download");
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1366,768));
        return driver;
    }

    public static WebDriver macOSFirefox() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/macOS/Firefox/geckodriver");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().setSize(new Dimension(1366,768));
        return driver;
    }

    public static WebDriver macOSEdge() {
        System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/macOS/Edge/msedgedriver");
        EdgeDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1366,768));
        return driver;
    }

    public static WebDriver macOSSafari() {
        SafariDriver driver = new SafariDriver();
        driver.manage().window().setSize(new Dimension(1366,768));
        return driver;
    }

    /* Linux */
    public static WebDriver linuxFirefox() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/Linux/Firefox/geckodriver");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().setSize(new Dimension(1366,768));
        return driver;
    }

    public static WebDriver linuxChrome() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/Linux/Chrome/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366,768));
        return driver;
    }

    /* Windows */
    public static WebDriver windowsFirefox() {
        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\Windows\\Firefox\\geckodriver.exe");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().setSize(new Dimension(1366,768));
        return driver;
    }

    public static WebDriver windowsChrome() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\Windows\\Chrome\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366,768));
        return driver;
    }

    public static WebDriver windowsEdge() {
        System.setProperty("webdriver.edge.driver", "src\\test\\resources\\drivers\\Windows\\Edge\\msedgedriver.exe");
        EdgeDriver driver = new EdgeDriver();
        driver.manage().window().setSize(new Dimension(1366,768));
        return driver;
    }
}