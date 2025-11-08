package com.pruebatecnica.solicitudes.service;

import com.pruebatecnica.solicitudes.dto.DecisionDTO;
import com.pruebatecnica.solicitudes.dto.SolicitudDTO;
import com.pruebatecnica.solicitudes.dto.SolicitudListDTO;
import com.pruebatecnica.solicitudes.entity.Solicitud;
import java.math.BigDecimal;
import java.util.List;

public interface ISolicitudService {
    Solicitud guardar(SolicitudDTO solicitud);
    DecisionDTO procesarSolicitud(SolicitudDTO solicitud);

    List<DecisionDTO> procesarLote(List<SolicitudDTO> solicitudes);

    List<Solicitud> listar();

    List<SolicitudListDTO> listarSolicitudes();

    String evaluarSolicitud(BigDecimal monto, Integer plazo, BigDecimal ingresos);
}
