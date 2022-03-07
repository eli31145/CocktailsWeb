package Project.CocktailWeb.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import static Project.CocktailWeb.repositories.SQL.*;

import Project.CocktailWeb.models.Favourites;
import Project.CocktailWeb.models.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;

    public Optional<Integer> addUser(User user) {
        int added = template.update(SQL_ADD_USER, user.getUser(), user.getEmail());
        //System.out.println("added number: " + added);
        if (added > 0)
            return Optional.empty();

        return Optional.of(400);
    }

    public Optional<Integer> addFavourite(Favourites f) {
        int added = template.update(SQL_ADD_NEW_FAVOURITE, f.getIdDrink(), f.getEmail(), f.getStrDrink());

        if (added > 0)
            return Optional.empty();

        return Optional.of(400);

    }

    public Optional<Integer> deleteFavourite(String idDrink){
        int deleted = template.update(SQL_DELETE_FAVOURITE, idDrink);

        if (deleted >0)
            return Optional.empty();
        
        return Optional.of(400);
    }

    // make this optional, no. If it is empty, handle at front end 
    public List<Favourites> getFavourites(String email) {
        final List<Favourites> favourites = new ArrayList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_FAVOURITES, email);
        while (rs.next()) {
            final Favourites favourite = Favourites.populate(rs);
            favourites.add(favourite);
        }
        //System.out.println("return favourites list: " + favourites);
        return favourites;
    }
    // make this optional, no. If it is empty, handle at front end 
    // public List<Favourites> getFavourites() {
    //     final List<Favourites> favourites = new ArrayList<>();
    //     final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_FAVOURITES);
    //     while (rs.next()) {
    //         final Favourites favourite = Favourites.populate(rs);
    //         favourites.add(favourite);
    //     }
    //     //System.out.println("return favourites list: " + favourites);
    //     return favourites;
    // }



}
