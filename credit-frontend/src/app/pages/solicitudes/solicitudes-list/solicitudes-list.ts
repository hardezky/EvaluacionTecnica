import { Component } from '@angular/core';
import { Solicitud, SolicitudList } from '../../../services/solicitud';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import * as XLSX from 'xlsx';
import * as FileSaver from 'file-saver';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

@Component({
  selector: 'app-solicitudes-list',
  imports: [CommonModule, FormsModule],
  templateUrl: './solicitudes-list.html',
  styleUrl: './solicitudes-list.css',
})
export class SolicitudesList {

  solicitudes: SolicitudList[] = [];

  filtro: string = '';

get solicitudesFiltradas() {
  if (!this.filtro) return this.solicitudes;
  const texto = this.filtro.toLowerCase();
  return this.solicitudes.filter(s =>
    s.clienteNombre.toLowerCase().includes(texto) ||
    s.estado.toLowerCase().includes(texto)
  );
}

  constructor(private solicitudService: Solicitud) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar() {
    this.solicitudService.obtenerSolicitudes().subscribe((data) => {
      this.solicitudes = data;
    });
  }

  exportarExcel() {
    const worksheet = XLSX.utils.json_to_sheet(this.solicitudes);
    const workbook = { Sheets: { 'Solicitudes': worksheet }, SheetNames: ['Solicitudes'] };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    const blob = new Blob([excelBuffer], { type: 'application/octet-stream' });
    FileSaver.saveAs(blob, 'solicitudes.xlsx');
  }

  exportarPDF() {
    const doc = new jsPDF();
    const columnas = ['ID', 'Fecha', 'Cliente', 'Estatus', 'Monto', 'Plazo (meses)'];
    const filas = this.solicitudes.map(s => [
      s.id,
      s.fecha,
      s.clienteNombre,
      s.estado,
      s.monto,
      s.plazoMeses
    ]);

    autoTable(doc, {
      head: [columnas],
      body: filas
    });

    doc.save('solicitudes.pdf');
  }

}
