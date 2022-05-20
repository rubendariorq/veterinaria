package com.ceiba.tratamiento.puerto.dao;

import com.ceiba.tratamiento.modelo.dto.TratamientoDTO;

import java.util.List;

public interface DaoTratamiento {
    List<TratamientoDTO> listarTratamientos();
    TratamientoDTO obtener(Long idTratamiento);
}
