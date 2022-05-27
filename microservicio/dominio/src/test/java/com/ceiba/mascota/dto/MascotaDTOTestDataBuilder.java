package com.ceiba.mascota.dto;

import com.ceiba.mascota.modelo.dto.MascotaDTO;

public class MascotaDTOTestDataBuilder {

    private Long id;
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;

    public MascotaDTOTestDataBuilder conMascotaDTOPorDefecto() {
        this.id = 1l;
        this.codigoMascota = "MASC001";
        this.nombre = "Manotas";
        this.tipoMascota = 1l;
        return this;
    }

    public MascotaDTOTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public MascotaDTOTestDataBuilder conCodigoMascota(String codigoMascota) {
        this.codigoMascota = codigoMascota;
        return this;
    }

    public MascotaDTOTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public MascotaDTOTestDataBuilder conTipoMascota(Long tipoMascota) {
        this.tipoMascota = tipoMascota;
        return this;
    }

    public MascotaDTO crear() {
        return new MascotaDTO(id, codigoMascota, nombre, tipoMascota);
    }
}
