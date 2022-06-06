package com.ceiba.cupon.adaptador.dao;

import com.ceiba.cupon.modelo.dto.CuponDTO;
import com.ceiba.cupon.puerto.dao.DaoCupon;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoCuponMysql implements DaoCupon {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoResumenCupon mapeoResumenCupon;

    @SqlStatement(namespace = "cupon", value = "listarporidmascota")
    private static String sqlListarPorIdMascota;

    public DaoCuponMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoResumenCupon mapeoResumenCupon) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoResumenCupon = mapeoResumenCupon;
    }

    @Override
    public List<CuponDTO> listarPorIdMascota(Long idMascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", idMascota);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarPorIdMascota, paramSource, mapeoResumenCupon);
    }
}
