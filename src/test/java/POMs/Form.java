package POMs;

import Config.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * @Autor Angel Torre
 * @Created: 04/09/2021
 */

public class Form extends Actions {
    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(xpath = "//input[@id='gender-radio-1']/following-sibling::label")
    private WebElement maleGender;

    @FindBy(id = "userNumber")
    private WebElement mobileNumber;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//table//td[contains(text(),'Student Name')]/following-sibling::td")
    private WebElement userNameTable;

    @FindBy(xpath = "//table//td[contains(text(),'Student Email')]/following-sibling::td")
    private WebElement emailTable;

    @FindBy(xpath = "//table//td[contains(text(),'Gender')]/following-sibling::td")
    private WebElement genderTable;

    @FindBy(xpath = "//table//td[contains(text(),'Mobile')]/following-sibling::td")
    private WebElement mobileTable;

    @FindBy(xpath = "//table")
    private WebElement table;

    @FindBy(className = "was-validated")
    private WebElement alertRequired;

    private WebDriver driver;
    private AjaxElementLocatorFactory factory;

    public Form(WebDriver driver){
        this.driver = driver;
        factory = new AjaxElementLocatorFactory(driver, 5);
        PageFactory.initElements(factory, this);
    }

    public boolean sendFirsName(String textFirstName){
        return sendKeys(driver, firstName, 10, textFirstName);
    }

    public boolean sendLastName(String textLastName){
        return sendKeys(driver, lastName, 10, textLastName);
    }

    public boolean sendEmail(String textEmail){
        return sendKeys(driver, userEmail, 10, textEmail);
    }

    public boolean clickMaleGender(){
        return click(driver, maleGender, 10);
    }

    public boolean sendMobileNumber(String textMobileNumber){
        return sendKeys(driver, mobileNumber, 10, textMobileNumber);
    }

    public boolean clickSubmitButton(){
        return click(driver, submitButton, 10);
    }

    public String getTextUserNameTable(){
        return getText(driver, userNameTable, 10).trim();
    }

    public String getTextEmailTable(){
        return getText(driver, emailTable, 10).trim();
    }

    public String getTextGenderTable(){
        return getText(driver, genderTable, 10).trim();
    }

    public String getTextMobileTable(){
        return getText(driver, mobileTable, 10).trim();
    }

    public boolean tableIsDisplayed(){
        return waitElementVisibility(driver, table, 5);
    }

    public boolean alertRequiredIsDisplayed(){
        return waitElementVisibility(driver, alertRequired, 5);
    }

    public void waitSecond(int second){
        waitSeconds(second);
    }
}