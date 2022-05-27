package com.ceiba.mascota.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.mascota.comando.ComandoSolicitudRegistrarMascota;
import com.ceiba.mascota.modelo.dto.MascotaDTO;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
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

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorMascota.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorMascotaTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DaoMascota daoMascota;

    @Test
    void deberiaListarMascotasCorrectamente() throws Exception {

        ComandoSolicitudRegistrarMascota comandoSolicitudRegistrarMascota = new ComandoRegistrarMascotaTestDataBuilder()
                .conComandoRegistarMascotaPorDefecto().build();

        mockMvc.perform(post("/mascota")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarMascota)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        mockMvc.perform(post("/mascota")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarMascota)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        MvcResult resultado = mockMvc.perform(get("/mascota")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        RespuestaMascotas respuestaMascotas = objectMapper.readValue(jsonResult, RespuestaMascotas.class);

        Assertions.assertEquals(1l, respuestaMascotas.getValor().get(0).getId());
        Assertions.assertEquals("MASC010", respuestaMascotas.getValor().get(0).getCodigoMascota());
        Assertions.assertEquals("Manotas", respuestaMascotas.getValor().get(0).getNombre());
        Assertions.assertEquals(1l, respuestaMascotas.getValor().get(0).getTipoMascota());
    }
}
