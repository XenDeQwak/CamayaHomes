import { Routes } from '@angular/router';
import { LoginComponent } from './components/login.component';
import { SignupComponent } from './components/signup.component';
import { HomeComponent } from './components/home.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
