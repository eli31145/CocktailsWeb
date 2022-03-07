package Project.CocktailWeb.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Project.CocktailWeb.models.Cocktail;
import Project.CocktailWeb.models.CocktailSummary;
import Project.CocktailWeb.services.CocktailService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping(path = "/api/beverage", produces = MediaType.APPLICATION_JSON_VALUE)
public class CocktailRestController {

    @Autowired
    private CocktailService cocktailSvc;

    //@CrossOrigin
    @GetMapping(path = "/{name}")
    public ResponseEntity<String> getCocktailsByName(@PathVariable String name) {

        List<Cocktail> cocktailList = Collections.emptyList();

        try {
            cocktailList = cocktailSvc.getCocktailsByName(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // System.out.println(">>> results: " + cocktailList);

        final JsonArrayBuilder arr = Json.createArrayBuilder();
        cocktailList.stream()
                .forEach(c -> arr.add(c.toJson()));

        //System.out.println("cocktail list: " + cocktailList);

        return ResponseEntity.ok(arr.build().toString());
    }

    //get by Id
    //@CrossOrigin
    @GetMapping(path = "/id/{idDrink}")
    public ResponseEntity<String> getCocktailById(@PathVariable String idDrink){
        
        Cocktail c = new Cocktail();

        try {
            c = CocktailService.getCocktailById(idDrink);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        //System.out.println("Cocktail with id %s".formatted(idDrink));
        //System.out.println(c.toJson().toString());

        return ResponseEntity.ok(c.toJson().toString());
    }


    //@CrossOrigin
    @GetMapping(path = "ingredient/{ingredient}")
    public ResponseEntity<String> getCocktailsByIngredient(@PathVariable String ingredient) {

        List<CocktailSummary> cocktailList = Collections.emptyList();

        try {
            cocktailList = cocktailSvc.getCocktailsByIngredient(ingredient);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // System.out.println(">>> results: " + cocktailList);

        final JsonArrayBuilder arr = Json.createArrayBuilder();
        cocktailList.stream()
                .forEach(c -> arr.add(c.summaryToJson()));

        //System.out.println("cocktail list: " + cocktailList);

        return ResponseEntity.ok(arr.build().toString());
    }




    // random generator
    //@CrossOrigin
    @GetMapping(path = "/random")
    public ResponseEntity<String> getRandomCocktail() {

        Cocktail random = new Cocktail();

        try {
            random = CocktailService.getRandomCocktail();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //System.out.println("random cocktail: " + random);
        System.out.println("random to json + string: " + random.toJson().toString());

        return ResponseEntity.ok(random.toJson().toString());
    }

}
