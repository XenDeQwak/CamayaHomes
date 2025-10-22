import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Property, PropertyService } from '../services/property.service';
import { FormsModule } from '@angular/forms';
import { User, UserService } from '../services/user.service';

@Component({
    selector: 'app-buyproperty',
    templateUrl: '../html/buy-property.html',
    imports: [CommonModule, FormsModule ],
    standalone: true
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
        this.propertyService.getProperties().subscribe(data => this.properties = data)
        this.currentUser = this.userService.getCurrentUser()
    }

    buyProperty() {
        if(!this.currentUser?.id || !this.selectedPropertyId) return
        this.userService.linkProperty(this.selectedPropertyId, this.currentUser?.id).subscribe({
            next: () => alert("User linked successfully"),
            error: err => console.error(err)
        })
    }

}