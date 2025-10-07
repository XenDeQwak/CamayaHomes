import { Component, OnInit } from '@angular/core';
import { CustomerService, Customer } from './services/customer.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './index.html',
  styleUrls: ['./css/app.css'],
  imports: [CommonModule]
})

export class CustomerComponent implements OnInit {
  customers: Customer[] = [];
  constructor(private customerService:CustomerService) {}

  ngOnInit() {
    this.customerService.getCustomers().subscribe(data => {
      console.log('Customers from API:', data);
      this.customers = data;
    })
  }
}
