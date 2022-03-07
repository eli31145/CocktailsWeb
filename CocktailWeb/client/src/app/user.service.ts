import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { Cocktail, CocktailSummary, User } from "./models";


@Injectable()
export class UserService {

  constructor(private http: HttpClient) {}

  postUser(u: User): Promise<any>{
    //no header due to angular default
/*     const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json') */

    return lastValueFrom(
        this.http.post('/api/user', u)
    )
  }

  searchRandomCocktail(): Promise<Cocktail>{
    return lastValueFrom(
        this.http.get<Cocktail>('/api/beverage/random')
    )
  }

  getCocktail(idDrink: string): Promise<Cocktail>{
    return lastValueFrom(
      this.http.get<Cocktail>(`/api/beverage/id/${idDrink}`)
    )
  }

  getCocktails(cocktailName: string): Promise<Cocktail[]>{
    return lastValueFrom(
      this.http.get<Cocktail[]>(`/api/beverage/${cocktailName}`)
    )
  }

  getCocktailsByIngredient(ingredient: string): Promise<Cocktail[]>{
    return lastValueFrom(
      this.http.get<Cocktail[]>(`/api/beverage/ingredient/${ingredient}`)
    )
  }


  //get back userId & drinkId
  /* getUserInfo(): Promise<User>{
    return lastValueFrom(
      this.http.get<User>('/api/user')
    )
  } */

  // getUserFavourites(): Promise<CocktailSummary[]>{
  //   return lastValueFrom(
  //     this.http.get<CocktailSummary[]>(`/api/user/`)
  //   )
  // }

  getUserFavourites(email: string): Promise<CocktailSummary[]>{
    return lastValueFrom(
      this.http.get<CocktailSummary[]>(`/api/user/${email}`)
    )
  }

  saveCocktail(favCocktail: CocktailSummary):Promise<any>{
    return lastValueFrom(
      this.http.post('/api/user/addFavourite', favCocktail)
    )
  }

  deleteUserFavourite(idDrink: string): Promise<any>{
    return lastValueFrom(
      this.http.delete(`api/user/delete/${idDrink}`)
    )
  }

}
