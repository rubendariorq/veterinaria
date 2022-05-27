package com.ceiba.servicio.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public class Servicio {
    private Long id;
    private String descripcion;
    private Double valor;

    public Servicio(String descripcion, Double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Servicio(Long id, String descripcion, Double valor) {
        this.id = id;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public static Servicio crear(String descripcion, Double valor) {
        ValidadorArgumento.validarObligatorio(descripcion, "La descripción del servicio es requerida");
        ValidadorArgumento.validarObligatorio(valor, "El valor del servicio es requerido");
        return new Servicio(descripcion, valor);
    }

    public static Servicio reconstruir(Long id, String descripcion, Double valor) {
        ValidadorArgumento.validarObligatorio(id, "El id del servicio es requerido");
        ValidadorArgumento.validarObligatorio(descripcion, "La descripción del servicio es requerida");
        ValidadorArgumento.validarObligatorio(valor, "El valor del servicio es requerido");
        return new Servicio(id, descripcion, valor);
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getValor() {
        return valor;
    }
}
