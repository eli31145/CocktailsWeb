package Project.CocktailWeb.repositories;

public class SQL {
    //id not added here
    public static final String SQL_ADD_USER = "insert into members(user, email) values(?, ?)";
    
    //public static final String SQL_GET_ALL_FAVOURITES = "select * from favourites";
    public static final String SQL_GET_ALL_FAVOURITES = "select * from favourites where email like ?";

    public static final String SQL_DELETE_FAVOURITE = "delete from favourites where idDrink like ?";

    public static final String SQL_ADD_NEW_FAVOURITE = "insert into favourites(idDrink, email, strDrink) values(?, ?, ?)";
}
