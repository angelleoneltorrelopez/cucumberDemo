package Auxiliary;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class GeneralMethods {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger( GeneralMethods.class.getName() );
    private static WebElement searched;
    public static boolean highlightBool = true;

    public boolean waitElementVisibility(WebDriver driver, WebElement element, int seconds){
        try{
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception error) {
            System.out.println(error);
            return false;
        }
    }

    public boolean waitElementClickable(WebDriver driver, WebElement element, int seconds){
        try{
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }catch (Exception error) {
            System.out.println(error);
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
        }catch (Exception e) {
            Assert.fail(e.getMessage());
            return false;
        }
    }

    public void clickJs(WebDriver driver, WebElement element){
        try{
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            waitPageLoad(driver);
        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
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
        }catch (Exception e) {
            Assert.fail(e.getMessage());
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
            LOGGER.log(Level.SEVERE, e.toString(), e);
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

    public String getAttributeFromElement(WebDriver driver, WebElement element, String attribute) {
        waitPageLoad(driver);
        goToElement(element, driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
        highlight(driver, element);
        return element.getAttribute(attribute);
    }

    public int getRandomInteger(int min, int max) {
        return ThreadLocalRandom.current().nextInt(max - min) +  min;
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

    public void selectByText(WebElement select, String option, WebDriver driver, WebDriverWait wait){
        try{
            goToElement(select, driver);
            highlight(driver, select);
            wait.until(ExpectedConditions.visibilityOf(select));
            Select options = new Select(select);
            options.selectByVisibleText(option);
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }

    public WebElement getElementFromListByText(List<WebElement> list, String text) {
        for (WebElement element : list) {
            if (element.getText().equals(text)) {
                return element;
            }
        }
        return null;
    }

    public WebElement getElementFromListByIndex(List<WebElement> list, int index) {
        if (index <= list.size()) {
            return list.get(index);
        } else {
            return null;
        }
    }

    public void setInnerHTML(WebElement webElement, String text, WebDriver driver, WebDriverWait wait) {
        try{
            this.goToElement(webElement, driver);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            ((JavascriptExecutor)driver).executeScript("var ele=arguments[0]; ele.innerHTML = '" + text + "';", webElement);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public WebElement findElementXpath(String xpath, WebDriver driver, WebDriverWait wait){
        try{
            searched =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            this.goToElement(searched,driver);
            highlight(driver, searched);
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return searched;
    }

    public List<WebElement> findElementListXpath(String xpath, WebDriver driver, WebDriverWait wait){
        List<WebElement> lista = new LinkedList<>();
        try{
            lista =wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
            this.goToElement(lista.get(0), driver);
            for (WebElement columnField : lista) {
                highlight(driver, columnField);
            }
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return lista;
    }

    public void waitSeconds(int seconds){
        long miliseconds = seconds*1000;
        try{
            Thread.sleep(miliseconds);
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }

    public String getClassName() {
        return getClass().getSimpleName();
    }

    public String getMethodFromTrace() {
        if (Thread.currentThread().getStackTrace().length > 2) {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        } else {
            return "undefined";
        }
    }

    public void goToElement(WebElement element, WebDriver driver){
        try {
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            LOGGER.log(Level.INFO,"Falla el desplazar elemento:");
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
            ExpectedCondition<Boolean> pageLoadCondition = new
                    ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                        }
                    };
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(pageLoadCondition);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Falló esperar carga de la página.");
        }
    }

    public void closeDriver(WebDriver driver){
        driver.quit();
    }

    public void loadUrl(WebDriver driver, String url){
        if (url.isEmpty()){
            Assert.fail("Empty URL given");
        }
        try {
            driver.get(url);
            waitPageLoad(driver);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private String acceptAlert(WebDriver driver){
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public void clickXpathJS(WebDriver driver, String xpath, int seconds){
        try{
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
            waitPageLoad(driver);
        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public void clickList(WebDriver driver, List<WebElement> listaWebElements, int seconds){
        for (WebElement columnFields : listaWebElements) {
            highlight(driver, columnFields);
            click(driver, columnFields, seconds);
        }
    }

    public void deleteFile(String fileName) {
        String path = System.getProperty("user.dir")+"\\downloads\\";
        File file = new File(path + fileName);
        file.delete();
    }

    public void downloadWait(String nombre, int intentos) {
        String path = System.getProperty("user.dir")+"\\downloads\\";
        File downloadfile = new File(path + "\\" + nombre);
        for(int i = 0; i < intentos; i++) {
            waitSeconds(1);
            if (downloadfile.exists() && !downloadfile.isDirectory()) {
                break;
            }
        }
    }

    public int getRandomPosition(List<String> list) {
        return ThreadLocalRandom.current().nextInt(list.size());
    }

    public String getAttribute(WebElement element, String attribute, WebDriver driver, WebDriverWait wait){
        highlight(driver, element);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute(attribute);
    }

    public String getNonVisibleAttribute(WebElement element, String attribute, WebDriver driver){
        highlight(driver, element);
        return element.getAttribute(attribute);
    }

    public void showOnScreen(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public Double getDoubleFromElement(WebElement element, WebDriver driver, WebDriverWait wait){
        try{
            this.goToElement(element,driver);
            highlight(driver,element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return Double.parseDouble(element.getText().trim().replace(",", ""));
    }

    public List<String> getTextListNotEmpty(List<WebElement> listWebElements, WebDriver driver){
        List<String> strList = new LinkedList<>();
        for (WebElement columnFields : listWebElements) {
            highlight(driver,columnFields);
            if(!columnFields.getText().isEmpty()&&!columnFields.getText().equals(" ")){
                strList.add(columnFields.getText().trim());
            }
        }
        return strList;
    }

    public void printStringList(List<String> lista){
        for (int i = 0; i <= lista.size() - 1; i++) {
            System.out.print(lista.get(i));
            System.out.print("\n");
        }
    }

    public double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    public double roundDown(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_DOWN);
        return bd.doubleValue();
    }

    public void removeReadOnlyAttribute(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", element);
    }

    public static void checkMsg(WebDriver driver, WebElement element, String msg){
        String valor = element.getAttribute("value");
        if(!valor.equals(msg)){
            Assert.fail("Mensaje incorrecto: " + valor);
        }
    }

    public static void checkEnabled(WebDriver driver, WebElement element, Boolean habilitado){
        if(!element.getAttribute("enabled").equals(habilitado.toString())){
            Assert.fail("El boton no esta habilitado");
        }
    }
}
