package com.ceiba.cupon.puerto.repositorio;

import com.ceiba.cupon.entidad.Cupon;

public interface RepositorioCupon {
    Long guardar (Cupon cupon, Long idMascota);
    Cupon obtener(Long idCupon);
}
