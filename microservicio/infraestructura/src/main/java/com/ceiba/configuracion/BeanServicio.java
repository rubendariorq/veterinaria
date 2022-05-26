package com.ceiba.configuracion;

import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.ServicioRegistrarMascota;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;
import com.ceiba.tratamiento.servicio.ServicioTratamiento;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioRegistrarMascota servicioRegistrarMascota(RepositorioMascota repositorioMascota) {
        return new ServicioRegistrarMascota(repositorioMascota);
    }

    @Bean
    public ServicioTratamiento servicioIniciarTratamiento(RepositorioTratamiento repositorioTratamiento) {
        return new ServicioTratamiento(repositorioTratamiento);
    }



}
