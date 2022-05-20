package com.ceiba.cupon.puerto.repositorio;

import com.ceiba.cupon.entidad.Cupon;

import java.util.List;

public interface RepositorioCupon {
    void guardar (List<Cupon> cupones, Long idMascota);
}
