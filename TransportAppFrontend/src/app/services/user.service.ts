import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  registerURL = "http://localhost:9000/api/v1/users/register"

  usersURL = "http://localhost:9000/api/v1/users"

  updateURL = "http://localhost:9000/api/v1/users/update"

  constructor(private httpClient: HttpClient) { }

  // CRUD Operations

  //Create - Post
  createUser(user: any){
    return this.httpClient.post(`${this.registerURL}`, user)
  }

  //Read - Get
  getUserId(userName: string){
    return this.httpClient.get(`http://localhost:9000/api/v1/users/userNames/${userName}`)
  }

  getUser(userId: number){
    return this.httpClient.get(`http://localhost:9000/api/v1/users/${userId}`)
  }

  //Update - Put
  updateUser(user: any, userId: number){
    return this.httpClient.put(`http://localhost:9000/api/v1/users/update/${userId}`, user)
  }

  //Delete - Delete
  deleteUser(userId: number){
    return this.httpClient.delete(`http://localhost:9000/api/v1/users/${userId}`)
  }
}
