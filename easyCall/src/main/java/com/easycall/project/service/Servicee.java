package com.easycall.project.service;


import com.easycall.project.data.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "service")
public class Servicee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(length = 100)
    private String nombre;

    private int precio;

    @Column(length = 400)
    private String descripcion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servicee)) return false;
        Servicee servicee = (Servicee) o;
        return Objects.equals(getServiceId(), servicee.getServiceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceId());
    }


    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
