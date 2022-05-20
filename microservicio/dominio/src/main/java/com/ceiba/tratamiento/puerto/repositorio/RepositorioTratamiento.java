package com.ceiba.tratamiento.puerto.repositorio;

import com.ceiba.tratamiento.modelo.entidad.Tratamiento;

public interface RepositorioTratamiento {
    Tratamiento guardar(Tratamiento tratamiento);
    Tratamiento obtener(Long idTratamiento);
    String eliminar(Tratamiento tratamiento);
}
