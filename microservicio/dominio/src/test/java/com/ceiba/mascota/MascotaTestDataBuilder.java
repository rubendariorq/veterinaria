package com.ceiba.mascota;

import com.ceiba.mascota.modelo.entidad.Mascota;

public class MascotaTestDataBuilder {
    private Long id;
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;

    public MascotaTestDataBuilder conMascotaPorDefecto() {
        this.id = 1l;
        this.codigoMascota = "MASC001";
        this.nombre = "Manotas";
        this.tipoMascota = 1l;
        return this;
    }

    public MascotaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public MascotaTestDataBuilder conCodigoMascota(String codigoMascota) {
        this.codigoMascota = codigoMascota;
        return this;
    }

    public MascotaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public MascotaTestDataBuilder conTipoMascota(Long tipoMascota) {
        this.tipoMascota = tipoMascota;
        return this;
    }

    public Mascota crear() {
        return Mascota.crear(codigoMascota, nombre, tipoMascota);
    }

    public Mascota reconstruir() {
        return Mascota.reconstruir(id, codigoMascota, nombre, tipoMascota);
    }
}
