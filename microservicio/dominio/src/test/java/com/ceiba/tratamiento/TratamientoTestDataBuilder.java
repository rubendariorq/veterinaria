package com.ceiba.tratamiento;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.mascota.MascotaTestDataBuilder;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.servicio.ServicioTestDataBuilder;
import com.ceiba.servicio.modelo.entidad.Servicio;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;

import java.time.LocalDate;

public class TratamientoTestDataBuilder {

    private Long id;
    private String codigoTratamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long tipoTratamiento;
    private Mascota mascota;
    private Servicio servicio;
    private Double valor;
    private Cupon cupon;

    public TratamientoTestDataBuilder conTratamientoPorDefecto() {
        this.id = 1l;
        this.codigoTratamiento = "TRAT001";
        this.fechaInicio = LocalDate.of(2022,06,01);
        this.fechaFin = LocalDate.of(2022,06,04);
        this.tipoTratamiento = 2l;
        this.mascota = new MascotaTestDataBuilder().conId(1l).conNombre("Manotas").conTipoMascota(1l).conCodigoMascota("MASC001").reconstruir();
        this.servicio = new ServicioTestDataBuilder().conId(1l).conDescripcion("Tratamiento médico").conValor(49000D).reconstruir();
        this.valor = 49000D;
        return this;
    }

    public TratamientoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public TratamientoTestDataBuilder conCodigoTratamiento(String codigoTratamiento) {
        this.codigoTratamiento = codigoTratamiento;
        return this;
    }

    public TratamientoTestDataBuilder conFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public TratamientoTestDataBuilder conFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public TratamientoTestDataBuilder conTipoTratamiento(Long tipoTratamiento) {
        this.tipoTratamiento = tipoTratamiento;
        return this;
    }

    public TratamientoTestDataBuilder conMascota(Mascota mascota) {
        this.mascota = mascota;
        return this;
    }

    public TratamientoTestDataBuilder conServicio(Servicio servicio) {
        this.servicio = servicio;
        return this;
    }

    public TratamientoTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public TratamientoTestDataBuilder conCupon(Cupon cupon) {
        this.cupon = cupon;
        return this;
    }

    public Tratamiento crear() {
        return Tratamiento.crear(mascota, servicio, cupon, codigoTratamiento, fechaInicio, fechaFin, tipoTratamiento);
    }

    public Tratamiento reconstruir() {
        return Tratamiento.reconstruir(id, mascota, servicio, codigoTratamiento, fechaInicio, fechaFin, tipoTratamiento, valor);
    }
}
