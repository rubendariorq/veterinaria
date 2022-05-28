package com.ceiba.tratamiento.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.mascota.comando.ComandoSolicitudRegistrarMascota;
import com.ceiba.mascota.controlador.ComandoRegistrarMascotaTestDataBuilder;
import com.ceiba.mascota.controlador.ConsultaControladorMascota;
import com.ceiba.servicio.comando.ComandoSolicitudRegistrarServicio;
import com.ceiba.servicio.controlador.ComandoRegistrarServicioTestDataBuilder;
import com.ceiba.tratamiento.comando.ComandoSolicitudIniciarTratamiento;
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
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorTratamiento.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorTratamientoTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deberiaListarTratamientosCorrectamente() throws Exception {

        ComandoSolicitudRegistrarMascota comandoSolicitudRegistrarMascota = new ComandoRegistrarMascotaTestDataBuilder()
                .conComandoRegistarMascotaPorDefecto().build();

        mockMvc.perform(post("/mascota")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarMascota)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        ComandoSolicitudRegistrarServicio comandoSolicitudRegistrarServicio = new ComandoRegistrarServicioTestDataBuilder()
                .conComandoRegistarServicioPorDefecto().build();

        mockMvc.perform(post("/servicio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarServicio)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        ComandoSolicitudIniciarTratamiento comandoSolicitudIniciarTratamiento = new ComandoRegistrarTratamientoTestDataBuilder()
                .conComandoRegistrarTratamientoPorDefecto().build();

        mockMvc.perform(post("/tratamiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudIniciarTratamiento)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        mockMvc.perform(get("/tratamiento")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].codigoTratamiento", is("TRAT001")))
                .andExpect(jsonPath("$[0].fechaInicio", is("2022-05-30")))
                .andExpect(jsonPath("$[0].fechaFin", is("2022-06-06")));
    }

    @Test
    void deberiaObtenerTratamientosPorIdCorrectamente() throws Exception {

        ComandoSolicitudRegistrarMascota comandoSolicitudRegistrarMascota = new ComandoRegistrarMascotaTestDataBuilder()
                .conComandoRegistarMascotaPorDefecto().build();

        mockMvc.perform(post("/mascota")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarMascota)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        ComandoSolicitudRegistrarServicio comandoSolicitudRegistrarServicio = new ComandoRegistrarServicioTestDataBuilder()
                .conComandoRegistarServicioPorDefecto().build();

        mockMvc.perform(post("/servicio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudRegistrarServicio)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        ComandoSolicitudIniciarTratamiento comandoSolicitudIniciarTratamiento = new ComandoRegistrarTratamientoTestDataBuilder()
                .conComandoRegistrarTratamientoPorDefecto().build();

        mockMvc.perform(post("/tratamiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudIniciarTratamiento)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        mockMvc.perform(get("/tratamiento/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("codigoTratamiento", is("TRAT001")))
                .andExpect(jsonPath("fechaInicio", is("2022-05-30")))
                .andExpect(jsonPath("fechaFin", is("2022-06-06")));
    }
}
