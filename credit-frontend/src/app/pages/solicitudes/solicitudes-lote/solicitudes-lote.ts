import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Solicitud } from '../../../services/solicitud';
import mockSolicitudes from '../../../../assets/mock-solicitudes.json';

@Component({
  selector: 'app-solicitudes-lote',
  imports: [CommonModule],
  templateUrl: './solicitudes-lote.html',
  styleUrl: './solicitudes-lote.css',
})
export class SolicitudesLote {
resultados: any[] = [];
  cargando = false;

  constructor(private solicitudService: Solicitud) {}

  procesarLote() {
    this.cargando = true;
    //this.solicitudService.cargarMock().subscribe((solicitudes) => {
      this.solicitudService.procesarLote(mockSolicitudes).subscribe({
        next: (res) => {
          this.resultados = res;
          this.cargando = false;
        },
        error: (err) => {
          console.error(err);
          this.cargando = false;
        },
      });
    //});
  }
}
