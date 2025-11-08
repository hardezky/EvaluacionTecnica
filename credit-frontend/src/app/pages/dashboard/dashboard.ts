import { Component } from '@angular/core';
import { Solicitud } from '../../services/solicitud';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {

  
  total = 0;
  aprobadas = 0;
  rechazadas = 0;

  constructor(private solicitudService: Solicitud) {}

  ngOnInit(): void {
    this.solicitudService.obtenerSolicitudes().subscribe(solicitudes => {
      this.total = solicitudes.length;
      this.aprobadas = solicitudes.filter(s => s.estado === 'APROBADO').length;
      this.rechazadas = solicitudes.filter(s => s.estado === 'RECHAZADO').length;
    });
  }
}
