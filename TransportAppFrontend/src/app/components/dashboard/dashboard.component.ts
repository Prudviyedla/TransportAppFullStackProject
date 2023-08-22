import { Component, OnInit } from '@angular/core';
import { FavoritesService } from 'src/app/services/favorites.service';
import { PlacesService } from 'src/app/services/places.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userId = localStorage.getItem("userId")

  userFavorites: string[] = []

  allPlaces: any

  constructor(private placesService: PlacesService, private favoritesService: FavoritesService) { }

  ngOnInit(): void {
    this.placesService.getAllPlaces().subscribe(
      places => {
        this.allPlaces = places;
      }, error => {
        console.log(error);

      }
    )
    this.refreshUserFavorites()
  }

  addToFavorites(placeName: string) {
    let favorite = {
      "userId": this.userId,
      "favPlace": placeName
    }
    this.favoritesService.addToFavorites(favorite).subscribe(
      addedFav => {      
        this.refreshUserFavorites()        
      }, error => {
        console.log(error);
        
      }
    )
  }
  refreshUserFavorites() {
    this.favoritesService.getUserFavorites(Number(this.userId)).subscribe(
      userFavs => {
        this.userFavorites = Object.values(userFavs)
      }, error => {
        console.log(error)
      }
    )
  }
  removeFromFavorites(placeName: string){
    this.favoritesService.removeFromFavorites(Number(this.userId), placeName).subscribe(
      removedFav => {
        this.refreshUserFavorites()
      }, error => {
        console.log(error);
        
      }
    )
  }
  isAFavorite(placeName: string){
    return this.userFavorites.includes(placeName)
  }
}
