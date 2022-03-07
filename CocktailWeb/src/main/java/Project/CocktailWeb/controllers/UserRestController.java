package Project.CocktailWeb.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Project.CocktailWeb.models.User;
import Project.CocktailWeb.models.Favourites;
import Project.CocktailWeb.repositories.UserRepository;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "api/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepo;

    //@CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody String payload) {
        System.out.println(">>> payload: " + payload);

        User user = null;
        JsonObject err;

        try {
            user = User.createUser(payload);
            System.out.println("user created from springboot is: " + user);
        } catch (Exception e) {
            err = Json.createObjectBuilder()
                    .add("error", e.getMessage()).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.toString());
            // what happens when you do not override toString method, see above
        }

        Optional<Integer> opt = userRepo.addUser(user);
            if (opt.isEmpty()) {
                System.out.println("opt is empty");
                return ResponseEntity.ok("{}");
            }

            err = Json.createObjectBuilder()
                .add("error", "Failed to create new user")
                .build();
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.toString());
    }

    //@CrossOrigin
    @PostMapping(path="/addFavourite", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addFavourite(@RequestBody String payload){
        //System.out.println("favourite payload: " + payload);

        Favourites favourite = null;
        JsonObject err;

        try {
            favourite = Favourites.create(payload);
        } catch (Exception ex) {
            
            err = Json.createObjectBuilder()
                .add("error", ex.getMessage())
                .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.toString());
        }

        Optional<Integer> opt = userRepo.addFavourite(favourite);
            if (opt.isEmpty())
                return ResponseEntity.ok("{}");
            
            err = Json.createObjectBuilder()
                .add("error", "cannot add to favourites repo")
                .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.toString());

    }
    
    // @CrossOrigin
    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> getUserFavourites(){
    //     List<Favourites> result = userRepo.getFavourites();
    //         JsonArrayBuilder arr = Json.createArrayBuilder();
    //         result.stream()
    //             .forEach(f -> arr.add(f.toJson()));
        
    //     return ResponseEntity.ok(arr.build().toString());
    // }

    //@CrossOrigin
    @GetMapping(path = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    //confirm this payload necessary
    public ResponseEntity<String> getUserFavourites(@PathVariable String email){
        List<Favourites> result = userRepo.getFavourites(email);
            JsonArrayBuilder arr = Json.createArrayBuilder();
            result.stream()
                .forEach(f -> arr.add(f.toJson()));
        
        //haven't override toString
        return ResponseEntity.ok(arr.build().toString());
    }

    //@CrossOrigin
    @DeleteMapping(path="delete/{idDrink}")
    public ResponseEntity<String> deleteUserFavourite(@PathVariable String idDrink){

        //no need to create anything
        
        Optional<Integer> opt = userRepo.deleteFavourite(idDrink);
            if (opt.isEmpty())
                return ResponseEntity.ok("{}");
            
        JsonObject err = Json.createObjectBuilder()
                .add("error", "cannot delete from favourites")
                .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.toString());
    }

}
