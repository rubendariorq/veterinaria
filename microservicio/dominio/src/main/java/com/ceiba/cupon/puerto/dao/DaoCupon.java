package com.ceiba.cupon.puerto.dao;

import com.ceiba.cupon.modelo.dto.CuponDTO;

import java.util.List;

public interface DaoCupon {

    List<CuponDTO> listarPorIdMascota(Long idMascota);
}
