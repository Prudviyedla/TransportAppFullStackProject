import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/router';
import { StatisticsService } from 'src/app/services/statistics.service';

@Component({
  selector: 'app-station-statistics',
  templateUrl: './station-statistics.component.html',
  styleUrls: ['./station-statistics.component.css']
})
export class StationStatisticsComponent implements OnInit {

  searched = false
  route = {
    origin: "",
    destination: ""
  }

  allDirections: any
  reqDirections: any[] = [];

  constructor(private statisticsService: StatisticsService) { }

  ngOnInit(): void {
    this.statisticsService.getAllDirections().subscribe(
      data => {
        this.allDirections=data
        // console.log(this.allDirections)
      }, error => {
        console.log(error)
      }
    )
  }

  onSubmit() {
    this.allDirections.forEach((direction: any) => {
      if(direction.origin === this.route.origin && direction.destination === this.route.destination ) {
        this.reqDirections.push(direction);
      }
    });
    this.searched = true
  }

  unSearched() {
    this.searched = false
    this.reqDirections = []
  }


  isRouteAvail() {
    if(this.reqDirections.length != 0) {
      return true
    } else {
      return false
    }
  }

}
