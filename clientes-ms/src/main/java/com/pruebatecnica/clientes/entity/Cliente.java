package com.pruebatecnica.clientes.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table (name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String rfc;
    private String email;
    private String telefono;
    @Column(name = "creado_en")
    private LocalDateTime creadoEn = LocalDateTime.now();

    public Cliente() {
        // Constructor vacío requerido por JPA
    }
    public Cliente(Long id, String nombre, String rfc, String email, String telefono, LocalDateTime creadoEn) {
        this.id = id;
        this.nombre = nombre;
        this.rfc = rfc;
        this.email = email;
        this.telefono = telefono;
        this.creadoEn = creadoEn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}