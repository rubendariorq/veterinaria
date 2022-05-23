package com.ceiba.mascota.puerto.repositorio;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.mascota.modelo.entidad.Mascota;

import java.util.List;

public interface RepositorioMascota {
    Mascota guardar(Mascota mascota, Cupon cupon);
    Mascota obtener(Long idMascota);
}
