package com.ceiba.mascota.puerto.dao;

import com.ceiba.mascota.modelo.dto.MascotaDTO;

import java.util.List;

public interface DaoMascota {
    List<MascotaDTO> listarMascotas();
}
