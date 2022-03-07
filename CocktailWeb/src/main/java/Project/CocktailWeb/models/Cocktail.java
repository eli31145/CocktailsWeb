package Project.CocktailWeb.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Cocktail {
    private String idDrink;
    private String strDrink;
    private String strAlcoholic;
    private String strGlass;
    private String strInstructions;
    //now put as string because retrieve pic using url
    //consider how mod2day18 models getIcon() 
    private String strDrinkThumb;
    private List<String> strIngredients = new ArrayList<>();
    private List<String> strMeasures = new ArrayList<>();

    private List<String> ingredientAndMeasure = new ArrayList<>();
    
    public String getIdDrink() {
        return idDrink;
    }
    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }
    public String getStrDrink() {
        return strDrink;
    }
    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }
    public String getStrAlcoholic() {
        return strAlcoholic;
    }
    public void setStrAlcoholic(String strAlcoholic) {
        this.strAlcoholic = strAlcoholic;
    }
    public String getStrGlass() {
        return strGlass;
    }
    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }
    public String getStrInstructions() {
        return strInstructions;
    }
    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }
    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }
    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }
    public List<String> getStrIngredients() {
        return strIngredients;
    }
    public void setStrIngredients(List<String> ingArr) {
        this.strIngredients = ingArr;
    }
    
    public List<String> getStrMeasures() {
        return strMeasures;
    }
    public void setStrMeasures(List<String> msArr) {
        this.strMeasures = msArr;
    }
    
    public void setIngredientAndMeasure(List<String> ingredientAndMeasure) {
        this.ingredientAndMeasure = ingredientAndMeasure;
    }

    @Override
    public String toString() {
        return "cocktailId: %s, strDrink: %s, strAlcoholic: %s, strGlass: %s, strInstructions: %s, strDrinkThumb: %s, strIngredients: %s, strMeasure: %s, ingredientAndMeasure: %s"
                .formatted(idDrink, strDrink, strAlcoholic, strGlass, strInstructions, strDrinkThumb, strIngredients, strMeasures, ingredientAndMeasure);
    }

    public JsonObject toJson(){
            JsonObject o = Json.createObjectBuilder()
            .add("idDrink", idDrink)
            .add("strDrink", strDrink)
            .add("strAlcoholic", strAlcoholic)
            .add("strGlass", strGlass)
            .add("strInstructions", strInstructions)
            .add("strDrinkThumb", strDrinkThumb) 
            .add("strIngredients", Json.createArrayBuilder(strIngredients))
            .add("strMeasure", Json.createArrayBuilder(strMeasures))
    //set ingre
            .add("ingredientAndMeasure", Json.createArrayBuilder(ingredientAndMeasure))
            .build();

            //System.out.println("this is json obj" + o.toString());
        return o;
        
        /* return Json.createObjectBuilder()
            .add("idDrink", idDrink)
            .add("strDrink", strDrink)
            .add("strAlcoholic", strAlcoholic)
            .add("strGlass", strGlass)
            .add("strInstructions", strInstructions) */
            //.add("strDrinkThumb", strDrinkThumb) /*  */
            /* .add("strIngredients", Json.createArrayBuilder(strIngredients))
            .add("strMeasure", Json.createArrayBuilder(strMeasures))
            .build();
 */
    }

    public static Cocktail create(JsonObject o){
        final Cocktail c = new Cocktail();
        c.setIdDrink(o.getString("idDrink"));
        c.setStrDrink(o.getString("strDrink"));
        c.setStrAlcoholic(o.getString("strAlcoholic"));
        c.setStrGlass(o.getString("strGlass"));
        c.setStrInstructions(o.getString("strInstructions"));
        c.setStrDrinkThumb(o.getString("strDrinkThumb"));
        //Ingredient & Measure added in Service
            return c;
    }

    public static Cocktail create(String body) throws IOException {
        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            JsonObject obj = reader.readObject();

            final JsonArray readings = obj.getJsonArray("drinks");

            //List<Cocktail> cocktailList = new LinkedList<>();
            List<String> ingArr = new ArrayList<>();
            List<String> msArr = new ArrayList<>();
    //set ingre
            List<String> ingredientAndMeasure = new ArrayList<>();
            
            Cocktail c = new Cocktail();

            for(JsonValue v: readings){
                JsonObject jO = (JsonObject)v;

                //System.out.println(">>>>> jO = " + jO.toString());
                
                for (int i=1; i<16; i++){
                    String ing = "strIngredient%d".formatted(i);
                    String ms = "strMeasure%d".formatted(i);

                    if (jO.isNull(ing))
                        break;
                    
                    final String strIngredient = jO.getString(ing);

                    if (jO.isNull(ms))
                        break;
                        
                    final String strMeasure = jO.getString(ms); 

                    ingArr.add(strIngredient);
                    msArr.add(strMeasure);
    //set ingre                
                    ingredientAndMeasure.add(strIngredient + ", " + strMeasure);
                    
                }
                
                //System.out.println(ingArr);
                //System.out.println(msArr);
                //System.out.println("new jO: " + jO);
                //System.out.println("I&M: " + ingredientAndMeasure);

                c = Cocktail.create(jO);
                    c.setStrIngredients(ingArr);
                    c.setStrMeasures(msArr);
    //set ingre
                    c.setIngredientAndMeasure(ingredientAndMeasure);

                //System.out.println("cocktail obj: " + c);
                
                //return c;  
            } return c; 
        } 
    } 

    public static List<Cocktail> createList(String body) throws IOException {
        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            JsonObject obj = reader.readObject();

            final JsonArray readings = obj.getJsonArray("drinks");

            List<Cocktail> cocktailList = new LinkedList<>();
            List<String> ingArr = new ArrayList<>();
            List<String> msArr = new ArrayList<>();
    //set ingre
            List<String> ingredientAndMeasure = new ArrayList<>();
            
            Cocktail c = new Cocktail();

            for(JsonValue v: readings){
                JsonObject jO = (JsonObject)v;

                //System.out.println(">>>>> jO = " + jO.toString());
                
                for (int i=1; i<16; i++){
                    String ing = "strIngredient%d".formatted(i);
                    String ms = "strMeasure%d".formatted(i);

                    if (jO.isNull(ing))
                        break;
                    
                    final String strIngredient = jO.getString(ing);

                    if (jO.isNull(ms))
                        break;
                        
                    final String strMeasure = jO.getString(ms); 

                    ingArr.add(strIngredient);
                    msArr.add(strMeasure);
    //set ingre                
                    ingredientAndMeasure.add(strIngredient + ", " + strMeasure);
                    
                }
                
                //System.out.println(ingArr);
                //System.out.println(msArr);
                //System.out.println("new jO: " + jO);
                //System.out.println("I&M: " + ingredientAndMeasure);

                c = Cocktail.create(jO);
                    c.setStrIngredients(ingArr);
                    c.setStrMeasures(msArr);
    //set ingre
                    c.setIngredientAndMeasure(ingredientAndMeasure);

                //System.out.println("cocktail obj: " + c);
                
                cocktailList.add(c);
                //System.out.println("cocktail list: " + cocktailList);
                
                //return c;  
            } return cocktailList; 
        } 
    } 


}
