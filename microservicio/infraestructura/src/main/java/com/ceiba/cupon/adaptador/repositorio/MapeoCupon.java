package com.ceiba.cupon.adaptador.repositorio;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.infraestructura.jdbc.MapperResult;
import lombok.var;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoCupon implements RowMapper<Cupon>, MapperResult {
    @Override
    public Cupon mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var codigoCupon = resultSet.getString("codigo_cupon");
        var valorDescuento = resultSet.getDouble("valor_descuento");
        var fechaVigencia = resultSet.getDate("fecha_vigencia");
        return Cupon.reconstruir(id, codigoCupon, valorDescuento, fechaVigencia.toLocalDate());
    }
}
