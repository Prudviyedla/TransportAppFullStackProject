import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StatisticsService {

  searchURL = "http://localhost:9000/api/v1/directions/specifiedDirections"

  allDirections: any[] = []
  
  reqDirections: any[] = []

  constructor(private httpClient: HttpClient) { }

  getByOriginAndDestination(route: any) {
    console.log(route)
    return this.httpClient.get(`${this.searchURL}`, route);
  } 

  getAllDirections() {
    return this.httpClient.get("http://localhost:9000/api/v1/directions/allDirections")
  }

  getSpecifiedDirections(origin: string, destination: string) {
    this.allDirections.map((direction: any) => {
      if(direction.origin === origin && direction.destination === destination) {
        this.reqDirections.push(direction);
      }
    })
  }
}
