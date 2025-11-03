import { Component, OnInit } from '@angular/core'
import { UserService, User } from '../services/user.service'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'

@Component({
  selector: 'app-contacts',
  standalone: true,
  imports: [ CommonModule , FormsModule ],
  templateUrl: '../html/contact.html',
  styleUrl: '../css/contact.css'
})
export class ContactComponent implements OnInit {
  users: User[] = []
  selectedAdmin?: User

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUsers().subscribe(users => {
      this.users = users
      this.selectedAdmin = users.find(u => u.role.toLowerCase() === 'admin')
    })
  }

  linkToAdmin(userId?: number): void {
    if (!this.selectedAdmin?.id) {
      console.error('No admin found')
      return
    }

    this.userService.linkToAdmin(userId, this.selectedAdmin.id).subscribe({
      next: () => {
        alert("User Linked Successfully")
        this.userService.getUsers().subscribe(users => this.users = users)
      },
      error: err => alert("Link failed")
    })
  }
}
