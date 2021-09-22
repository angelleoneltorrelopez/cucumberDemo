package PasosDefinidos;

import Config.GetDriver;
import POMs.Form;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class FormularioDefinido {

    WebDriver driver;

    String firsName = "Angel";
    String lastName = "Torre";
    String email = "angeltorrelopez@gmail.com";
    String gender = "Male";
    String mobileNumber = "1234567890";

    GetDriver getDriver;
    Form form;

    @Given("que la web DEMOQA esta disponible")
    public void que_la_web_DEMOQA_esta_disponible(){
        getDriver = new GetDriver();
        driver = getDriver.driver;
        form = new Form(driver);
        driver.navigate().to("https://demoqa.com/automation-practice-form");
    }

    @When("registro los datos requeridos")
    public void registro_los_datos_requeridos(){
        form.sendFirsName(firsName);
        form.sendLastName(lastName);
        form.sendEmail(email);
        form.clickMaleGender();
        form.sendMobileNumber(mobileNumber);
        /*
        driver.findElement(By.id("firstName")).sendKeys(firsName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='gender-radio-1']/following-sibling::label")).click();
        driver.findElement(By.id("userNumber")).sendKeys(mobileNumber);
         */
    }

    @And("doy Click en el boton")
    public void doy_click_en_el_boton() {
        form.clickSubmitButton();
     /*   JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("submit")));
      */
    }

    @Then("se muestran mis datos en la pantalla")
    public void se_muestran_mis_datos_en_la_pantalla(){
        Assert.assertEquals(form.getTextUserNameTable(), firsName + " "+ lastName);
        Assert.assertEquals(form.getTextEmailTable(), email);
        Assert.assertEquals(form.getTextGenderTable(), gender);
        Assert.assertEquals(form.getTextMobileTable(), mobileNumber);
        /*
        driver.findElement(By.xpath("//table//td[contains(text(),'Student Name')]/following-sibling::td")).getText();
        driver.findElement(By.xpath("//table//td[contains(text(),'Student Email')]/following-sibling::td")).getText();
        driver.findElement(By.xpath("//table//td[contains(text(),'Gender')]/following-sibling::td")).getText();
        driver.findElement(By.xpath("//table//td[contains(text(),'Mobile')]/following-sibling::td")).getText();
         */
        driver.manage().deleteAllCookies();
        driver.close();
    }

    
    @When("no se ingresan datos")
    public void no_se_ingresan_datos() {

    }

    @Then("no se muestra la tabla con los datos")
    public void no_se_muestra_la_tabla_con_los_datos() {
        form.sendFirsName("");
        Assert.assertFalse(form.tableIsDisplayed());
    }

    @Then("se muestran los campos requeridos")
    public void se_muestran_los_campos_requeridos() {
        Assert.assertTrue(form.alertRequiredIsDisplayed());
        driver.manage().deleteAllCookies();
        driver.close();
    }
}