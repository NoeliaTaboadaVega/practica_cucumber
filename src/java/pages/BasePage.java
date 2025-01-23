package java.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
    //Donde instancio el webdriver. solo lo uso una vez
    /*
     *Declaración de una variable estatica 'driver' de tipo WebDriver
     * Esta variable va a ser compartida por todas las instancias de BasePage y sus subclases
    */
    protected static WebDriver driver;
    /*
     *Declaración de una variable de instancia 'wait' de tipo WebDriverWait.
     * Se inicializa inmediatamente con una instancia de WebDriverWait utilizando el 'driver' estático
     * WebDiverWait se usa para poner esperas explicitas en los elementos web
    */
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Aumentar de 5 a 10 segundos


    /*
     *Configura el WebDriver para Chrome usando WebDriverManager
     * WebDriverManager va a estar descargando y configurando automaticamente el driver del navegador
    */
    static {
        WebDriverManager.chromedriver().setup();
        //Inicializa la variable estatica ´driver´con una instancia de ChromeDriver
        driver = new ChromeDriver();
    }

    /*Este es el constuctor de BasePage que acepta un objeto WebDriver como argumento */
    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    /*Metodo estatico para navegar a una URL*/
    public static void navigateTo(String url){
        driver.get(url);
    }

    /*Encuentra y devuelve un WebElement en la pagina utilizando un locator XPath
     * esperando a que este presente en el DOM */
    private WebElement Find(String locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    /*clickea en el elemento luego de buscarlo*/
    public void clickElement(String locator){
        Find(locator).click();
    }
    //Cierra el navegador
    public static void closeBrowser(){
        driver.quit();
    }

    public void write(String locator, String keysToSend){
        Find(locator).clear();
        Find(locator).sendKeys(keysToSend);
    }

    //Menu desplegables seleccion por valor
    public void selectFromDropdownByValue(String locator, String value){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByValue(value);
    }

    //Menu desplegables seleccion por numero de indice
    public void selectFromDropdownByValue(String locator, Integer index){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByIndex(index);
    }

    public int selectFromDropdownByValue(String locator){
        Select dropdown = new Select(Find(locator));

        List<WebElement> dropdownOptions = dropdown.getOptions();

        return dropdownOptions.size();
    }

    public List<String> getDropDownValues(String dropdownLocator, String optionLocator) {
        // Hacer clic en el elemento que despliega las opciones
        clickElement(dropdownLocator);
    
        // Esperar a que las opciones sean visibles y capturarlas
        List<WebElement> optionsElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(optionLocator)));
        
        String[] optionsElementsSeparated = optionsElements.get(0).getText().split("\n");

        // Extraer los textos de las opciones
        List<String> values = new ArrayList<>();
        for (String option : optionsElementsSeparated) {
            values.add(option.trim());
        }
    
        // Clic para cerrar el dropdown si es necesario, puede ser el mismo elemento o uno diferente
        clickElement(dropdownLocator); // Si clic en el mismo lo cierra, si no, ajusta según sea necesario
    
        return values;
    }

    public void scrollToElement(String locator) {
        WebElement element = Find(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Asegurarse de que el elemento se carga y está presente
        if (element != null) {
            // Intentar hacer scroll hacia el elemento usando scrollIntoView
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", element);
            
            // Verificar si el elemento es visible después del scroll
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
            } catch (Exception e) {
                System.out.println("El elemento sigue sin estar visible después de hacer scroll: " + e.getMessage());
            }
        } else {
            System.out.println("El elemento no fue encontrado con el locator: " + locator);
        }
    }
    
    
    
}
