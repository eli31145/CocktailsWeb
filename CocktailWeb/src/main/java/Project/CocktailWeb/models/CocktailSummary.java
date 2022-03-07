package Project.CocktailWeb.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class CocktailSummary {
    private String idDrink;
    private String strDrink;
    private String strDrinkThumb;

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
    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }
    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    @Override
    public String toString() {
        return "cocktailId: %s, strDrink: %s, strDrinkThumb: %s"
                .formatted(idDrink, strDrink, strDrinkThumb);
    }

    public JsonObject summaryToJson(){
        JsonObject o = Json.createObjectBuilder()
        .add("idDrink", idDrink)
        .add("strDrink", strDrink)
        .add("strDrinkThumb", strDrinkThumb) 
        .build();
        //System.out.println("this is json obj" + o.toString());
    return o;
    }

    public static CocktailSummary create(JsonObject o){
        final CocktailSummary c = new CocktailSummary();
        c.setIdDrink(o.getString("idDrink"));
        c.setStrDrink(o.getString("strDrink"));
        c.setStrDrinkThumb(o.getString("strDrinkThumb"));
        
        return c;
    }

    public static List<CocktailSummary> createListUsingIngredient(String body) throws IOException {
        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            JsonObject obj = reader.readObject();

            final JsonArray readings = obj.getJsonArray("drinks");

            List<CocktailSummary> cocktailList = new LinkedList<>();
            
            CocktailSummary c = new CocktailSummary();

            for(JsonValue v: readings){
                JsonObject jO = (JsonObject)v;

                //System.out.println(">>>>> jO = " + jO.toString());
                c = CocktailSummary.create(jO);

                cocktailList.add(c);
                //System.out.println("cocktail list: " + cocktailList);
                
                //return c;  
            } return cocktailList; 
        } 
    } 

}
