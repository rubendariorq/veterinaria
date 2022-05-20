package com.ceiba.cupon.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.mascota.modelo.entidad.Mascota;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public class Cupon {
    private static final Double VALOR_DESCUENTO = 0.10;
    private static final String CUPON_BIENVENIDA = "WELCOME";
    private static final int DIAS_HABILES_VIGENCIA_CUPON = 3;

    private Long id;
    private String codigoCupon;
    private Double valorDescuento;
    private LocalDate fechaVigencia;

    private Cupon(String nombre) {
        this.codigoCupon = this.generarCodigoCupon(nombre);
        this.valorDescuento = VALOR_DESCUENTO;
        this.fechaVigencia = this.calcularFecha();
    }

    public static Cupon crear(Mascota mascota) {
        ValidadorArgumento.validarObligatorio(mascota, "La mascota es requerida");
        return new Cupon(mascota.getNombre());
    }

    private String generarCodigoCupon(String nombreMascota) {
        return nombreMascota + CUPON_BIENVENIDA;
    }

    private LocalDate calcularFecha() {
        LocalDate result = LocalDate.now();
        int diaAgregado = 0;
        while (diaAgregado < DIAS_HABILES_VIGENCIA_CUPON) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++diaAgregado;
            }
        }
        return result;
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
}
