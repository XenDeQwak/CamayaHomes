import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { UserService } from './user.service';

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) {}
  canActivate(): boolean | UrlTree {
    const u = this.userService.getCurrentUser();
    return u ? true : this.router.createUrlTree(['/login']);
  }
}
