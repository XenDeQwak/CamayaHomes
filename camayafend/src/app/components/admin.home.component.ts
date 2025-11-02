import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { User, UserService } from "../services/user.service";
import { Property, PropertyService } from "../services/property.service";
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-admin-home',
  standalone: true,
  templateUrl: '../html/admin-home.html',
  imports: [CommonModule, FormsModule]
})
export class AdminHomeComponent implements OnInit {
  customers: User[] = [];
  properties: Property[] = [];
  currentUser?: User;
  selectedUserId?: number;
  selectedPropertyId?: number;
  files: FileList | null = null;
  selectedProperty: any = null;
  selectedFiles: File[] = [];

  newProperty: Property = {
    id: 0,
    name: '',
    location: '',
    description: '',
    price: 0,
    bought: false
  };

  success = false;
  failed = false;

  constructor(
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

    this.propertyService.getProperties().subscribe({
      next: (props: Property[]) => this.properties = props,
      error: () => alert('Failed to load properties')
    });
  }

  addProperty() {
    this.propertyService.createProperty(this.newProperty).subscribe({
      next: (property: any) => {
        alert('Property added successfully!');
        this.properties.push(property);
        this.selectedProperty = property;
      },
      error: () => alert('Failed to add property')
    });
  }

  onFileChange(event: any) {
    this.files = event.target.files;
  }

  uploadImages() {
    if (!this.files || !this.selectedPropertyId) return;
    this.propertyService.uploadPropertyImages(this.selectedPropertyId, this.files).subscribe({
      next: (res) => alert('Images uploaded successfully!'),
      error: () => alert('Upload failed')
    });
  }
}
