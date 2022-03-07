import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { Cocktail, CocktailSummary, User } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user!: SocialUser
  loggedIn!: boolean
  idDrink!: string

  //cocktails: Cocktail[] = []
  favourites: CocktailSummary[] = [];
  //socialAuthServ is public here so login page can access
  constructor(private router: Router, public socialAuthService: SocialAuthService,
    private userSvc: UserService, private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {

    this.socialAuthService.authState.subscribe((user)=>{
      this.user = user;
      console.log(this.user.email)
      this.loggedIn = (user != null);
    })

    //change getUserFavourites to call specific user email
    this.userSvc.getUserFavourites(this.user.email)
    //this.userSvc.getUserFavourites()
      .then(result =>
        this.favourites = result)
      .catch(error=>{
        alert('cannot retrieve Favourites')
      })
  }

  deleteFavourite(idDrink: string) {
    console.log("deleted drink id: ", idDrink)
    this.userSvc.deleteUserFavourite(idDrink)
    .then( () =>
      console.log("deleted drink id: ", idDrink)
      )
    .catch(error=>{
        alert('cannot delete Favourite')
      })

    this.router.navigate(['/user', this.user.email])
	}

  logout(): void {
    this.socialAuthService.signOut().then(() => this.router.navigate(['']));
  }
}
