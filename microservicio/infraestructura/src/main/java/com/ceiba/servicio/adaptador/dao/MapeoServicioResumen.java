package com.ceiba.servicio.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.servicio.modelo.dto.ServicioDTO;
import lombok.var;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoServicioResumen implements RowMapper<ServicioDTO>, MapperResult  {
    @Override
    public ServicioDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var descripcion = resultSet.getString("descripcion");
        var valor = resultSet.getDouble("valor");
        return new ServicioDTO(id, descripcion, valor);
    }
}
