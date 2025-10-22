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
    selectedPropertyId?: number
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
    }

    link() {
        if(!this.currentUser || !this.selectedAdminId) return

        if (this.currentUser.adminId) {
            alert("Already linked to an admin")
            return
        }

        this.userService.linkToAdmin(this.currentUser?.id, this.selectedAdminId).subscribe({
            next: () => {
                this.success = true
                alert('Linked successfully')
            },
            error: err => {
                console.error(err)
            }
        })
    }

    linkProperty() {
        if(!this.currentUser?.id || !this.selectedPropertyId) return
        this.userService.linkProperty(this.selectedPropertyId, this.currentUser?.id).subscribe({
            next: () => alert("User linked successfully"),
            error: err => console.error(err)
        })
    }


}

