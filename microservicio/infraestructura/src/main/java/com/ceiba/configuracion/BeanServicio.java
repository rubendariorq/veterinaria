package com.ceiba.configuracion;

import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioAnular;
import com.ceiba.factura.servicio.ServicioFacturar;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.ServicioRegistrarMascota;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;
import com.ceiba.tratamiento.servicio.ServicioIniciarTratamiento;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {


    @Bean
    public ServicioFacturar servicioFacturar(RepositorioFactura repositorioFactura) {
        return new ServicioFacturar(repositorioFactura);
    }

    @Bean
    public ServicioAnular servicioAnular(RepositorioFactura repositorioFactura) {
        return new ServicioAnular(repositorioFactura);
    }
    @Bean
    public ServicioRegistrarMascota servicioRegistrarMascota(RepositorioMascota repositorioMascota) {
        return new ServicioRegistrarMascota(repositorioMascota);
    }

    @Bean
    public ServicioIniciarTratamiento servicioIniciarTratamiento(RepositorioTratamiento repositorioTratamiento) {
        return new ServicioIniciarTratamiento(repositorioTratamiento);
    }



}
