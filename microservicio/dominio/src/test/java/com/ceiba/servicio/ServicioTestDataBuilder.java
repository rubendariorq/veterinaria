package com.ceiba.servicio;

import com.ceiba.servicio.entidad.Servicio;

public class ServicioTestDataBuilder {

    private Long id;
    private String descripcion;
    private Double valor;

    public ServicioTestDataBuilder conServicioPorDefecto() {
        this.id = 1l;
        this.descripcion = "Servicio Tipo Medico";
        this.valor = 49999D;
        return this;
    }

    public ServicioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ServicioTestDataBuilder conDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public ServicioTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public Servicio reconstruir() {
        return Servicio.reconstruir(id, descripcion, valor);
    }
}