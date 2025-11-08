import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { environment } from '../../environments/environment';

interface LoginResponse {
  token: string;
  tipo: string;
}

@Injectable({ providedIn: 'root' })
export class Auth {
  private readonly TOKEN_KEY = 'token';
  private readonly API_URL = `${environment.apiUrl}/auth/login`;

  constructor(private http: HttpClient, private router: Router) {}

  /** 🔑 Realiza login contra el backend */
  login(usuario: string, password: string): Observable<LoginResponse> {
    //console.log(usuario, password);
    return this.http.post<LoginResponse>(this.API_URL, { usuario, password }).pipe(
      tap(res => {
        if (res?.token) {
          //console.log(res);
          this.saveToken(res.token);
          this.router.navigate(['/dashboard']);
        }
      })
    );
  }

  
  getUserInfo(): any {
    //console.log('token:', this.getToken());
    const token = this.getToken();
    if (!token) return '';
    try {
      //console.log('info:', jwtDecode(token))
      return jwtDecode(token);
    } catch (e) {
      return 'nada';
    }
  }

  getEmpleadolId(): number {
    const info = this.getUserInfo();
    return info.id ? info.id : 0;
  }

  getSucursalId(): number {
    const info = this.getUserInfo();
    return info.idSucursal ? info.idSucursal : 0;
  }

  getNombreSucursal(): string {
    const info = this.getUserInfo();
    return info && info.nombreSucursal ? info.nombreSucursal : '';
  }


  /** 🚪 Cierra sesión */
  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  /** ✅ Verifica si hay sesión activa */
  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.TOKEN_KEY);
  }

  /** 🔄 Obtiene el token almacenado */
  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }
}
