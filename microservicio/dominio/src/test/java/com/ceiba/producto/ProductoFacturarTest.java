package com.ceiba.producto;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.factura.ProductoFacturarTestDataBuilder;
import com.ceiba.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.producto.entidad.Producto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductoFacturarTest {

    @Test
    void calcularValorTotalExitosoConIva() {
        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
                .conProducto(new ProductoTestDataBuilder().conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(10000))
                        .conAplicaIva(true)
                        .reconstruir())
                .conCantidad(6)
                .build();

        BigDecimal totalConIva = productoFacturar.calcularTotalConIva();
        BigDecimal iva = productoFacturar.calcularIva();

        Assertions.assertEquals(BigDecimal.valueOf(71400).longValue(), totalConIva.longValue());
        Assertions.assertEquals(BigDecimal.valueOf(11400).longValue(), iva.longValue());
    }

    @Test
    void calcularValorTotalExitosoSinIva() {
        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
                .conProducto(new ProductoTestDataBuilder().conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(10000))
                        .conAplicaIva(false)
                        .reconstruir())
                .conCantidad(6)
                .build();

        BigDecimal totalConIva = productoFacturar.calcularTotalConIva();
        BigDecimal iva = productoFacturar.calcularIva();

        Assertions.assertEquals(BigDecimal.valueOf(60000).longValue(), totalConIva.longValue());
        Assertions.assertEquals(BigDecimal.valueOf(0).longValue(), iva.longValue());
    }

    @Test
    void deberiaCrearExitoso() {
        Producto producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();
        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
                .conProducto(producto)
                .conCantidad(6)
                .build();

        Assertions.assertEquals(producto, productoFacturar.getProducto());
        Assertions.assertEquals(6, productoFacturar.getCantidad());
    }

    @Test
    void deberiaReconstruirExitoso() {
        Producto producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();
        ProductoFacturar productoFacturar = new ProductoFacturarTestDataBuilder()
                .conProducto(producto)
                .conCantidad(6)
                .conId(5l)
                .reconstruir();

        Assertions.assertEquals(producto, productoFacturar.getProducto());
        Assertions.assertEquals(6, productoFacturar.getCantidad());
        Assertions.assertEquals(5l, productoFacturar.getId());
    }

    @Test
    void crearSinCantidaddeberiaFallar() {
        Producto producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();

        BasePrueba.assertThrows(()->new ProductoFacturarTestDataBuilder()
                .conProducto(producto)
                .build(), ExcepcionValorObligatorio.class, "Cantidad es requerida");
    }

    @Test
    void crearSinProductodeberiaFallar() {
        BasePrueba.assertThrows(()->new ProductoFacturarTestDataBuilder()
                .conCantidad(5)
                .build(), ExcepcionValorObligatorio.class, "Producto es requerido");
    }

    @Test
    void reconstruirSinCantidaddeberiaFallar() {
        Producto producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();

        BasePrueba.assertThrows(()->new ProductoFacturarTestDataBuilder()
                .conProducto(producto)
                .reconstruir(), ExcepcionValorObligatorio.class, "Cantidad es requerida");
    }

    @Test
    void reconstruirSinProductodeberiaFallar() {
        BasePrueba.assertThrows(()->new ProductoFacturarTestDataBuilder()
                .conCantidad(5)
                .reconstruir(), ExcepcionValorObligatorio.class, "Producto es requerido");
    }

}
