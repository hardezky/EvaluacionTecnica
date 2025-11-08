import { Component } from '@angular/core';
import { Cliente, ClienteService } from '../../../services/cliente';
import { Solicitud, SolicitudDTO } from '../../../services/solicitud';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../../../services/auth';

@Component({
  selector: 'app-solicitud-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './solicitud-form.html',
  styleUrl: './solicitud-form.css',
})
export class SolicitudForm {
clienteIdBusqueda!: number;
  cliente?: Cliente;

  monto!: number;
  plazoMeses!: number;
  ingresosMensuales!: number;

   mensaje = '';
  loading = false;

  // Puedes fijar sucursalId (ej. la del empleado logueado, si la devuelves en el token)
  sucursalId = 1;

  constructor(
    private clienteService: ClienteService,
    private solicitudService: Solicitud,
    private router: Router,
    public auth: Auth
  ) {}

  buscarCliente() {
    this.clienteService.getClienteById(this.clienteIdBusqueda).subscribe({
      next: c => this.cliente = c,
      error: () => {
        this.cliente = undefined;
        alert('Cliente no encontrado');
      }
    });
  }
  solicitud: SolicitudDTO = { clienteId: 0, monto: 0, plazoMeses: 0, ingresosMensuales: 0, sucursalId: 0, destino: '', empleadoId: 0 };
 


  crear() {
    console.log("entra a crear");
    this.loading = true;
    if(this.cliente === undefined) {
      alert('Debe buscar un cliente antes de crear la solicitud.');
      return;
    }
    const idSucursal = this.auth.getSucursalId();
    const idEmpleado = this.auth.getEmpleadolId();
    console.log("EmpleadoID: "+idEmpleado);
     this.solicitud.clienteId = this.cliente.id;
     this.solicitud.monto = this.monto;
     this.solicitud.plazoMeses = this.plazoMeses;
     this.solicitud.ingresosMensuales = this.ingresosMensuales;
     this.solicitud.sucursalId = idSucursal;
     this.solicitud.empleadoId = idEmpleado;

    this.solicitudService.crearSolicitud(this.solicitud).subscribe({
      next: res => {
            this.loading = false;
        this.mensaje = `Solicitud procesada: ${res.decision} (${res.motivo})`;
         this.router.navigate(['/solicitudes/resultado'], { state: { decision: res } });
      },
      error: err => {
            this.loading = false;
        console.error(err);
        this.mensaje = 'Error al procesar la solicitud.';
      }
    });
  }
}
