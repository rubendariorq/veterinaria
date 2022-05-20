package com.ceiba.tratamiento.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.servicio.entidad.Servicio;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Tratamiento {

    private static final Long TRATAMIENTO_ESTETICO = 1L;
    private static final Long TRATAMIENTO_MEDICO = 2L;

    private Long id;
    private String codigoTratamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long tipoTratamiento;
    private Long idMascota;
    private Long idServicio;
    private Double valor;

    private Tratamiento (Mascota mascota, Servicio servicio, Tratamiento tratamiento) {
        this.codigoTratamiento = tratamiento.getCodigoTratamiento();
        this.fechaInicio = tratamiento.getFechaInicio();
        this.fechaFin = tratamiento.getFechaFin();
        this.tipoTratamiento = tratamiento.getTipoTratamiento();
        this.idMascota = mascota.getId();
        this.idServicio = servicio.getId();
        this.valor = 0D;
    }

    public Tratamiento(Long id, String codigoTratamiento, LocalDate fechaInicio, LocalDate fechaFin, Long tipoTratamiento, Long idMascota, Long idServicio) {
        this.id = id;
        this.codigoTratamiento = codigoTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoTratamiento = tipoTratamiento;
        this.idMascota = idMascota;
        this.idServicio = idServicio;
        this.valor = 0D;
    }

    public static Tratamiento crear(SolicitudIniciarTratamiento solicitudIniciarTratamiento) {
        ValidadorArgumento.validarObligatorio(solicitudIniciarTratamiento.getMascota(), "La mascota es requerida");
        ValidadorArgumento.validarObligatorio(solicitudIniciarTratamiento.getServicio(), "El servicio es requerido");
        ValidadorArgumento.validarObligatorio(solicitudIniciarTratamiento.getTratamiento(), "El tratamiento es requerido");
        return new Tratamiento(solicitudIniciarTratamiento.getMascota(), solicitudIniciarTratamiento.getServicio(),
                solicitudIniciarTratamiento.getTratamiento());
    }

    public static Tratamiento construir(String codigoTratamiento, Long idMascota, LocalDate fechaInicio, LocalDate fechaFin,
                                        Long tipoTratamiento, Long idServicio) {
        validarArgumentosComunes(codigoTratamiento, idMascota, fechaInicio, fechaFin, tipoTratamiento, idServicio);

        return new Tratamiento(null, codigoTratamiento, fechaInicio, fechaFin, tipoTratamiento, idMascota, idServicio);
    }

    public static Tratamiento reconstruir(Long id, String codigoTratamiento, Long idMascota, LocalDate fechaInicio, LocalDate fechaFin,
                                        Long tipoTratamiento, Long idServicio) {
        ValidadorArgumento.validarObligatorio(id, "El id del tratamiento es requerido");
        validarArgumentosComunes(codigoTratamiento, idMascota, fechaInicio, fechaFin, tipoTratamiento, idServicio);

        return new Tratamiento(id, codigoTratamiento, fechaInicio, fechaFin, tipoTratamiento, idMascota, idServicio);
    }

    private static void validarArgumentosComunes(String codigoTratamiento, Long idMascota, LocalDate fechaInicio, LocalDate fechaFin,
                                                 Long tipoTratamiento, Long idServicio) {
        ValidadorArgumento.validarObligatorio(codigoTratamiento, "El código del tratamiento es requerido");
        ValidadorArgumento.validarObligatorio(idMascota, "El id de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(fechaInicio, "Debe indicar una fecha de inicio");
        ValidadorArgumento.validarObligatorio(fechaFin, "Debe indicar una fecha de finalización");
        ValidadorArgumento.validarObligatorio(tipoTratamiento, "Debe indicar el tipo de tratamiento");
        ValidadorArgumento.validarObligatorio(idServicio, "El id del servicio es requerido");
        if (validarDiaNoLaboral(fechaInicio)) {
            throw new ExcepcionValorInvalido("La fecha de inicio indicada es un día no laboral");
        }
        if (validarDiaNoLaboral(fechaFin)) {
            throw new ExcepcionValorInvalido("La fecha de finalización indicada es un día no laboral");
        }
    }

    private static boolean validarDiaNoLaboral(LocalDate dia) {
        return (dia.getDayOfWeek() == DayOfWeek.SATURDAY || dia.getDayOfWeek() == DayOfWeek.SUNDAY);
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
