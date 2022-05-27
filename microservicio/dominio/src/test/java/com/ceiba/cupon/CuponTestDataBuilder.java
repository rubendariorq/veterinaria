package com.ceiba.cupon;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.mascota.MascotaTestDataBuilder;
import com.ceiba.mascota.modelo.entidad.Mascota;

import java.time.LocalDate;

public class CuponTestDataBuilder {

    private Long id;
    private String codigoCupon;
    private Double valorDescuento;
    private LocalDate fechaVigencia;
    private Mascota mascota;

    public CuponTestDataBuilder conCuponPorDefecto() {
        this.id = 1l;
        this.codigoCupon = "WELCOME";
        this.valorDescuento = 0.10D;
        this.fechaVigencia = LocalDate.of(2022, 12,30);
        this.mascota = new MascotaTestDataBuilder().conMascotaPorDefecto().reconstruir();
        return this;
    }

    public CuponTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public CuponTestDataBuilder conCodigoCupon(String codigoCupon) {
        this.codigoCupon = codigoCupon;
        return this;
    }

    public CuponTestDataBuilder conValorDescuento(Double valorDescuento) {
        this.valorDescuento = valorDescuento;
        return this;
    }

    public CuponTestDataBuilder conFechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
        return this;
    }

    public CuponTestDataBuilder conMascota(Mascota mascota) {
        this.mascota = mascota;
        return this;
    }

    public Cupon crear() {
        return Cupon.crear(mascota);
    }

    public Cupon reconstruir() {
        return Cupon.reconstruir(id, codigoCupon, valorDescuento, fechaVigencia);
    }
}
