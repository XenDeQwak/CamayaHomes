import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { UserService, User } from '../services/user.service';

@Component({
    selector: 'app-signup',
    templateUrl: '../html/signup.html',
    standalone: true,
    imports: [CommonModule, FormsModule]
    
})
export class SignupComponent {
    newUser: User = {
        id: 0,
        name: '',
        email: '',
        password: '',
        role: 'customer'
    }
    success = false;
    failed = false;

    constructor(private userService: UserService) {}

    signup() {
        this.userService.createUser(this.newUser).subscribe({
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

    isFormValid(): boolean {
        return (
            this.newUser.name?.trim().length >= 2 &&
            /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.newUser.email || '') &&
            (this.newUser.password?.length || 0) >= 8
        )
    }

    isInvalidEmail(): boolean {
        if (!this.newUser.email) return false
        const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
        return !pattern.test(this.newUser.email)
    }


}