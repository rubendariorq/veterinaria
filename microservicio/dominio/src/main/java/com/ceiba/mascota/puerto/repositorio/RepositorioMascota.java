package com.ceiba.mascota.puerto.repositorio;

import com.ceiba.cupon.modelo.entidad.Cupon;
import com.ceiba.mascota.modelo.entidad.Mascota;

public interface RepositorioMascota {
    Mascota guardar(Mascota mascota, Cupon cupon);
    Mascota obtener(Long idMascota);
}
