package com.ceiba.servicio.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.servicio.comando.ComandoSolicitudRegistrarServicio;
import com.ceiba.servicio.modelo.entidad.Servicio;
import com.ceiba.servicio.puerto.repositorio.RepositorioServicio;
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
@WebMvcTest(ComandoControladorServicio.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorServicioTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private RepositorioServicio repositorioServicio;

    @Test
    void deberiaCrearServicioCorrectamente() throws Exception {
        ComandoSolicitudRegistrarServicio comandoSolicitudRegistrarServicio = new ComandoRegistrarServicioTestDataBuilder()
                .conComandoRegistarServicioPorDefecto().build();

        MvcResult resultado = mocMvc.perform(post("/servicio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarServicio)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        RespuestaServicio respuestaServicio = objectMapper.readValue(jsonResult, RespuestaServicio.class);
        Servicio servicio = repositorioServicio.obtener(respuestaServicio.getValor().getId());

        Assertions.assertEquals("Tratamiento medico", servicio.getDescripcion());
        Assertions.assertEquals(50000D, servicio.getValor());
    }
}
