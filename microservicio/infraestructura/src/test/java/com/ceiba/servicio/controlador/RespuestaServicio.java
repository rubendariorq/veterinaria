package com.ceiba.servicio.controlador;

import com.ceiba.servicio.modelo.dto.ServicioDTO;

public class RespuestaServicio {

    private ServicioDTO valor;

    public RespuestaServicio() {
    }

    public RespuestaServicio(ServicioDTO valor) {
        this.valor = valor;
    }

    public ServicioDTO getValor() {
        return valor;
    }
}
