package com.ceiba.tratamiento.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @SqlStatement(namespace = "tratamiento", value = "listarpormascota")
    private static String sqlListarTratamientosPorMascota;

    @SqlStatement(namespace = "tratamiento", value = "obtenerultimotratamientomedicopormascota")
    private static String sqlObtenerUltimoTratamientoMedicoPorMascota;

    @Override
    public Tratamiento guardar(Tratamiento tratamiento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigo_tratamiento", tratamiento.getCodigoTratamiento());
        setearParametroIdMascota(paramSource, tratamiento.getMascota().getId());
        paramSource.addValue("fecha_inicio", tratamiento.getFechaInicio());
        paramSource.addValue("fecha_fin", tratamiento.getFechaFin());
        setearParametroTipoTratamiento(paramSource, tratamiento.getTipoTratamiento());
        paramSource.addValue("id_servicio", tratamiento.getServicio().getId());
        paramSource.addValue("valor", tratamiento.getValor());
        Long id = this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
        return Tratamiento.reconstruir(id, tratamiento.getMascota(), tratamiento.getServicio(), tratamiento.getCodigoTratamiento(),
                tratamiento.getFechaInicio(), tratamiento.getFechaFin(), tratamiento.getTipoTratamiento(), tratamiento.getValor());
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

    @Override
    public List<Tratamiento> listarPorMascotayTipo(Long idMascota, Long tipoTratamiento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        setearParametroIdMascota(paramSource, idMascota);
        setearParametroTipoTratamiento(paramSource, tipoTratamiento);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarTratamientosPorMascota, paramSource, mapeoTratamiento);
    }

    @Override
    public Tratamiento obtenerUltimoTratamientoMedico(Long idMascota, Long tipoTratamiento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        setearParametroIdMascota(paramSource, idMascota);
        setearParametroTipoTratamiento(paramSource, tipoTratamiento);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerUltimoTratamientoMedicoPorMascota, paramSource, mapeoTratamiento));
    }

    private void setearParametroIdMascota(MapSqlParameterSource paramSource, Long idMascota) {
        paramSource.addValue("id_mascota", idMascota);
    }
    private void setearParametroTipoTratamiento(MapSqlParameterSource paramSource, Long tipoTratamiento) {
        paramSource.addValue("tipo_tratamiento", tipoTratamiento);
    }
}
