package com.pruebatecnica.solicitudes.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class SolicitudDTO {
    private Long clienteId;
    private Long sucursalId;
    private BigDecimal monto;
    private Integer plazoMeses;
    private BigDecimal ingresosMensuales;
    private String destino;
    private Long empleadoId;
}