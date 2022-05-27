package com.ceiba.mascota.controlador;

import com.ceiba.mascota.modelo.dto.MascotaDTO;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class RespuestaMascotas {

    private List<MascotaDTO> valor;

    public RespuestaMascotas() {
    }

    @JsonCreator
    public RespuestaMascotas(List<MascotaDTO> valor) {
        this.valor = valor;
    }

    public List<MascotaDTO> getValor() {
        return valor;
    }
}
