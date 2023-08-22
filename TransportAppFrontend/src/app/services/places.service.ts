import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PlacesService {

  constructor(private httpClient: HttpClient) { }

  getAllPlaces() {
    return this.httpClient.get("http://localhost:9000/api/v1/places/");
  }
}
