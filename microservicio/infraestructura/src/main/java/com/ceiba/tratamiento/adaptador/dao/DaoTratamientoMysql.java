package com.ceiba.tratamiento.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tratamiento.modelo.dto.TratamientoDTO;
import com.ceiba.tratamiento.puerto.dao.DaoTratamiento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoTratamientoMysql implements DaoTratamiento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoResumenTratamiento mapeoResumenTratamiento;

    @SqlStatement(namespace = "tratamiento", value = "listar")
    private static String sqlListarTratamientos;

    public DaoTratamientoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoResumenTratamiento mapeoResumenTratamiento) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoResumenTratamiento = mapeoResumenTratamiento;
    }

    @Override
    public List<TratamientoDTO> listarTratamientos() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarTratamientos, mapeoResumenTratamiento);
    }
}
