package Config;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Actions {
    public static boolean highlightBool = true;

    public boolean waitElementVisibility(WebDriver driver, WebElement element, int seconds){
        try{
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    public boolean waitElementClickable(WebDriver driver, WebElement element, int seconds){
        try{
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    public boolean xpathExists(WebDriver driver, String xpath, int seconds){
        try{
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public boolean click(WebDriver driver, WebElement element, int seconds){
        try {
            goToElement(element, driver);
            waitElementVisibility(driver, element, seconds);
            waitElementClickable(driver, element, seconds);
            highlight(driver, element);
            element.click();
            waitPageLoad(driver);
            return true;
        }catch (Exception error) {
            Assert.fail(error.getMessage());
            return false;
        }
    }

    public void clickJs(WebDriver driver, WebElement element){
        try{
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            waitPageLoad(driver);
        }catch (Exception error) {
            Assert.fail(error.getMessage());
        }
    }

    public void clickXpathJS(WebDriver driver, String xpath, int seconds){
        try{
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
            waitPageLoad(driver);
        }catch (Exception error) {
            Assert.fail(error.getMessage());
        }
    }

    public boolean sendKeys(WebDriver driver, WebElement element, int seconds, String text){
        try{
            goToElement(element, driver);
            waitElementVisibility(driver, element, seconds);
            highlight(driver, element);
            element.clear();
            element.sendKeys(text);
            return true;
        }catch (Exception error) {
            Assert.fail(error.getMessage());
            return false;
        }
    }

    public void sendKeysWithEnter(WebDriver driver, WebElement element, int seconds, String text){
        try{
            goToElement(element, driver);
            waitElementVisibility(driver, element, seconds);
            highlight(driver, element);
            element.clear();
            element.sendKeys(text);
            element.sendKeys(Keys.ENTER);
            waitPageLoad(driver);
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public String getText(WebDriver driver, WebElement element, int seconds){
        try{
            goToElement(element, driver);
            waitElementVisibility(driver, element, seconds);
            highlight(driver, element);
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return element.getText();
    }

    public String getTextJS(WebDriver driver, WebElement element) {
       return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value", element);
    }

    public List<String> getTextList(List<WebElement> listWebElements, WebDriver driver){
        List<String> strList = new LinkedList<>();
        for (WebElement columnFields : listWebElements) {
            highlight(driver,columnFields);
            strList.add(columnFields.getText().trim());
        }
        return strList;
    }

    public String getAttribute(WebDriver driver, WebElement element, String attribute) {
        waitPageLoad(driver);
        goToElement(element, driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
        highlight(driver, element);
        return element.getAttribute(attribute);
    }

    private boolean isCheckboxChecked(WebElement chk, WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOf(chk)).getAttribute("checked")!= null;
    }

    public void checkboxCheck(WebElement chk, WebDriver driver, WebDriverWait wait){
        if(!isCheckboxChecked(chk, wait)){
            click(driver, chk, 10);
        }
    }

    public void checkboxUncheck(WebElement chk, WebDriver driver, WebDriverWait wait){
        if(isCheckboxChecked(chk, wait)){
            click(driver, chk, 10);
        }
    }

    public void waitSeconds(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (Exception error){
            error.printStackTrace();
        }
    }

    public void goToElement(WebElement element, WebDriver driver){
        try {
            waitSeconds(1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            waitSeconds(1);
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    public static void highlight(WebDriver driver, WebElement element){
        if(highlightBool){
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", element);
            }
        }
    }

    public void waitPageLoad(WebDriver driver) {
        try {
            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                }
            };
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(pageLoadCondition);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    private String acceptAlert(WebDriver driver){
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }
}