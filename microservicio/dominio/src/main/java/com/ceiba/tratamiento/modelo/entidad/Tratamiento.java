package com.ceiba.tratamiento.modelo.entidad;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.servicio.entidad.Servicio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tratamiento {

    private static final Double TASA_RECARGO = 0.15D;
    private static final String[] DIAS_FESTIVOS_2022 = {"01-01-2022", "10-01-2022", "21-03-2022", "14-04-2022", "15-04-2022", "01-05-2022",
            "30-05-2022", "20-06-2022", "27-06-2022", "04-07-2022", "20-07-2022", "07-08-2022", "15-08-2022", "17-10-2022",
            "07-11-2022", "14-11-2022", "08-12-2022", "25-12-2022"};

    private Long id;
    private String codigoTratamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long tipoTratamiento;
    private Mascota mascota;
    private Servicio servicio;
    private Double valor;

    private Tratamiento (Mascota mascota, Servicio servicio, Cupon cupon, String codigoTratamiento, LocalDate fechaInicio,
                         LocalDate fechaFin, Long tipoTratamiento) {
        this.mascota = mascota;
        this.servicio = servicio;
        this.codigoTratamiento = codigoTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoTratamiento = tipoTratamiento;
        this.valor = calcularValorTratamiento(servicio, cupon);
    }

    private Tratamiento(Long id, Mascota mascota, Servicio servicio, String codigoTratamiento, LocalDate fechaInicio,
                        LocalDate fechaFin, Long tipoTratamiento, Double valor) {
        this.id = id;
        this.mascota = mascota;
        this.servicio = servicio;
        this.codigoTratamiento = codigoTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoTratamiento = tipoTratamiento;
        this.valor = valor;
    }

    public static Tratamiento crear(Mascota mascota, Servicio servicio, Cupon cupon, String codigoTratamiento,
                                    LocalDate fechaInicio, LocalDate fechaFin, Long tipoTratamiento) {
        ValidadorArgumento.validarObligatorio(mascota, "La mascota es requerida");
        ValidadorArgumento.validarObligatorio(servicio, "El servicio es requerido");
        ValidadorArgumento.validarObligatorio(cupon, "El cupón es requerido");
        ValidadorArgumento.validarObligatorio(codigoTratamiento, "El código del tratamiento es requerido");
        ValidadorArgumento.validarObligatorio(fechaInicio, "Debe indicar una fecha de inicio");
        ValidadorArgumento.validarObligatorio(fechaFin, "Debe indicar una fecha de finalización");
        ValidadorArgumento.validarObligatorio(tipoTratamiento, "Debe indicar el tipo de tratamiento");
        validarFechas(fechaInicio, fechaFin);

        return new Tratamiento(mascota, servicio, cupon, codigoTratamiento, fechaInicio, fechaFin, tipoTratamiento);
    }

    public static Tratamiento reconstruir(Long id, Mascota mascota, Servicio servicio, String codigoTratamiento,
                                          LocalDate fechaInicio, LocalDate fechaFin, Long tipoTratamiento, Double valor) {
        return new Tratamiento(id, mascota, servicio, codigoTratamiento, fechaInicio, fechaFin, tipoTratamiento, valor);
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

    public Mascota getMascota() {
        return mascota;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Double getValor() {
        return valor;
    }

    private static void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) {
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

    private Double calcularValorTratamiento(Servicio servicio, Cupon cupon) {
        Double valorTratamiento = servicio.getValor();

        List<String> festivoFiltrado = Arrays.stream(DIAS_FESTIVOS_2022)
                .filter(dia -> dia.equals(LocalDate.now().toString()))
                .collect(Collectors.toList());

        if (!festivoFiltrado.isEmpty()) {
            valorTratamiento *= (1 + TASA_RECARGO);
        }
        if (LocalDate.now().isBefore(cupon.getFechaVigencia())
                || LocalDate.now().equals(cupon.getFechaVigencia())) {
            valorTratamiento *= (1 - cupon.getValorDescuento());
        }
        return valorTratamiento;
    }
}
