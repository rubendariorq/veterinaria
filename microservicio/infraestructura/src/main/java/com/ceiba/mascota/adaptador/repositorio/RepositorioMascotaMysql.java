package com.ceiba.mascota.adaptador.repositorio;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.cupon.puerto.repositorio.RepositorioCupon;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioMascotaMysql implements RepositorioMascota {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final RepositorioCupon repositorioCupon;

    public RepositorioMascotaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, RepositorioCupon repositorioCupon) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.repositorioCupon = repositorioCupon;
    }

    @SqlStatement(namespace = "mascota", value = "crear")
    private static String sqlCrear;
    @SqlStatement(namespace = "mascota", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @Override
    public Mascota guardar(Mascota mascota, Cupon cupon) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigo_mascota", mascota.getCodigoMascota());
        paramSource.addValue("nombre", mascota.getNombre());
        paramSource.addValue("tipo_mascota", mascota.getTipoMascota());
        Long idMascota = this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
        this.repositorioCupon.guardar(cupon, idMascota);
        return Mascota.reconstruir(idMascota, mascota.getCodigoMascota(), mascota.getNombre(), mascota.getTipoMascota());
    }

    @Override
    public Mascota obtener(Long idMascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idMascota);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,
                        paramSource, new MapeoMascota()));
    }

}
