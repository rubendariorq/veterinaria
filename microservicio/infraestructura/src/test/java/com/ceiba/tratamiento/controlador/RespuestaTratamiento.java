package com.ceiba.tratamiento.controlador;

import com.ceiba.tratamiento.modelo.dto.TratamientoDTO;

public class RespuestaTratamiento {
    private TratamientoDTO valor;

    public RespuestaTratamiento() {
    }

    public RespuestaTratamiento(TratamientoDTO valor) {
        this.valor = valor;
    }

    public TratamientoDTO getValor() {
        return valor;
    }
}
