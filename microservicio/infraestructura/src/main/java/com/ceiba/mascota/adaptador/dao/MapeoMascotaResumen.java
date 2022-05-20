package com.ceiba.mascota.adaptador.dao;

import com.ceiba.factura.modelo.dto.ResumenFacturaDTO;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mascota.modelo.dto.MascotaDTO;
import lombok.var;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class MapeoMascotaResumen implements RowMapper<MascotaDTO>, MapperResult {
    @Override
    public MascotaDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var codigoMascota = resultSet.getString("codigo_mascota");
        var nombre = resultSet.getString("nombre");
        var tipoMascota = resultSet.getLong("tipo_mascota");
        return new MascotaDTO(id, codigoMascota, nombre, tipoMascota);
    }
}
