package com.ceiba.mascota.controlador;

import com.ceiba.mascota.comando.ComandoSolicitudRegistrarMascota;

public class ComandoRegistrarMascotaTestDataBuilder {
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;

    public ComandoRegistrarMascotaTestDataBuilder conComandoRegistarMascotaPorDefecto() {
        this.codigoMascota = "MASC010";
        this.nombre = "Manotas";
        this.tipoMascota = 1l;
        return this;
    }

    public ComandoSolicitudRegistrarMascota build() {
        return new ComandoSolicitudRegistrarMascota(codigoMascota, nombre, tipoMascota);
    }


}
