import { Routes } from '@angular/router';
import { LoginComponent } from './components/login.component';
import { SignupComponent } from './components/signup.component';
import { HomeComponent } from './components/home.component';
import { AdminHomeComponent } from './components/admin.home.component';
import { BuyPropertyComponent } from './components/buy.property';
import { ContactComponent } from './components/contact.component';
import { ReserveComponent } from './components/reserved.component';
import { AuthGuard } from './services/auth.guard';
import { AdminGuard } from './services/admin.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'buy', component: BuyPropertyComponent, canActivate: [AuthGuard] },
  { path: 'contact', component: ContactComponent, canActivate: [AuthGuard] },
  { path: 'reserved', component: ReserveComponent, canActivate: [AuthGuard] },
  { path: 'admin', component: AdminHomeComponent, canActivate: [AdminGuard] },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' }
];
