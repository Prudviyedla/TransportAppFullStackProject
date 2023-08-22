import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {

  addingURL = "http://localhost:9000/api/v1/favorites"

  gettingURL = "http://localhost:9000/api/v1/favorites"

  deletingURL = "http://localhost:9000/api/v1/favorites/deleteFav"

  constructor(private httpClient: HttpClient) { }

  //add to favs
  addToFavorites(favorite: any) {
    return this.httpClient.post(`${this.addingURL}`, favorite);
  }

  //get user favs
  getUserFavorites(userId: number) {
    return this.httpClient.get(`http://localhost:9000/api/v1/favorites/${userId}`);
  }

  //remove from favs
  removeFromFavorites(userId: number, favPlace: string) {
    return this.httpClient.delete(`http://localhost:9000/api/v1/favorites/deleteFav/${userId}/${favPlace}`);
  }

}
