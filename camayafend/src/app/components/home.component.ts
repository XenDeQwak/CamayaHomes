import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { User, UserService } from "../services/user.service";
import { Property, PropertyService } from "../services/property.service";

@Component({
    selector: 'app-home',
    templateUrl: '../html/home.html',
    standalone: true,
    imports: [CommonModule, FormsModule]
    
})
export class HomeComponent implements OnInit {
    users: User[] = []
    properties: Property[] = []
    currentUser?: User
    selectedAdminId?: number
    success = false
    linked = false

    constructor(
        private userService: UserService,
        private propertyService: PropertyService
    ) {}

    ngOnInit() {
        this.userService.getUsers().subscribe(data => 
            this.users = data.filter(u => u.role === "admin"))
            
        this.currentUser = this.userService.getCurrentUser()
        this.propertyService.getProperties().subscribe(data => this.properties = data)
        if (this.currentUser?.adminId) this.linked = true
    }

    link() {
        if(!this.currentUser || !this.selectedAdminId) return
        this.userService.linkToAdmin(this.currentUser?.id, this.selectedAdminId).subscribe({
            next: () => {
                this.success = true
                this.linked = true
                alert('Linked successfully')
            },
            error: err => {
                console.error(err)
            }
        })
    }


}

