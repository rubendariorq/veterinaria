package com.ceiba.mascota.modelo.dto;

public class MascotaDTO {
    private Long id;
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;

    public MascotaDTO() {
    }

    public MascotaDTO(Long id, String codigoMascota, String nombre, Long tipoMascota) {
        this.id = id;
        this.codigoMascota = codigoMascota;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoMascota() {
        return codigoMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getTipoMascota() {
        return tipoMascota;
    }
}
