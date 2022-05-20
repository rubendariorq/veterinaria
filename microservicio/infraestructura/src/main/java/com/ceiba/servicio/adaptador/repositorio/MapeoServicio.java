package com.ceiba.servicio.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.servicio.entidad.Servicio;
import lombok.var;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoServicio implements RowMapper<Servicio>, MapperResult {
    @Override
    public Servicio mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var descripcion = resultSet.getString("descripcion");
        var valor = resultSet.getDouble("valor");
        return Servicio.reconstruir(id, descripcion, valor);
    }
}
