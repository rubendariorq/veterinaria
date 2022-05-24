package com.ceiba.tratamiento.puerto.repositorio;

import com.ceiba.tratamiento.modelo.entidad.Tratamiento;

import java.util.List;

public interface RepositorioTratamiento {
    Tratamiento guardar(Tratamiento tratamiento);
    Tratamiento obtener(Long idTratamiento);
    String eliminar(Tratamiento tratamiento);
    List<Tratamiento> listarPorMascotayTipo(Long idMascota, Long tipoTratamiento);
    Tratamiento obtenerUltimoTratamientoMedico(Long idMascota, Long tipoTratamiento);
}
