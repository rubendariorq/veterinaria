package com.ceiba.tratamiento.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioTratamientoMysql implements RepositorioTratamiento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoTratamiento mapeoTratamiento;

    public RepositorioTratamientoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoTratamiento mapeoTratamiento) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoTratamiento = mapeoTratamiento;
    }

    @SqlStatement(namespace = "tratamiento", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "tratamiento", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "tratamiento", value = "eliminarporid")
    private static String sqlEliminarPorId;

    @Override
    public Tratamiento guardar(Tratamiento tratamiento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigo_tratamiento", tratamiento.getCodigoTratamiento());
        paramSource.addValue("id_mascota", tratamiento.getIdMascota());
        paramSource.addValue("fecha_inicio", tratamiento.getFechaInicio());
        paramSource.addValue("fecha_fin", tratamiento.getFechaFin());
        paramSource.addValue("tipo_tratamiento", tratamiento.getTipoTratamiento());
        paramSource.addValue("id_servicio", tratamiento.getIdServicio());
        paramSource.addValue("valor", tratamiento.getValor());
        Long idTratamiento = this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
        return Tratamiento.reconstruir(idTratamiento, tratamiento.getCodigoTratamiento(), tratamiento.getIdMascota(),
                tratamiento.getFechaInicio(), tratamiento.getFechaFin(), tratamiento.getTipoTratamiento(), tratamiento.getIdServicio());
    }

    public Tratamiento obtener(Long idTratamiento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idTratamiento);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId, paramSource, mapeoTratamiento));
    }

    public String eliminar(Tratamiento tratamiento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", tratamiento.getId());
        EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .update(sqlEliminarPorId, paramSource));
        return "El tratamiento " + tratamiento.getCodigoTratamiento() + " fue eliminado satisfactoriamente";
    }
}
