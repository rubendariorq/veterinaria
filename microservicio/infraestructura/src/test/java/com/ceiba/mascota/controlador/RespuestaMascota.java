package com.ceiba.mascota.controlador;

import com.ceiba.mascota.modelo.dto.MascotaDTO;

public class RespuestaMascota {

    private MascotaDTO valor;

    public RespuestaMascota() {
    }

    public RespuestaMascota(MascotaDTO valor) {
        this.valor = valor;
    }

    public MascotaDTO getValor() {
        return valor;
    }
}
