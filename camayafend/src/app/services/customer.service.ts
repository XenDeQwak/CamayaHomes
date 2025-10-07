import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Customer {
  id?: number;
  customerName: string;
  customerEmail: string;
  customerPassword: string;
  customerContactNo: string;
}


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private url = 'http://localhost:8080/api/customer';

  constructor(private http: HttpClient) {}
    
  getCustomers(): Observable<Customer[]> {
      return this.http.get<Customer[]>(this.url);
    }

    getCustomer(id: number): Observable<Customer> {
      return this.http.get<Customer>(`${this.url}/${id}`);
    }

    createCustomer(customer: Customer): Observable<Customer> {
      return this.http.post<Customer>(this.url, customer);
    }

    updateCustomer(id: number, customer: Customer): Observable<Customer> {
      return this.http.put<Customer>(`${this.url}/${id}`, customer);
    }

    deleteCustomer(id: number): Observable<void> {
      return this.http.delete<void>(`${this.url}/${id}`);
    }
}
