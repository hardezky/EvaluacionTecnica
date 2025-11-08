import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudResultado } from './solicitud-resultado';

describe('SolicitudResultado', () => {
  let component: SolicitudResultado;
  let fixture: ComponentFixture<SolicitudResultado>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudResultado]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SolicitudResultado);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
