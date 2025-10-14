import { Component, OnInit } from '@angular/core'
import { Router } from '@angular/router'
import { LoginService } from '../services/auth.service'
import { UserService, User } from '../services/user.service'
import { FormsModule } from '@angular/forms'
import { CommonModule } from '@angular/common'

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: '../html/login.html',
  styleUrl: '../css/login.component.css',
  imports: [CommonModule, FormsModule]
})
export class LoginComponent implements OnInit {
  users: User[] = []
  email = ''
  password = ''
  loginSuccess = false
  loginAttempted = false

  constructor(
    private userService: UserService,
    private loginService: LoginService,
    private router: Router
  ) {}

  ngOnInit() {
    this.userService.getUsers().subscribe(data => this.users = data)
  }

  login() {
    this.loginAttempted = true
    this.loginService.login(this.email, this.password).subscribe({
      next: (res: any) => {
        if (res.success) {
          const user: User = {
            id: res.id,
            name: res.name,
            email: res.email,
            password: '',
            role: res.role
          }

          this.userService.setCurrentUser(user)
          this.loginSuccess = true

          if (res.role === 'admin') {
            this.router.navigate(['/admin'])
          } else if (res.role === 'customer') {
            this.router.navigate(['/home'])
          }
        } else {
          this.loginSuccess = false
        }
      },
      error: () => {
        this.loginSuccess = false
      }
    })
  }
}
