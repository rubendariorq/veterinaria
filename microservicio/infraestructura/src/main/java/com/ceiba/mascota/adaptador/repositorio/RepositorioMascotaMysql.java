package com.ceiba.mascota.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioMascotaMysql implements RepositorioMascota {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioMascotaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "mascota", value = "crear")
    private static String sqlCrear;

    @Override
    public Long guardar(Mascota mascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigo_mascota", mascota.getCodigoMascota());
        paramSource.addValue("nombre", mascota.getNombre());
        paramSource.addValue("tipo_mascota", mascota.getTipoMascota());
        Long idMascota = this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
        return idMascota;
    }
}
