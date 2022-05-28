package com.ceiba.mascota.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MascotaDTO {
    private Long id;
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;
}
