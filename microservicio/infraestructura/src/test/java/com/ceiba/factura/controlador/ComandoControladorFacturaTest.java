package com.ceiba.factura.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.factura.comando.ComandoSolicitudFacturar;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorFactura.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorFacturaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private RepositorioFactura repositorioFactura;


    @Test
    void crearFacturaExitosa() throws Exception {
        ComandoSolicitudFacturar comandoFacturarTestDataBuilder = new ComandoFacturarTestDataBuilder().crearPorDefecto().build();

        MvcResult resultado = mocMvc.perform(post("/factura")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoFacturarTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        RespuestaFacturar respuesta = objectMapper.readValue(jsonResult, RespuestaFacturar.class);

        Factura facturaGuardada = repositorioFactura.obtener(respuesta.getValor());

        Assertions.assertEquals("Cliente 1", facturaGuardada.getCliente().getNombre());
        Assertions.assertEquals(175000l, facturaGuardada.getValorTotal().longValue());
        Assertions.assertEquals(2, facturaGuardada.getProductos().size());

    }

    @Test
    void anularFacturaExitosa() throws Exception {

        mocMvc.perform(post("/factura/anular/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Factura facturaAnulada = repositorioFactura.obtener(1l);

        Assertions.assertTrue(facturaAnulada.esAnulada());
    }

}
