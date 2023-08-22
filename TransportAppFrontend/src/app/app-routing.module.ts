import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { StationStatisticsComponent } from './components/station-statistics/station-statistics.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AuthGuard } from './services/auth.guard';

const routes: Routes = [
  {
    path:"",
    component:HomeComponent,
    pathMatch:"full"
  }, {
    path:"login",
    component:LoginComponent,
    pathMatch:"full"
  }, {
    path:"register",
    component:RegisterComponent,
    pathMatch:"full"
  }, {
    path:"dashboard",
    component:DashboardComponent,
    pathMatch:"full",
    canActivate:[AuthGuard]
  }, {
    path:"user-profile",
    component:UserProfileComponent,
    pathMatch:"full",
    canActivate:[AuthGuard]
  }, {
    path:"edit-profile",
    component:EditProfileComponent,
    pathMatch:"full",
    canActivate:[AuthGuard]
  }, {
    path:"favorites",
    component:FavoritesComponent,
    pathMatch:"full",
    canActivate:[AuthGuard]
  }, {
    path:"station-statistics",
    component:StationStatisticsComponent,
    pathMatch:"full",
    canActivate:[AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
