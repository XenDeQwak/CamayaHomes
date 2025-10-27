import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Property {
    id?: number
    name: string
    location: string
    description: string
    price: number
    bought: boolean
    linkedUser?: number
}

@Injectable({
    providedIn: 'root'
})
export class PropertyService {
    private url = "http://localhost:8080/api/proxy/properties"

    constructor(private http: HttpClient) {}

    getProperties(): Observable<Property[]> {
          return this.http.get<Property[]>(this.url);
        }
    
    getProperty(id: number): Observable<Property> {
        return this.http.get<Property>(`${this.url}/${id}`);
    }

    createProperty(Property: Property): Observable<Property> {
        return this.http.post<Property>(this.url, Property);
    }

    updateProperty(id: number, Property: Property): Observable<Property> {
        return this.http.put<Property>(`${this.url}/${id}`, Property);
    }

    deleteProperty(id: number): Observable<void> {
        return this.http.delete<void>(`${this.url}/${id}`);
    }
}