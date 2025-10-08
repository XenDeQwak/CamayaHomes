import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/auth.service';
import { CustomerService, Customer } from '../services/customer.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: '../html/login.html',
  styleUrl: '../css/login.component.css',
  imports: [CommonModule, FormsModule]
})
export class LoginComponent implements OnInit {
  customers: Customer[] = [];
  email = '';
  password = '';
  loginSuccess = false;

  constructor(
    private customerService: CustomerService,
    private loginService: LoginService,
    private router: Router
  ) {}

  ngOnInit() {
    this.customerService.getCustomers()
      .subscribe(data => this.customers = data);
  }

  login() {
    this.loginService.login(this.email, this.password)
      .subscribe({
        next: (res) => {
          this.loginSuccess = res.success;

          if (this.loginSuccess) {
            this.router.navigate(['/home']);
          }
        },
        error: () => {
          this.loginSuccess = false;
        }
      });
  }
}
