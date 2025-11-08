import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-solicitud-resultado',
  imports: [CommonModule],
  templateUrl: './solicitud-resultado.html',
  styleUrl: './solicitud-resultado.css',
})
export class SolicitudResultado {
    decision: any;

  constructor(private router: Router) {
    const nav = this.router.getCurrentNavigation();
    this.decision = nav?.extras?.state?.['decision'];
  }
}
