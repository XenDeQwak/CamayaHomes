import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class LoginService {
  private url = 'http://localhost:8080/api/auth/login';
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<{success: boolean; customerName?: string; message?: string}> {
    return this.http.post<{success: boolean; customerName?: string; message?: string}>(
      this.url, {customerEmail: email, customerPassword: password}
    );
  }
}
