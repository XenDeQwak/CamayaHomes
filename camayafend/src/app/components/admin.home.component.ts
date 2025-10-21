import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { User, UserService } from "../services/user.service";
import { Property, PropertyService } from "../services/property.service"
import { FormsModule } from "@angular/forms";

@Component({
    selector: 'app-admin-home',
    standalone: true,
    templateUrl: '../html/admin-home.html',
    imports: [CommonModule, FormsModule ]
})
export class AdminHomeComponent implements OnInit {
    customers: User[] = []
    properties: Property[] = []
    currentUser?: User
    selectedUserId?: number
    selectedPropertyId?: number

    newProperty: Property = {
        id: 0,
        name: '',
        location: '',
        description: ''
    }
    success = false;
    failed = false;

    constructor (
        private userService: UserService,
        private propertyService: PropertyService
    ) {}

    ngOnInit() {
        const currentUser = this.userService.getCurrentUser();
        if (currentUser?.role === 'admin' && currentUser.id) {
            this.userService.getLinkedCustomers(currentUser.id).subscribe(data => {
            this.customers = data;
            });
        }
        this.propertyService.getProperties().subscribe(data => this.properties = data)
    }

    addProperty() {
        this.propertyService.createProperty(this.newProperty).subscribe({
            next: () => {
                this.success = true
            },
            error: () => {
                this.failed = true
            }
        })
    }

    linkProperty() {
        if(!this.selectedUserId || !this.selectedPropertyId) return
        this.userService.linkProperty(this.selectedPropertyId, this.selectedUserId).subscribe({
            next: () => alert("User linked successfully"),
            error: err => console.error(err)
        })
    }

    
}