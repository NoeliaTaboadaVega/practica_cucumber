package pages;

import java.util.List;

public class PaginaPrincipal extends BasePage{

    private String categoriasLink = "//a[@class='nav-menu-categories-link']";
    private String categoriasDropdown = "//ul[@class='nav-categs-departments']";



    public PaginaPrincipal(){
        super(driver);
    }
    
    public List<String> returnValuesOfDropdown() {
        return getDropDownValues(categoriasLink, categoriasDropdown);
    }
    
}