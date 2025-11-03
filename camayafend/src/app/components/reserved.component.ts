import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { UserService, User } from "../services/user.service";
import { PropertyService, Property } from "../services/property.service";

@Component({
  selector: 'app-reserved',
  standalone: true,
  templateUrl: '../html/reserved.html',
  styleUrls: ['../css/reserved.css'],
  imports: [CommonModule, FormsModule]
})
export class ReserveComponent implements OnInit {
  reservedProperties: Property[] = [];
  currentUser?: User;
  loading = true;

  constructor(private userService: UserService, private propertyService: PropertyService) {}

  ngOnInit() {
    this.currentUser = this.userService.getCurrentUser();
    if (this.currentUser?.id) {
      this.propertyService.getReservedByUser(this.currentUser.id).subscribe({
        next: (data) => {
          this.reservedProperties = data;
          this.loading = false;
        },
        error: (err) => {
          console.error("Failed to load reserved properties", err);
          this.loading = false;
        }
      });
    } else {
      this.loading = false;
    }
  }

  getImageUrl(url: string) {
    return `http://localhost:8080/api/proxy/uploads/${url}`;
  }
}
