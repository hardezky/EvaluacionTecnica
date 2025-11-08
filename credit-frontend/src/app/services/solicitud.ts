import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface SolicitudDTO {
  clienteId: number;
  monto: number;
  plazoMeses: number;
  ingresosMensuales: number;
  sucursalId: number;
  destino: string;
  empleadoId: number;
}

export interface DecisionDTO {
  solicitudId: number;
  score: number;
  decision: string;
  motivo: string;
}

export interface SolicitudList {
  id: number;
  fecha: string;          // o Date si prefieres
  clienteNombre: string;
  monto: number;
  plazoMeses: number;
  ingresosMensuales: number;
  estado: string;         // "APROBADO" | "RECHAZADO"
}


@Injectable({ providedIn: 'root' })
export class Solicitud {
  private readonly API_URL = `${environment.apiUrl}/solicitudes`;

  constructor(private http: HttpClient) {}

  /** 📤 Crear nueva solicitud */
  crearSolicitud(dto: SolicitudDTO): Observable<DecisionDTO> {
    return this.http.post<DecisionDTO>(`${this.API_URL}/crear`, dto);
  }

  /** 📋 Obtener todas las solicitudes */
 obtenerSolicitudes(): Observable<SolicitudList[]> {
  return this.http.get<SolicitudList[]>(`${this.API_URL}/todas`);
}

  /** 📊 Estadísticas (total aprobadas / rechazadas) */
  obtenerEstadisticas(): Observable<{ aprobadas: number; rechazadas: number }> {
    return this.http.get<{ aprobadas: number; rechazadas: number }>(`${this.API_URL}/estadisticas`);
  }

  procesarLote(solicitudes: any[]): Observable<any> {
    return this.http.post(`${this.API_URL}/procesar-lote`, solicitudes);
  }

  cargarMock(): Observable<any[]> {
    return this.http.get<any[]>('assets/mock-solicitudes.json');
  }

}
