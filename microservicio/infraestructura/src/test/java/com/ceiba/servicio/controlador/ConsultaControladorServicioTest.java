package com.ceiba.servicio.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.servicio.comando.ComandoSolicitudRegistrarServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorServicio.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorServicioTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deberiaListarServiciosCorrectamente() throws Exception {

        ComandoSolicitudRegistrarServicio comandoSolicitudRegistrarServicio = new ComandoRegistrarServicioTestDataBuilder()
                .conComandoRegistarServicioPorDefecto().build();

        mockMvc.perform(post("/servicio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarServicio)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        mockMvc.perform(get("/servicio")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].descripcion", is("Tratamiento medico")))
                .andExpect(jsonPath("$[0].valor", is(50000D)));
    }
}
