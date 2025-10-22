import { Routes } from '@angular/router';
import { LoginComponent } from './components/login.component';
import { SignupComponent } from './components/signup.component';
import { HomeComponent } from './components/home.component';
import { AdminHomeComponent } from './components/admin.home.component';
import { BuyPropertyComponent } from './components/buy.property';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'home', component: HomeComponent },
  { path: 'admin', component: AdminHomeComponent },
  { path: 'buy', component: BuyPropertyComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
