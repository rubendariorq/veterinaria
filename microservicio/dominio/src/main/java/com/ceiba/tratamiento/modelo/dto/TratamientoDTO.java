package com.ceiba.tratamiento.modelo.dto;

import java.time.LocalDate;

public class TratamientoDTO {
    private Long id;
    private String codigoTratamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long tipoTratamiento;
    private Long idMascota;
    private Long idServicio;
    private Double valor;

    public TratamientoDTO() {
    }

    public TratamientoDTO(Long id, String codigoTratamiento, LocalDate fechaInicio, LocalDate fechaFin, Long tipoTratamiento, Long idMascota, Long idServicio, Double valor) {
        this.id = id;
        this.codigoTratamiento = codigoTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoTratamiento = tipoTratamiento;
        this.idMascota = idMascota;
        this.idServicio = idServicio;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoTratamiento() {
        return codigoTratamiento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Long getTipoTratamiento() {
        return tipoTratamiento;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public Double getValor() {
        return valor;
    }
}
