package com.ceiba.servicio.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.servicio.modelo.dto.ServicioDTO;
import com.ceiba.servicio.puerto.dao.DaoServicio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoServicioMysql implements DaoServicio {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoServicioResumen mapeoServicioResumen;

    @SqlStatement(namespace = "servicio", value = "listar")
    private static String sqlListarServicios;

    public DaoServicioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoServicioResumen mapeoServicioResumen) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoServicioResumen = mapeoServicioResumen;
    }

    @Override
    public List<ServicioDTO> listarServicios() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarServicios, mapeoServicioResumen);
    }
}
