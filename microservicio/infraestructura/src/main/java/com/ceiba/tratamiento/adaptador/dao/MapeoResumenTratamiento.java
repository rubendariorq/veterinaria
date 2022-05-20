package com.ceiba.tratamiento.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mascota.modelo.dto.MascotaDTO;
import com.ceiba.tratamiento.modelo.dto.TratamientoDTO;
import lombok.var;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoResumenTratamiento implements RowMapper<TratamientoDTO>, MapperResult {


    @Override
    public TratamientoDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var codigoTratamiento = resultSet.getString("codigo_tratamiento");
        var fechaInicio = resultSet.getDate("fecha_inicio");
        var fechaFin = resultSet.getDate("fecha_fin");
        var tipoTratamiento = resultSet.getLong("tipo_tratamiento");
        var idMascota = resultSet.getLong("id_mascota");
        var idServicio = resultSet.getLong("id_servicio");
        var valor = resultSet.getDouble("valor");
        return new TratamientoDTO(id, codigoTratamiento, fechaInicio.toLocalDate(), fechaFin.toLocalDate(), tipoTratamiento, idMascota, idServicio, valor);
    }
}
