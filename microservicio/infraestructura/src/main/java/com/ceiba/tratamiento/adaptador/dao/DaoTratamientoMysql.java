package com.ceiba.tratamiento.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.servicio.adaptador.repositorio.MapeoServicio;
import com.ceiba.tratamiento.modelo.dto.TratamientoDTO;
import com.ceiba.tratamiento.puerto.dao.DaoTratamiento;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoTratamientoMysql implements DaoTratamiento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoResumenTratamiento mapeoResumenTratamiento;

    @SqlStatement(namespace = "tratamiento", value = "listar")
    private static String sqlListarTratamientos;

    @SqlStatement(namespace = "tratamiento", value = "obtenerporid")
    private static String sqlObtenerPorId;

    public DaoTratamientoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoResumenTratamiento mapeoResumenTratamiento) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoResumenTratamiento = mapeoResumenTratamiento;
    }

    @Override
    public List<TratamientoDTO> listarTratamientos() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarTratamientos, mapeoResumenTratamiento);
    }

    @Override
    public TratamientoDTO obtener(Long idTratamiento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idTratamiento);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,
                        paramSource, new MapeoResumenTratamiento()));
    }
}
