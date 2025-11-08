package com.pruebatecnica.solicitudes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudListDTO {

    private Long id;
    private String clienteNombre;
    private LocalDateTime fecha;
    private BigDecimal monto;
    private Integer plazoMeses;
    private BigDecimal ingresosMensuales;
    private String estado;
}
