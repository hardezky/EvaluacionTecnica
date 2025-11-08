import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

export interface Cliente {
  id: number;
  nombre: string;
  apellidos?: string;
  // otros campos...
}

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  
  constructor(private http: HttpClient) { }

  getClienteById(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${environment.apiUrl}/clientes/${id}`);
  }
}
