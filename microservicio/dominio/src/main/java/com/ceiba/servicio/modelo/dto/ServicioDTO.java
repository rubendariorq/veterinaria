package com.ceiba.servicio.modelo.dto;

public class ServicioDTO {

    private Long id;
    private String descripcion;
    private Double valor;

    public ServicioDTO() {
    }

    public ServicioDTO(Long id, String descripcion, Double valor) {
        this.id = id;
        this.descripcion = descripcion;
        this.valor = valor;
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
