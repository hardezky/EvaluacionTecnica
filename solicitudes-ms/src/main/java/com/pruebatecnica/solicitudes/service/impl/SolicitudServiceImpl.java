package com.pruebatecnica.solicitudes.service.impl;

import com.pruebatecnica.solicitudes.dto.DecisionDTO;
import com.pruebatecnica.solicitudes.dto.SolicitudDTO;
import com.pruebatecnica.solicitudes.dto.SolicitudListDTO;
import com.pruebatecnica.solicitudes.entity.Cliente;
import com.pruebatecnica.solicitudes.entity.Empleado;
import com.pruebatecnica.solicitudes.entity.Solicitud;
import com.pruebatecnica.solicitudes.entity.Sucursal;
import com.pruebatecnica.solicitudes.repository.ClienteRepository;
import com.pruebatecnica.solicitudes.repository.EmpleadoRepository;
import com.pruebatecnica.solicitudes.repository.SolicitudRepository;
import com.pruebatecnica.solicitudes.repository.SucursalRepository;
import com.pruebatecnica.solicitudes.service.ISolicitudService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SolicitudServiceImpl implements ISolicitudService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public DecisionDTO procesarSolicitud(SolicitudDTO dto) {

        // Obtener usuario autenticado
       /*
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
*/
        // Buscar empleado que hizo la solicitud
        System.out.println("Datos - EmpleadoID: "+dto.getEmpleadoId());
        System.out.println("Datos - Monto: "+dto.getMonto());
        System.out.println("Datos - Ingresos: "+dto.getIngresosMensuales());
        System.out.println("Datos - Destino: "+dto.getDestino());
        System.out.println("Datos - ClienteID: "+dto.getClienteId());

        Empleado empleado = empleadoRepository.findById(dto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Sucursal sucursal = sucursalRepository.findById(dto.getSucursalId())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Solicitud solicitud = new Solicitud();
        solicitud.setCliente(cliente);
        solicitud.setMonto(dto.getMonto());
        solicitud.setPlazoMeses(dto.getPlazoMeses());
        solicitud.setIngresosMensuales(dto.getIngresosMensuales());
        solicitud.setDestino(dto.getDestino());
        solicitud.setSucursal(sucursal);
        solicitud.setProcesadoPor(empleado);
        solicitud.setFechaSolicitud(Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        System.out.println("➡ Iniciando evaluación de solicitud ...");
        System.out.println("Monto: " + dto.getMonto());
        System.out.println("Plazo: " + dto.getPlazoMeses());
        System.out.println("Ingresos: " + dto.getIngresosMensuales());


        String decision = evaluarSolicitud(dto.getMonto(), dto.getPlazoMeses(), dto.getIngresosMensuales());


        System.out.println(" Solicitud evaluada desde procedimiento almacenado: " + decision);

        DecisionDTO response = new DecisionDTO();
        response.setDecision(decision);
        response.setMotivo(Objects.equals(decision, "APROBADO") ?"Score suficiente":"Score insuficiente");

        //System.out.println("estado: " +decision);

        solicitud.setScore(0);  //TODO pendiente
        solicitud.setEstado(decision);
        solicitud.setDecisionMotivo(Objects.equals(solicitud.getEstado(), "APROBADO") ?"Score suficiente":"Score insuficiente");

        Solicitud guardada = solicitudRepository.save(solicitud);

        response.setSolicitudId(guardada.getId());
        response.setScore(0); //pendiente
        return response;
    }

    @Override
    public List<DecisionDTO> procesarLote(List<SolicitudDTO> solicitudes) {
        List<DecisionDTO> resultados = new ArrayList<>();
        for (SolicitudDTO s : solicitudes) {
            DecisionDTO resultado = procesarSolicitud(s);
            resultados.add(resultado);
        }
        return resultados;
    }

    @Override
    public Solicitud guardar(SolicitudDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Solicitud solicitud = new Solicitud();
        solicitud.setCliente(cliente);
        solicitud.setMonto(dto.getMonto());
        solicitud.setPlazoMeses(dto.getPlazoMeses());
        solicitud.setIngresosMensuales(dto.getIngresosMensuales());
        solicitud.setDestino(dto.getDestino());
        solicitud.setEstado("PENDIENTE");

        return solicitudRepository.save(solicitud);
    }

    @Override
    public List<Solicitud> listar() {
        return solicitudRepository.findAll();
    }

    @Override
    public List<SolicitudListDTO> listarSolicitudes() {
        return solicitudRepository.listarResumen();
    }


    @Override
    public String evaluarSolicitud(BigDecimal monto, Integer plazo, BigDecimal ingresos) {
        // Llama al procedimiento almacenado definido en el Repository
       // return solicitudRepository.calcularDecision(monto, plazo, ingresos);


        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_calcular_decision");

        query.registerStoredProcedureParameter("p_monto", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_plazo", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_ingresos", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_decision", String.class, ParameterMode.OUT);

        query.setParameter("p_monto", monto);
        query.setParameter("p_plazo", plazo);
        query.setParameter("p_ingresos", ingresos);

        query.execute();

        return (String) query.getOutputParameterValue("p_decision");
    }
}
