package com.ceiba.servicio.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.servicio.entidad.Servicio;
import com.ceiba.servicio.puerto.repositorio.RepositorioServicio;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioServicioMysql implements RepositorioServicio {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "servicio", value = "crear")
    private static String sqlCrear;
    @SqlStatement(namespace = "servicio", value = "obtenerporid")
    private static String sqlObtenerPorId;

    public RepositorioServicioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Servicio obtener(Long idServicio) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idServicio);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,
                        paramSource, new MapeoServicio()));
    }

    @Override
    public Servicio guardar(Servicio servicio) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("descripcion", servicio.getDescripcion());
        paramSource.addValue("valor", servicio.getValor());
        Long id = this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
        return Servicio.reconstruir(id, servicio.getDescripcion(), servicio.getValor());
    }
}
