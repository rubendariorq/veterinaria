package com.ceiba.mascota.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.mascota.comando.ComandoSolicitudRegistrarMascota;
import com.ceiba.mascota.modelo.dto.MascotaDTO;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@WebMvcTest(ComandoControladorMascota.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorMascotaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private RepositorioMascota repositorioMascota;

    @Test
    void deberiaCrearMascotaCorrectamente() throws Exception {
        ComandoSolicitudRegistrarMascota comandoSolicitudRegistrarMascota = new ComandoRegistrarMascotaTestDataBuilder()
                .conComandoRegistarMascotaPorDefecto().build();

        MvcResult resultado = mocMvc.perform(post("/mascota")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarMascota)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        RespuestaMascota respuestaMascota = objectMapper.readValue(jsonResult, RespuestaMascota.class);
        Mascota mascota = repositorioMascota.obtener(respuestaMascota.getValor().getId());

        //Assertions.assertEquals(1l, mascota.getId());
        Assertions.assertEquals("Manotas", mascota.getNombre());
        Assertions.assertEquals(1l, mascota.getTipoMascota());
        Assertions.assertEquals("MASC010", mascota.getCodigoMascota());
    }
}
