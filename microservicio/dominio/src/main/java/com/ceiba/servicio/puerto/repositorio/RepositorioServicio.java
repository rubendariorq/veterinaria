package com.ceiba.servicio.puerto.repositorio;

import com.ceiba.servicio.modelo.entidad.Servicio;

public interface RepositorioServicio {
    Servicio obtener(Long idServicio);
    Servicio guardar(Servicio servicio);
}
