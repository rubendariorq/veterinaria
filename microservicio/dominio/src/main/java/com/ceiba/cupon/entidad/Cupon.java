package com.ceiba.cupon.entidad;

import com.ceiba.dominio.ValidadorArgumento;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Cupon {
    private static final Double VALOR_DESCUENTO = 0.10;
    private static final String CUPON_BIENVENIDA = "WELCOME";
    private static final int DIAS_HABILES_VIGENCIA_CUPON = 3;

    private Long id;
    private String codigoCupon;
    private Double valorDescuento;
    private LocalDate fechaVigencia;

    private Cupon(String nombre) {
        this.codigoCupon = generarCodigoCupon(nombre);
        this.valorDescuento = VALOR_DESCUENTO;
        this.fechaVigencia = calcularFecha();
    }

    private Cupon(Long id, String codigoCupon, Double valorDescuento, LocalDate fechaVigencia) {
        this.id = id;
        this.codigoCupon = codigoCupon;
        this.valorDescuento = valorDescuento;
        this.fechaVigencia = fechaVigencia;
    }

    public static Cupon crear(String nombreMascota) {
        ValidadorArgumento.validarObligatorio(nombreMascota, "Nombre de la mascota requerido");
        return new Cupon(nombreMascota);
    }

    public static Cupon reconstruir(Long id, String codigoCupon, Double valorDescuento, LocalDate fechaVigencia) {
        ValidadorArgumento.validarObligatorio(id, "El id del cupón es requerido");
        ValidadorArgumento.validarObligatorio(codigoCupon, "El codigo del cupón es requerido");
        ValidadorArgumento.validarObligatorio(valorDescuento, "El valor de descuento del cupón es requerido");
        ValidadorArgumento.validarObligatorio(fechaVigencia, "La fecha de vigencia del cupón es requerida");
        return new Cupon(id, codigoCupon, valorDescuento, fechaVigencia);
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
}
