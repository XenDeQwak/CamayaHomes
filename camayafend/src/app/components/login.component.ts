import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/auth.service';
import { CustomerService, Customer } from '../services/customer.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  template: './html/login.html',
  styleUrl: '../css/login.component.css',
  imports: [CommonModule, FormsModule]
})
export class LoginComponent implements OnInit {
  customers: Customer[] = [];
  email = '';
  password = '';
  loginSuccess = false;
  loginFailed = false;

  constructor(
    private customerService: CustomerService,
    private loginService: LoginService
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
          this.loginFailed = !res.success;
        },
        error: () => {
          this.loginSuccess = false;
          this.loginFailed = true;
        }
      });
  }
}
