import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide = true
  credentials={
    userName:"",
    password:""
  }
  constructor(private loginService: LoginService, private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    if(this.credentials.userName != '' && this.credentials.password != '' && this.credentials.userName != null && this.credentials.password != null){
      this.loginService.genarateToken(this.credentials).subscribe(
        response => {
          console.log(response)
          this.loginService.loginUser(response, this.credentials.userName)
          this.setUserIdInLocalStorage()
          window.location.href="/dashboard"
        }, error => {
          console.log(error)
        });

    } else {
      alert("Fill all the required credentials")
    }
    
  }
  setUserIdInLocalStorage() {
    let userName = localStorage.getItem("userName")
    if(userName != null){
      this.userService.getUserId(userName).subscribe(
        userId => {
          localStorage.setItem("userId", String(userId))
        }, error => {
          console.log(error)
        }        
      )
    }
  }

}
