import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudesLote } from './solicitudes-lote';

describe('SolicitudesLote', () => {
  let component: SolicitudesLote;
  let fixture: ComponentFixture<SolicitudesLote>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudesLote]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SolicitudesLote);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
