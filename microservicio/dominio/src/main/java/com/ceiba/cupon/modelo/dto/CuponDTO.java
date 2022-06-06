package com.ceiba.cupon.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CuponDTO {

    private Long id;
    private String codigoCupon;
    private Double valorDescuento;
    private LocalDate fechaVigencia;
    private Long idMascota;
}
