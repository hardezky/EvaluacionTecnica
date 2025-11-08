import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'filtroSolicitudes', standalone: true })
export class FiltroSolicitudesPipe implements PipeTransform {

 transform(solicitudes: any[], texto: string): any[] {
    if (!texto) return solicitudes;
    texto = texto.toLowerCase();
    return solicitudes.filter(s =>
      s.cliente?.nombre?.toLowerCase().includes(texto) ||
      s.decision?.toLowerCase().includes(texto)
    );
  }

}
