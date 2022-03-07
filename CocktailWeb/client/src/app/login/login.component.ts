import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GoogleLoginProvider, SocialAuthService } from 'angularx-social-login';
import { User } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  idDrink!: string

  //userForm!: FormGroup
  constructor(private router: Router, private fb: FormBuilder,
              private userSvc: UserService, private socialAuthService:SocialAuthService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams
      .subscribe(params=> {
        console.log(params);
        this.idDrink = params['idDrink']
        console.log("gotten this",this.idDrink)
      })

    // this.userForm = this.fb.group({
    //   user: this.fb.control('', [Validators.required, Validators.minLength(3)]),
    //   email: this.fb.control('')
    //   /* check if email has auto-validators. Yes, but only to check for @ */
    // })
  }

  //if using traditional log in, may not show log in at every page unlike SocialAuthLogin
  // onLogin(){
  //   const u: User = this.userForm.value as User
  //   console.log(">>>user: ", u);
  //   this.userSvc.postUser(u)
  //     .then(result => {
  //       console.log("postuser response: ", result)
  //         this.userForm.reset();
  //         alert('Details Verified')
  //         this.router.navigate(['/search'])
  //     })
  //     .catch(error => {
  //       alert('user not added');
  //       console.error('Error', error);
  //     })

  //   }

    loginWithGoogle(): void {
      this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID)
        .then(result => {
          console.log("google response: ", result)
          let u: User = {
            user: '',
            email: ''
          }
          u.user = result.name
          u.email = result.email
          console.log("User object: ", u)
           this.userSvc.postUser(u)
           .then(result => {
             console.log("postuser response: ", result)
               alert('Details Verified')
               /* this.router.navigate(['/user', u.email]) */
               this.router.navigate(['/cocktail', this.idDrink])
          })
          .catch(error => {
            alert('user not added');
            console.error('Error', error);
          })

        })
        .catch(error => {
          alert('Google account not signed in');
          console.error('Error', error);
          this.router.navigate(['/cocktail', this.idDrink])
        })

    }
}
