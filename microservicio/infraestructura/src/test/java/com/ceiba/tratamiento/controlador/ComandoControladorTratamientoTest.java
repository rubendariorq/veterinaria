package com.ceiba.tratamiento.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.mascota.comando.ComandoSolicitudRegistrarMascota;
import com.ceiba.mascota.controlador.ComandoRegistrarMascotaTestDataBuilder;
import com.ceiba.mascota.controlador.RespuestaMascota;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.tratamiento.comando.ComandoSolicitudIniciarTratamiento;
import com.ceiba.tratamiento.modelo.entidad.Tratamiento;
import com.ceiba.tratamiento.puerto.repositorio.RepositorioTratamiento;
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
@WebMvcTest(ComandoControladorTratamiento.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorTratamientoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private RepositorioTratamiento repositorioTratamiento;

    /*@Test
    void deberiaCrearTratamientoCorrectamente() throws Exception {
        ComandoSolicitudRegistrarMascota comandoSolicitudRegistrarMascota = new ComandoRegistrarMascotaTestDataBuilder()
                .conComandoRegistarMascotaPorDefecto().build();

        mocMvc.perform(post("/mascota")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarMascota)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        ComandoSolicitudIniciarTratamiento comandoSolicitudIniciarTratamiento = new ComandoRegistrarTratamientoTestDataBuilder()
                .conComandoRegistrarTratamientoPorDefecto().build();

        MvcResult resultado = mocMvc.perform(post("/tratamiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudIniciarTratamiento)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        RespuestaTratamiento respuestaTratamiento = objectMapper.readValue(jsonResult, RespuestaTratamiento.class);
        Tratamiento tratamiento = repositorioTratamiento.obtener(respuestaTratamiento.getValor().getId());

        Assertions.assertEquals("TRAT001", tratamiento.getCodigoTratamiento());
        Assertions.assertEquals(2l, tratamiento.getTipoTratamiento());
        Assertions.assertEquals("2022-05-30", tratamiento.getFechaInicio().toString());
        Assertions.assertEquals("2022-06-06", tratamiento.getFechaFin().toString());
    }*/
}
