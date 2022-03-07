import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { Cocktail } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-cocktail-ingredient-list',
  templateUrl: './cocktail-ingredient-list.component.html',
  styleUrls: ['./cocktail-ingredient-list.component.css']
})
export class CocktailIngredientListComponent implements OnInit {

  user!: SocialUser
  loggedIn!: boolean

  ingredient!:string
  cocktails: Cocktail[] = []
  constructor(private userSvc:UserService, private activatedRoute: ActivatedRoute, private router: Router, private socialAuthService: SocialAuthService) { }

  ngOnInit(): void {
    this.socialAuthService.authState.subscribe((user)=>{
      this.user = user;
      console.log("user at cocktail list page", this.user.email)
      this.loggedIn = (user != null);
    })


    this.ingredient = this.activatedRoute.snapshot.params['ingredient']
      this.userSvc.getCocktailsByIngredient(this.ingredient)
      .then(result =>
        this.cocktails = result)
      .catch(error=>{
        alert('cannot find drink containing this ingredient')
        this.router.navigate(['/search'])
      })

  }

}
