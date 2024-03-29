package com.ceiba.cupon.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.mascota.modelo.entidad.Mascota;

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

    private Mascota mascota;

    public Cupon(Mascota mascota) {
        this.codigoCupon = generarCodigoCupon(mascota.getNombre());
        this.valorDescuento = VALOR_DESCUENTO;
        this.fechaVigencia = calcularFecha();
    }

    public Cupon(Long id, String codigoCupon, Double valorDescuento, LocalDate fechaVigencia) {
        this.id = id;
        this.codigoCupon = codigoCupon;
        this.valorDescuento = valorDescuento;
        this.fechaVigencia = fechaVigencia;
    }

    public static Cupon crear(Mascota mascota) {
        ValidadorArgumento.validarObligatorio(mascota, "La mascota es requerida");
        return new Cupon(mascota);
    }

    public static Cupon reconstruir(Long id, String codigoCupon, Double valorDescuento, LocalDate fechaVigencia) {
        ValidadorArgumento.validarObligatorio(id, "El id del cupón es requerido");
        ValidadorArgumento.validarObligatorio(codigoCupon, "El código del cupón es requerido");
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
