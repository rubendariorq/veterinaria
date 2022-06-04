package com.ceiba.cupon.modelo.dto;

import java.time.LocalDate;

public class CuponDTO {

    private Long id;
    private String codigoCupon;
    private Double valorDescuento;
    private LocalDate fechaVigencia;

    private Long idMascota;

    public CuponDTO() {
    }

    public CuponDTO(Long id, String codigoCupon, Double valorDescuento, LocalDate fechaVigencia, Long idMascota) {
        this.id = id;
        this.codigoCupon = codigoCupon;
        this.valorDescuento = valorDescuento;
        this.fechaVigencia = fechaVigencia;
        this.idMascota = idMascota;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoCupon() {
        return codigoCupon;
    }

    public Double getValorDescuento() {
        return valorDescuento;
    }

    public LocalDate getFechaVigencia() {
        return fechaVigencia;
    }

    public Long getIdMascota() {
        return idMascota;
    }
}
