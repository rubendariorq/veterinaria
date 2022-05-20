package com.ceiba.mascota.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mascota.modelo.dto.MascotaDTO;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoMascotaMysql implements DaoMascota {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoMascotaResumen mapeoMascotaResumen;

    @SqlStatement(namespace = "mascota", value = "listar")
    private static String sqlListarMascotas;

    public DaoMascotaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoMascotaResumen mapeoMascotaResumen) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoMascotaResumen = mapeoMascotaResumen;
    }

    @Override
    public List<MascotaDTO> listarMascotas() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarMascotas, mapeoMascotaResumen);
    }
}
