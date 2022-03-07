drop database if exists cocktailweb;

create database cocktailweb;

use cocktailweb;

create table cocktail(
    idDrink int not null,
    strDrink varchar(64) not null,
    strAlcoholic char(24),
    strGlass char(24),
    /* consider Instructions as mediumtext */
    strInstructions varchar(256),
    /* thumbnail pic is retrieved as URL */
    strDrinkThumb varchar(256),
    /* ingredient and measure not included here */

    primary key(idDrink)
);

create table members(
    userId int auto_increment not null,
    user varchar(64) not null,
    email varchar(64) not null,

    primary key(userId)
);

create table favourites(
    fid int auto_increment not null,
    idDrink int not null,
    email varchar(64) not null,
    strDrink varchar(64) not null,
    
    primary key(fid)
);

/* create table CocktailSummary(
    idDrink int not null,
    userId int,

    primary key(idDrink)
); */
