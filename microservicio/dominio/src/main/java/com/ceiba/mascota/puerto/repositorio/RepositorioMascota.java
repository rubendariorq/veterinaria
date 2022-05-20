package com.ceiba.mascota.puerto.repositorio;

import com.ceiba.mascota.modelo.entidad.Mascota;

import java.util.List;

public interface RepositorioMascota {
    Long guardar(Mascota mascota);
}
