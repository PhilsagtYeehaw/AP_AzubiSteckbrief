import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AzubiService {
  private baseUrl = 'http://localhost:8080/api/auszubildende';

  constructor(private http: HttpClient) {}

  getAzubis() {
    return this.http.get<any[]>(this.baseUrl);
  }

  getAzubiById(id: number) {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }
}
