import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { User, UserService } from "../services/user.service";

@Component({
    selector: 'app-admin-home',
    standalone: true,
    templateUrl: '../html/admin-home.html',
    imports: [CommonModule]
})
export class AdminHomeComponent implements OnInit {
    linkedCustomers: User[] = []
    currentUser?: User

    constructor (
        private userService : UserService
    ) {}

    ngOnInit() {
        const currentUser = this.userService.getCurrentUser();
        if (currentUser?.role === 'admin' && currentUser.id) {
            this.userService.getLinkedCustomers(currentUser.id).subscribe(data => {
            this.linkedCustomers = data;
            });
        }
    }

    
}