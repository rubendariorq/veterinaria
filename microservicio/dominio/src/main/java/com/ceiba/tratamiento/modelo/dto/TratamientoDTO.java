package com.ceiba.tratamiento.modelo.dto;

import java.time.LocalDate;

public class TratamientoDTO {
    private Long idTratamiento;
    private String codigoDeTratamiento;
    private LocalDate fechaDeInicio;
    private LocalDate fechaDeFin;
    private Long tipoDeTratamiento;
    private Long idMascota;
    private Long idServicio;
    private Double valor;

    public TratamientoDTO() {
    }

    public TratamientoDTO(Long idTratamiento, String codigoDeTratamiento, LocalDate fechaDeInicio, LocalDate fechaDeFin, Long tipoDeTratamiento, Long idMascota, Long idServicio, Double valor) {
        this.idTratamiento = idTratamiento;
        this.codigoDeTratamiento = codigoDeTratamiento;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeFin = fechaDeFin;
        this.tipoDeTratamiento = tipoDeTratamiento;
        this.idMascota = idMascota;
        this.idServicio = idServicio;
        this.valor = valor;
    }

    public Long getIdTratamiento() {
        return idTratamiento;
    }

    public String getCodigoDeTratamiento() {
        return codigoDeTratamiento;
    }

    public LocalDate getFechaDeInicio() {
        return fechaDeInicio;
    }

    public LocalDate getFechaDeFin() {
        return fechaDeFin;
    }

    public Long getTipoDeTratamiento() {
        return tipoDeTratamiento;
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
