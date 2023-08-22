import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  hide = true;
  constructor(private userService: UserService) { }

  user = {
    userName: "",
    password: "",
    city: "",
    email: "",
    phoneNo: ""
  }

  ngOnInit(): void {
    this.getUserDetails()
  }

  getUserDetails() {
    let userId = localStorage.getItem("userId")
    if (userId != null) {
      this.userService.getUser(Number(userId)).subscribe(
        (userDetails: any) => {
          this.user = userDetails;
        }, error => {
          console.log(error);
        }
      )
    }
  }

  onSubmit() {
    if (this.user.userName === '' || this.user.userName === null
      || this.user.password === '' || this.user.password === null
      || this.user.city === '' || this.user.city === null
      || this.user.email === '' || this.user.email === null
      || this.user.phoneNo === '' || this.user.phoneNo === null) {
      alert("Please enter all the details")
    } else {
      this.userService.updateUser(this.user, Number(localStorage.getItem("userId"))).subscribe(
        data => {
          alert("User Profile Updated Successfully :)")
          location.href = '/dashboard'
        }, error => {
          alert("Something went wrong !! Unable to create new user")
          console.log(error)
        }
      )
    }
  }
}
