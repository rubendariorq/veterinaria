package com.ceiba.servicio.controlador;

import com.ceiba.servicio.comando.ComandoSolicitudRegistrarServicio;

public class ComandoRegistrarServicioTestDataBuilder {

    private String descripcion;
    private Double valor;

    public ComandoRegistrarServicioTestDataBuilder conComandoRegistarServicioPorDefecto() {
        this.descripcion = "Tratamiento medico";
        this.valor = 50000D;
        return this;
    }

    public ComandoSolicitudRegistrarServicio build() {
        return new ComandoSolicitudRegistrarServicio(descripcion, valor);
    }
}
