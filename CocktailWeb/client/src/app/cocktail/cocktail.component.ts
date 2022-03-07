import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { Cocktail, CocktailSummary } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-cocktail',
  templateUrl: './cocktail.component.html',
  styleUrls: ['./cocktail.component.css']
})
export class CocktailComponent implements OnInit {
  user!: SocialUser
  loggedIn!: boolean

  idDrink!: string
  cocktail!: Cocktail

  constructor(private userSvc:UserService, private activatedRoute: ActivatedRoute,
    private socialAuthService: SocialAuthService, private router: Router) { }

  ngOnInit(): void {
    this.socialAuthService.authState.subscribe((user)=>{
      this.user = user;
      console.log("user at cocktail page", this.user.email)
      this.loggedIn = (user != null);
    })

    //implement ID display
    this.idDrink = this.activatedRoute.snapshot.params['idDrink']
    this.userSvc.getCocktail(this.idDrink)
      .then(c => this.cocktail = c as Cocktail)
      .catch(error=>{
        alert('cannot retrieve cocktail with this ID')
      })

  }

  goToLogin() {
    this.router.navigate(['/login'], { queryParams: { idDrink: this.idDrink } });
  }

  addFavourites(){
    if (this.loggedIn === false){
      alert('Please Log In to Add Favourite')
    }
    //snapshot or get idDrink, save in separate component/cocktail-list
    this.idDrink = this.activatedRoute.snapshot.params['idDrink']
    console.log("name of added drink" ,this.cocktail.strDrink)
    let favCocktail: CocktailSummary = {
      idDrink:'',
      email:'',
      strDrink:''
    }
    favCocktail.idDrink = this.idDrink
    favCocktail.email = this.user.email
    favCocktail.strDrink = this.cocktail.strDrink

    //console.log("favCocktail sent: ", favCocktail)

    this.userSvc.saveCocktail(favCocktail)
      .then(result=> {
        //console.log("idDrink Saved: ", result)
          alert('Cocktail Saved')
      })
      .catch(error => {
        alert('Please Log In to Add Favourites');
        console.error('Error', error);
      })
  }

}
