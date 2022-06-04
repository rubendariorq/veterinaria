package com.ceiba.tratamiento.modelo.entidad;

import com.ceiba.cupon.modelo.entidad.Cupon;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.servicio.modelo.entidad.Servicio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tratamiento {

    private static final Double TASA_RECARGO = 0.15D;
    private static final String[] DIAS_FESTIVOS_2022 = {"2022-01-01", "2022-01-10", "2022-03-21", "2022-04-14", "2022-04-15", "2022-05-01",
            "2022-05-30", "2022-06-20", "2022-06-27", "2022-07-04", "2022-07-20", "2022-08-07", "2022-08-15", "2022-10-17",
            "2022-11-07", "2022-11-14", "2022-12-08", "2022-12-25"};

    private Long id;
    private String codigoTratamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long tipoTratamiento;
    private Mascota mascota;
    private Servicio servicio;
    private Double valor;
    private Cupon cupon;

    public Tratamiento (Mascota mascota, Servicio servicio, Cupon cupon, String codigoTratamiento, LocalDate fechaInicio,
                         LocalDate fechaFin, Long tipoTratamiento) {
        this.mascota = mascota;
        this.servicio = servicio;
        this.codigoTratamiento = codigoTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoTratamiento = tipoTratamiento;
        this.cupon = cupon;
        this.valor = calcularValorTratamiento();
    }

    public Tratamiento(Long id, Mascota mascota, Servicio servicio, String codigoTratamiento, LocalDate fechaInicio,
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
        ValidadorArgumento.validarObligatorio(codigoTratamiento, "El código del tratamiento es requerido");
        ValidadorArgumento.validarObligatorio(fechaInicio, "Debe indicar una fecha de inicio");
        ValidadorArgumento.validarObligatorio(fechaFin, "Debe indicar una fecha de finalización");
        ValidadorArgumento.validarObligatorio(tipoTratamiento, "Debe indicar el tipo de tratamiento");
        validarFechas(fechaInicio, fechaFin);

        return new Tratamiento(mascota, servicio, cupon, codigoTratamiento, fechaInicio, fechaFin, tipoTratamiento);
    }

    public static Tratamiento reconstruir(Long id, Mascota mascota, Servicio servicio, String codigoTratamiento,
                                          LocalDate fechaInicio, LocalDate fechaFin, Long tipoTratamiento, Double valor) {
        ValidadorArgumento.validarObligatorio(id, "El id de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(mascota, "La mascota es requerida");
        ValidadorArgumento.validarObligatorio(servicio, "El servicio es requerido");
        ValidadorArgumento.validarObligatorio(codigoTratamiento, "El código del tratamiento es requerido");
        ValidadorArgumento.validarObligatorio(fechaInicio, "Debe indicar una fecha de inicio");
        ValidadorArgumento.validarObligatorio(fechaFin, "Debe indicar una fecha de finalización");
        ValidadorArgumento.validarObligatorio(tipoTratamiento, "Debe indicar el tipo de tratamiento");
        ValidadorArgumento.validarObligatorio(valor, "Debe indicar el valor del tratamiento");
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

    private Double calcularValorTratamiento() {
        Double valorTratamiento = this.servicio.getValor();

        List<String> festivoFiltrado = Arrays.stream(DIAS_FESTIVOS_2022)
                .filter(dia -> dia.equals(this.fechaInicio.toString()))
                .collect(Collectors.toList());

        if (!festivoFiltrado.isEmpty()) {
            valorTratamiento *= (1 + TASA_RECARGO);
        }
        if ((this.cupon != null) && (LocalDate.now().isBefore(this.cupon.getFechaVigencia())
                || LocalDate.now().equals(this.cupon.getFechaVigencia()))) {
                valorTratamiento *= (1 - this.cupon.getValorDescuento());
        }
        return valorTratamiento;
    }
}
