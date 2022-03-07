package Project.CocktailWeb.services;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import static Project.CocktailWeb.Constants.*;

import Project.CocktailWeb.models.Cocktail;
import Project.CocktailWeb.models.CocktailSummary;

@Service
public class CocktailService {

    public List<Cocktail> getCocktailsByName(String name) {

        final String url = UriComponentsBuilder
                .fromUriString(URL_COCKTAIL_SEARCH_BY_NAME)
                .queryParam("s", name.trim().replace(" ", "+"))
                .toUriString();

        System.out.println("url string is: " + url);

        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("Error: status code %s".formatted(resp.getStatusCode().toString()));
        }

        final String body = resp.getBody();

        // System.out.println("responsebody: " + body);

        try {
            List<Cocktail> cocktailList = Cocktail.createList(body);

            return cocktailList;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;

    }

    public List<CocktailSummary> getCocktailsByIngredient(String ingredient) {

        final String url = UriComponentsBuilder
                .fromUriString(URL_COCKTAIL_SEARCH_BY_INGREDIENT)
                .queryParam("i", ingredient.trim().replace(" ", "+"))
                .toUriString();

        System.out.println("url string is: " + url);

        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("Error: status code %s".formatted(resp.getStatusCode().toString()));
        }

        final String body = resp.getBody();

        // System.out.println("responsebody: " + body);

        try {
            List<CocktailSummary> cocktailList = CocktailSummary.createListUsingIngredient(body);

            return cocktailList;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;

    }


    public static Cocktail getCocktailById(String idDrink) {
        Cocktail c = new Cocktail();

        final String url = UriComponentsBuilder
                .fromUriString(URL_COCKTAIL_SEARCH_BY_ID)
                // check need to parse to int
                .queryParam("i", idDrink.trim())
                .toUriString();

        System.out.println("url string is: " + url);

        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("Error: status code %s".formatted(resp.getStatusCode().toString()));
        }

        final String body = resp.getBody();

        // System.out.println("responsebody: " + body);
        try {
            c = Cocktail.create(body);
            //System.out.println("cocktail obj: " + c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public static Cocktail getRandomCocktail() {
        Cocktail random = new Cocktail();

        final String url = UriComponentsBuilder
                .fromUriString(URL_COCKTAIL_RANDOM_SEARCH)
                .toUriString();

        System.out.println("url string is: " + url);

        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("Error: status code %s".formatted(resp.getStatusCode().toString()));
        }

        final String body = resp.getBody();

        // System.out.println("responsebody: " + body);
        try {
            random = Cocktail.create(body);
            //System.out.println("cocktail obj: " + random);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return random;

    }

}
