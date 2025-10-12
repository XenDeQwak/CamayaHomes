import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private url = 'http://localhost:8080/api/auth/login';

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<{
    role: string;
    success: boolean;
    userName?: string;
    message?: string;
  }> {
    return this.http.post<{
      role: string;
      success: boolean;
      userName?: string;
      message?: string;
    }>(
      this.url,
      { email, password }
    );
  }
}
