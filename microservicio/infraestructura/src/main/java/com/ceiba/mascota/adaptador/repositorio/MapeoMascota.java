package com.ceiba.mascota.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mascota.modelo.entidad.Mascota;
import lombok.var;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoMascota implements RowMapper<Mascota>, MapperResult {

    @Override
    public Mascota mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var codigoMascota = resultSet.getString("codigo_mascota");
        var nombre = resultSet.getString("nombre");
        var tipoMascota = resultSet.getLong("tipo_mascota");
        return Mascota.reconstruir(id, codigoMascota, nombre, tipoMascota);
    }
}
