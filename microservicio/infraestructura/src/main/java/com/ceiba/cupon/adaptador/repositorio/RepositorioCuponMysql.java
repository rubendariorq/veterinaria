package com.ceiba.cupon.adaptador.repositorio;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.cupon.puerto.repositorio.RepositorioCupon;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCuponMysql implements RepositorioCupon {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoCupon mapeoCupon;

    public RepositorioCuponMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoCupon mapeoCupon) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoCupon = mapeoCupon;
    }

    @SqlStatement(namespace = "cupon", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "cupon", value = "obtenerporid")
    private static String sqlObtenerPorId;

    @Override
    public Long guardar(Cupon cupon, Long idMascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigo_cupon", cupon.getCodigoCupon());
        paramSource.addValue("id_mascota", idMascota);
        paramSource.addValue("valor_descuento", cupon.getValorDescuento());
        paramSource.addValue("fecha_vigencia", cupon.getFechaVigencia());
        return this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }

    @Override
    public Cupon obtener(Long idCupon) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idCupon);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId, paramSource, mapeoCupon));
    }
}
