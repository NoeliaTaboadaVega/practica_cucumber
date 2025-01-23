package steps;



import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.PaginaIdioma;
import pages.PaginaPrincipal;


public class MercadoLibreSteps {


    PaginaIdioma landingPageIdioma = new PaginaIdioma();
    PaginaPrincipal mainPage = new PaginaPrincipal();


    @Given ("I navigate to www.mercadolibre.com")
    public void iNavigateToFRT(){
        landingPageIdioma.navigateToMercadoLibre();
    }

    @When("I select open the dropdown of the Categoria")
    public void navigateElegirPlan(){
        landingPageIdioma.clickOnCountryArgentina(); 
        }

    @Then("I can validate the options in the dropdown")
    public void validateCheckoutPlans(){  
       
        List<String> lista = mainPage.returnValuesOfDropdown();
        List<String> listaEsperada = Arrays.asList(
            "Vehículos", 
            "Inmuebles",
            "Supermercado",
            "Tecnología",
            "Compra Internacional",
            "Hogar y Muebles", 
            "Electrodomésticos",
            "Herramientas",
            "Construcción",
            "Deportes y Fitness", 
            "Accesorios para Vehículos",
            "Mascotas",
            "Moda",
            "Juegos y Juguetes",
            "Bebés",
            "Belleza y Cuidado Personal",
            "Salud y Equipamiento Médico",
            "Industrias y Oficinas",
            "Agro",
            "Productos Sustentables",
             "Servicios", 
             "Más vendidos",
             "Tiendas oficiales",
             "Ver más categorías" );

        System.out.println("Elementos de lista: " +  lista);
        System.out.println("Elementos de lista esperada: " +  listaEsperada);
        Assert.assertEquals(lista.size(), listaEsperada.size());
        Assert.assertEquals(listaEsperada,lista);
    }
}
