package com.ceiba.mascota.controlador;

import com.ceiba.mascota.modelo.dto.MascotaDTO;

import java.util.List;

public class RespuestaMascotas {

    private List<MascotaDTO> valor;

    public RespuestaMascotas() {
    }

    public RespuestaMascotas(List<MascotaDTO> valor) {
        this.valor = valor;
    }

    public List<MascotaDTO> getValor() {
        return valor;
    }
}
