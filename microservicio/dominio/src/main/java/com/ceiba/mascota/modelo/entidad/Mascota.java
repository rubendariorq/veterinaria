package com.ceiba.mascota.modelo.entidad;

import com.ceiba.cupon.entidad.Cupon;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.util.ArrayList;
import java.util.List;

public class Mascota {

    private static final Long TIPO_MASCOTA_GATO = 1L;
    private static final Long TIPO_MASCOTA_PERRO = 2L;

    private Long id;
    private String codigoMascota;
    private String nombre;
    private Long tipoMascota;
    private List<Cupon> cupones;

    private Mascota (String codigoMascota, String nombre, Long tipoMascota) {
        this.codigoMascota = codigoMascota;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
    }

    public Mascota(Long id, String codigoMascota, String nombre, Long tipoMascota, List<Cupon> cupones) {
        this.id = id;
        this.codigoMascota = codigoMascota;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.cupones = cupones;
    }

    public static Mascota crear(String codigoMascota, String nombre, Long tipoMascota) {
        ValidadorArgumento.validarObligatorio(codigoMascota, "El código de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(tipoMascota, "El tipo de mascota es requerido");

        if (!TIPO_MASCOTA_GATO.equals(tipoMascota) && !TIPO_MASCOTA_PERRO.equals(tipoMascota)) {
            throw new ExcepcionValorInvalido("Tipo de mascota no permitido en la veterinaria");
        }
        return new Mascota(codigoMascota, nombre, tipoMascota);
    }

    public static Mascota reconstruir(Long id, String codigoMascota, String nombre, Long tipoMascota, List<Cupon> cupones) {
        ValidadorArgumento.validarObligatorio(id, "El id es requerido");
        ValidadorArgumento.validarObligatorio(codigoMascota, "El código de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre de la mascota es requerido");
        ValidadorArgumento.validarObligatorio(tipoMascota, "El tipo de mascota es requerido");

        if (!TIPO_MASCOTA_GATO.equals(tipoMascota) && !TIPO_MASCOTA_PERRO.equals(tipoMascota)) {
            throw new ExcepcionValorInvalido("Tipo de mascota no permitido en la veterinaria");
        }
        return new Mascota(id, codigoMascota, nombre, tipoMascota, cupones);
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

    public List<Cupon> getCupones() {
        return cupones;
    }
}
