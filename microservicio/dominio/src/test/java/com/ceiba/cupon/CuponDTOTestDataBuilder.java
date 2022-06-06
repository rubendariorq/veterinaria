package com.ceiba.cupon;

import com.ceiba.cupon.modelo.dto.CuponDTO;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;

import java.time.LocalDate;

public class CuponDTOTestDataBuilder {

    private Long id;
    private String codigoCupon;
    private Double valorDescuento;
    private LocalDate fechaVigencia;
    private Long idMascota;

    public CuponDTOTestDataBuilder conCuponDTOPorDefecto(Long id, String codigoCupon, Double valorDescuento, LocalDate fechaVigencia, Long idMascota) {
        this.id = 1l;
        this.codigoCupon = "WELCOME";
        this.valorDescuento = 0.10D;
        this.fechaVigencia = LocalDate.now();
        this.idMascota = 1l;
        return this;
    }

    public CuponDTOTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public CuponDTOTestDataBuilder conCodigoCupon(String codigoCupon) {
        this.codigoCupon = codigoCupon;
        return this;
    }

    public CuponDTOTestDataBuilder conValorDescuento(Double valorDescuento) {
        this.valorDescuento = valorDescuento;
        return this;
    }

    public CuponDTOTestDataBuilder conFechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
        return this;
    }

    public CuponDTOTestDataBuilder conIdMascota(Long idMascota) {
        this.idMascota = idMascota;
        return this;
    }

    public CuponDTO crear() {
        return new CuponDTO(id, codigoCupon, valorDescuento, fechaVigencia, idMascota);
    }
}
