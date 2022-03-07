import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { Cocktail, User } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-cocktail-list',
  templateUrl: './cocktail-list.component.html',
  styleUrls: ['./cocktail-list.component.css']
})
export class CocktailListComponent implements OnInit {
  user!: SocialUser
  loggedIn!: boolean

  ingredient!:string
  cocktailName!: string
  cocktails: Cocktail[] = []
  constructor(private userSvc:UserService, private activatedRoute: ActivatedRoute, private router: Router, private socialAuthService: SocialAuthService) { }

  ngOnInit(): void {
    this.socialAuthService.authState.subscribe((user)=>{
      this.user = user;
      console.log("user at cocktail list page", this.user.email)
      this.loggedIn = (user != null);
    })

    this.cocktailName = this.activatedRoute.snapshot.params['cocktailName']
    console.log('queryParam: ', this.cocktailName)
      this.userSvc.getCocktails(this.cocktailName)
        .then(result =>
          this.cocktails = result)
        .catch(error=>{
          alert('cannot retrieve cocktail list')
          this.router.navigate(['/search'])
        })

  }

}
