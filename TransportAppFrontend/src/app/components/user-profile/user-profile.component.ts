import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  userId = 0
  user = {
    userName: "",
    passowrd: "",
    city: "",
    email: "",
    phoneNo: ""
  }

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    
    this.userId = Number(localStorage.getItem("userId"))
    this.getUserDetails()
  }

  getUserDetails() {
    if(this.userId != null){
      this.userService.getUser(this.userId).subscribe(
        (userDetails:any) => {
          this.user = userDetails;
        }, error => {
          console.log(error);
        }
      )
    }
  }

  deleteUser() {
    if(this.userId != null){
      this.userService.deleteUser(this.userId).subscribe(
        response => {
          alert("User Deleted Successfully!")
          localStorage.removeItem("token")
          location.href=""
        }, error => {
          console.log(error)
        }
      )
    }
  }

}
