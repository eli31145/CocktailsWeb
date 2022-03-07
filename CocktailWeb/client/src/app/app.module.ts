import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CocktailListComponent } from './cocktail-list/cocktail-list.component';
import { CocktailComponent } from './cocktail/cocktail.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchComponent } from './search/search.component';
import { UserService } from './user.service';
import { GoogleLoginProvider, SocialAuthService, SocialAuthServiceConfig, SocialLoginModule } from 'angularx-social-login';
import { UserComponent } from './user/user.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CocktailIngredientListComponent } from './cocktail-ingredient-list/cocktail-ingredient-list.component';

const appRoutes: Routes = [
  {path:'', component: SearchComponent},
  {path:'search', component: SearchComponent},
  {path:'login', component: LoginComponent},
  {path:'user', component: UserComponent},
  {path:'user/:email', component: UserComponent},
  //{path:'list', component: CocktailListComponent},
  {path:'list/:cocktailName', component: CocktailListComponent},
  {path:'list/ingredient/:ingredient', component: CocktailIngredientListComponent},
  /* from assessment 3 */
  //{path:'cocktail', component: CocktailComponent},
  {path:'cocktail/:idDrink', component: CocktailComponent},
  {path:'**', redirectTo:'/', pathMatch:'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CocktailListComponent,
    CocktailComponent,
    SearchComponent,
    UserComponent,
    CocktailIngredientListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    FormsModule, ReactiveFormsModule,
    SocialLoginModule,
    BrowserAnimationsModule,
    MaterialModule, FlexLayoutModule
  ],
  providers: [UserService,
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: true, //switch to true to keep user signed in
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider('196337584041-tbsao6i4j79qs7d1qtakimmalra63711.apps.googleusercontent.com') // your client id
          }
        ]
      } as SocialAuthServiceConfig,
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
