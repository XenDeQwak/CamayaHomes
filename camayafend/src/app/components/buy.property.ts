import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Property, PropertyService } from '../services/property.service';
import { FormsModule } from '@angular/forms';
import { User, UserService } from '../services/user.service';

@Component({
    selector: 'app-buyproperty',
    templateUrl: '../html/buy-property.html',
    imports: [CommonModule, FormsModule ],
    standalone: true,
    styleUrl: '../css/buy-property.css'
})
export class BuyPropertyComponent implements OnInit {

    properties: Property[] = []
    selectedPropertyId?: number
    currentUser?: User
    users: User[] = []

    constructor(
        private propertyService: PropertyService,
        private userService: UserService
    ) {}

    ngOnInit() {
        this.loadProperties()
        this.currentUser = this.userService.getCurrentUser()
        this.userService.getUsers().subscribe(users => this.users = users)
    }

    loadProperties() {
        this.propertyService.getProperties().subscribe(data => {
            this.properties = data
        })
    }

    buyProperty(propertyId?: number) {
        if(!this.currentUser?.id) {
            alert("Please log in to buy a property")
            return
        }

        const property = this.properties.find(p => p.id === propertyId)
        if(property?.bought) {
            alert("This property is already sold")
            return
        }

        this.userService.linkProperty(propertyId, this.currentUser?.id).subscribe({
            next: () => {
                alert("Property purchased successfully!")
                this.loadProperties()
            },
            error: err => {
                console.error(err)
                alert("Failed to purchase property")
            }
        })
    }

}