package com.ceiba.tratamiento.controlador;

import com.ceiba.tratamiento.comando.ComandoSolicitudIniciarTratamiento;

import java.time.LocalDate;

public class ComandoRegistrarTratamientoTestDataBuilder {

    private String codigoTratamiento;
    private Long idMascota;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long idServicio;
    private Long tipoTratamiento;
    private Long idCupon;

    public ComandoRegistrarTratamientoTestDataBuilder conComandoRegistrarTratamientoPorDefecto() {
        this.codigoTratamiento = "TRAT001";
        this.idMascota = 1l;
        this.fechaInicio = LocalDate.of(2022,05,30);
        this.fechaFin = LocalDate.of(2022,06,06);
        this.idServicio = 1l;
        this.tipoTratamiento = 2l;
        this.idCupon = 1l;
        return this;
    }

    public ComandoSolicitudIniciarTratamiento build() {
        return new ComandoSolicitudIniciarTratamiento(codigoTratamiento, idMascota, fechaInicio, fechaFin, idServicio, tipoTratamiento, idCupon);
    }
}
