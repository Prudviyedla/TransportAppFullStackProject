import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  baseUrl = "http://localhost:9000/api/v1/users/login"

  constructor(private httpClient: HttpClient) { }

  // Calling the server to generate token

  genarateToken(credentials: any){
    // generateToken
    return this.httpClient.post(`${this.baseUrl}`, credentials, {responseType: 'text'})
  }

  loginUser(token: string, userName: string) {
    localStorage.setItem("token", token);
    localStorage.setItem("userName", userName);
    return true
  }

  isLoggedIn() {
    let token = localStorage.getItem("token");
    if(token==undefined || token === '' || token === null){
      return false;
    } else {
      return true;
    }
  }

  logoutUser() {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("userName");
    return true;
  }

  getToken() {
    return localStorage.getItem("token");
  }

}
