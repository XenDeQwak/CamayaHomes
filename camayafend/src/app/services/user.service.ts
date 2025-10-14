import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  id?: number;
  name: string
  email: string
  password: string
  role: string
}


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = 'http://localhost:8080/api/users';
  private currentUser?: User
  
  constructor(private http: HttpClient) {}
    
  getUsers(): Observable<User[]> {
      return this.http.get<User[]>(this.url);
    }

    getUser(id: number): Observable<User> {
      return this.http.get<User>(`${this.url}/${id}`);
    }

    createUser(user: User): Observable<User> {
      return this.http.post<User>(this.url, user);
    }

    updateUser(id: number, user: User): Observable<User> {
      return this.http.put<User>(`${this.url}/${id}`, user);
    }

    deleteUser(id: number): Observable<void> {
      return this.http.delete<void>(`${this.url}/${id}`);
    }

    linkToAdmin(userId: number, adminId: number | undefined): Observable<any> {
      return this.http.post(`${this.url}/${userId}/assign/${adminId}`, {})
    }

    setCurrentUser(user: User) {
      this.currentUser = user
      localStorage.setItem('user', JSON.stringify(user))
    }

    getCurrentUser(): User | undefined {
      if (this.currentUser) return this.currentUser
      if (typeof window !== 'undefined' && window.localStorage) {
        const stored = localStorage.getItem('user')
        return stored ? JSON.parse(stored): undefined
      }
      return undefined
    }

    clearCurrentUser() {
      this.currentUser = undefined
      localStorage.removeItem('user')
    }
}
