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
      next: (res) => {
        if (res.success) {
          this.userService.getUsers().subscribe(users => {
            const matched = users.find(u => u.email === this.email)
            if (matched) {
              this.userService.setCurrentUser(matched)
              if (matched.role === 'admin') {
                this.router.navigate(['/admin'])
              } else if (matched.role === 'customer') {
                this.router.navigate(['/home'])
              }
            }
          })
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
