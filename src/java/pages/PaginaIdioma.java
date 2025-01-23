package pages;

public class PaginaIdioma extends BasePage {

    private String LinkArgentina = "//a[@id='AR']";


    public PaginaIdioma(){
        super(driver);
    }

    //Metodo para navegar a wwww.freerangetesters.com
    public void navigateToMercadoLibre(){
        navigateTo("https://www.mercadolibre.com");
    }

    public void clickOnCountryArgentina(){
        clickElement(LinkArgentina);
    }
    
}