package Project.CocktailWeb;

public class Constants {
    //confirm if need '//' for URL 
    public static final String URL_CONSTANT = "https://www.thecocktaildb.com/api/json/v1/1";
    
    public static final String URL_COCKTAIL_SEARCH_BY_NAME = 
        URL_CONSTANT + "/search.php";

    public static final String URL_COCKTAIL_RANDOM_SEARCH = 
        URL_CONSTANT + "/random.php";

    public static final String URL_COCKTAIL_SEARCH_BY_INGREDIENT = 
        URL_CONSTANT + "/filter.php?";

    //implement if there is time 
    public static final String URL_COCKTAIL_SEARCH_BY_ID = 
        URL_CONSTANT + "/lookup.php";
}
