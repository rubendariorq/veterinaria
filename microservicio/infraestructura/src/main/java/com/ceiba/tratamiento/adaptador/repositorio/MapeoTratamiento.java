package com.ceiba.tratamiento.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.servicio.puerto.repositorio.RepositorioServicio;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import lombok.var;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoTratamiento implements RowMapper<Tratamiento>, MapperResult {

    private final RepositorioMascota repositorioMascota;
    private final RepositorioServicio repositorioServicio;

    public MapeoTratamiento(RepositorioMascota repositorioMascota, RepositorioServicio repositorioServicio) {
        this.repositorioMascota = repositorioMascota;
        this.repositorioServicio = repositorioServicio;
    }

    @Override
    public Tratamiento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var codigoTratamiento = resultSet.getString("codigo_tratamiento");
        var fechaInicio = resultSet.getDate("fecha_inicio");
        var fechaFin = resultSet.getDate("fecha_fin");
        var tipoTratamiento = resultSet.getLong("tipo_tratamiento");
        var idMascota = resultSet.getLong("id_mascota");
        var idServicio = resultSet.getLong("id_servicio");
        var valor = resultSet.getDouble("valor");
        return Tratamiento.reconstruir(id, repositorioMascota.obtener(idMascota), repositorioServicio.obtener(idServicio),
                codigoTratamiento, fechaInicio.toLocalDate(), fechaFin.toLocalDate(), tipoTratamiento, valor);
    }
}
