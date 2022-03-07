package Project.CocktailWeb.models;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class User {
    private int userId;
    private String user;
    private String email; 

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public static User createUser(String payload){

        InputStream is = new ByteArrayInputStream(payload.getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject obj = reader.readObject();

        final User user = new User();

        try {
            user.userId = obj.getInt("userId", -1);
        } catch (Exception e) {}
        
        user.user = obj.getString("user");
        user.email = obj.getString("email");

        return user;
    }


}
