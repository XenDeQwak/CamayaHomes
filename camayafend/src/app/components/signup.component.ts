import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { CustomerService, Customer } from '../services/customer.service';

@Component({
    selector: 'app-signup',
    templateUrl: '../html/signup.html',
    standalone: true,
    imports: [CommonModule, FormsModule]
    
})
export class SignupComponent {
    newCustomer: Customer = {
        id: 0,
        customerName: '',
        customerEmail: '',
        customerPassword: '',
        customerContactNo: ''
    }
    success = false;
    failed = false;

    constructor(private customerService: CustomerService) {}

    signup() {
        this.customerService.createCustomer(this.newCustomer).subscribe({
            next: res => {
                this.success = true;
                this.failed = false;
            },
            error: err => {
                this.success = false;
                this.failed = true;
            }
        });
    }
}