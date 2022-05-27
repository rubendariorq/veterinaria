package com.ceiba.mascota.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MascotaDTO {
    private Long id;
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;
}
