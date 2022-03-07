package Project.CocktailWeb.models;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Favourites {
    private String idDrink;
    //private String user;
    private String email;
    private String strDrink;

    public String getIdDrink() {
        return idDrink;
    }
    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getStrDrink() {
        return strDrink;
    }
    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public static Favourites create(String payload){
        InputStream is = new ByteArrayInputStream(payload.getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject obj = reader.readObject();
        final Favourites favourite = new Favourites();

        //consider try-catch if idDrink is empty
        /* try {
            favourite.idDrink = obj.getString("idDrink").trim().
        } catch (Exception ex) {} */

        favourite.idDrink = obj.getString("idDrink");
        favourite.email = obj.getString("email");
        favourite.strDrink = obj.getString("strDrink");

        return favourite;
    }

    public static Favourites populate(SqlRowSet rs){
        final Favourites fav = new Favourites();
        fav.setIdDrink(rs.getString("idDrink"));
        fav.setEmail(rs.getString("email"));
        fav.setStrDrink(rs.getString("strDrink"));
        
        return fav;
    }

    public JsonObject toJson(){
        JsonObject o = Json.createObjectBuilder()
                .add("idDrink", idDrink)
                .add("email", email)
                .add("strDrink", strDrink)
                .build();
        return o;
    }

    @Override
    public String toString() {
        return "idDrink: %s, email: %s, strDrink: %s"
                .formatted(idDrink, email, strDrink);
    }

}
