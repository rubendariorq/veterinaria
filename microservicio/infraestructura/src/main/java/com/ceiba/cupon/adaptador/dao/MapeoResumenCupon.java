package com.ceiba.cupon.adaptador.dao;

import com.ceiba.cupon.modelo.dto.CuponDTO;
import com.ceiba.infraestructura.jdbc.MapperResult;
import lombok.var;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoResumenCupon implements RowMapper<CuponDTO>, MapperResult {
    @Override
    public CuponDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var codigoCupon = resultSet.getString("codigo_cupon");
        var valorDescuento = resultSet.getDouble("valor_descuento");
        var fechaVigencia = resultSet.getDate("fecha_vigencia");
        var idMascota = resultSet.getLong("id_mascota");
        return new CuponDTO(id, codigoCupon, valorDescuento, fechaVigencia.toLocalDate(), idMascota);
    }
}
