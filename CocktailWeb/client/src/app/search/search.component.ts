import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { Cocktail } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  user!: SocialUser
  loggedIn!: boolean

  cocktailName = '';
  searchForm!: FormGroup
  cocktails: Cocktail[] = []
  constructor(private router: Router, private fb: FormBuilder,
    private userSvc: UserService, private socialAuthService: SocialAuthService) { }

  ngOnInit(): void {
    this.socialAuthService.authState.subscribe((user)=>{
      this.user = user;
      console.log("user at search page", this.user.email)
      this.loggedIn = (user != null);
    })

    this.searchForm = this.fb.group({
      cocktailName: this.fb.control(''),
      ingredient: this.fb.control('')
    })
  }

  searchName(){
    const cocktailName: string = this.searchForm.get('cocktailName')?.value
    console.info('Searching by name...', cocktailName)
    this.router.navigate(['list', cocktailName])
  }

  searchIngredient(){
    const ingredient: string = this.searchForm.get('ingredient')?.value
    console.info('Searching by name...', ingredient)
    this.router.navigate(['list/ingredient', ingredient])
  }

  searchRandom(){
    console.info('Random Cocktail choosing...')
    this.userSvc.searchRandomCocktail()
      .then(result =>{
        this.searchForm.reset();
        console.info('>>> result: ', result.idDrink);
        this.router.navigate(['/cocktail', result.idDrink])
      })
      .catch(error=>{
        alert('An error occured with random choosing')
        console.log('>>>Error', error)
      })
  }


}
