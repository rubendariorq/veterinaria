package com.ceiba.cupon.adaptador.repositorio;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.cupon.puerto.repositorio.RepositorioCupon;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCuponMysql implements RepositorioCupon {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioCuponMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "cupon", value = "crear")
    private static String sqlCrear;

    @Override
    public void guardar(List<Cupon> cupones, Long idMascota) {
        cupones.stream().forEach(cupon -> this.guardarCupon(cupon, idMascota));
    }

    private void guardarCupon(Cupon cupon, Long idMascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigo_cupon", cupon.getCodigoCupon());
        paramSource.addValue("id_mascota", idMascota);
        paramSource.addValue("valor_descuento", cupon.getValorDescuento());
        paramSource.addValue("fecha_vigencia", cupon.getFechaVigencia());
        this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
    }
}
