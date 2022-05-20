package com.ceiba.tratamiento.adaptador.repositorio;

import com.ceiba.cupon.puerto.repositorio.RepositorioCupon;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioTratamientoMysql implements RepositorioTratamiento {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioTratamientoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "tratamiento", value = "crear")
    private static String sqlCrear;

    @Override
    public Tratamiento guardar(Tratamiento tratamiento) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigo_tratamiento", tratamiento.getCodigoTratamiento());
        paramSource.addValue("id_mascota", tratamiento.getIdMascota());
        paramSource.addValue("fecha_inicio", tratamiento.getFechaInicio());
        paramSource.addValue("fecha_fin", tratamiento.getFechaFin());
        paramSource.addValue("tipo_tratamiento", tratamiento.getTipoTratamiento());
        paramSource.addValue("id_servicio", tratamiento.getIdServicio());
        Long idTratamiento = this.customNamedParameterJdbcTemplate.crear(paramSource, sqlCrear);
        return Tratamiento.reconstruir(idTratamiento, tratamiento.getCodigoTratamiento(), tratamiento.getIdMascota(),
                tratamiento.getFechaInicio(), tratamiento.getFechaFin(), tratamiento.getTipoTratamiento(), tratamiento.getIdServicio());
    }
}
