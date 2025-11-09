package br.com.mottu.challange.controller;

import br.com.mottu.challange.domain.dto.motorcycle.MotorcycleDTO;
import br.com.mottu.challange.domain.entity.Motorcycle;
import br.com.mottu.challange.domain.entity.StatusColor;
import br.com.mottu.challange.domain.repository.MotorcycleRepository;
import br.com.mottu.challange.domain.repository.UserRepository;
import br.com.mottu.challange.infra.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MotorcycleController.class)
public class MotorcycleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MotorcycleRepository motorcycleRepository;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void deveRegistrarMotorcycleComSucesso() throws Exception {
        MotorcycleDTO dto = new MotorcycleDTO(
                "ABC-1A23",
                "chassisABC",
                "engineABC",
                StatusColor.LIGHT_GREEN,
                1L,
                1L
        );

        Motorcycle motorcycleSalva = new Motorcycle(dto);
        motorcycleSalva.setId(99L);

        when(motorcycleRepository.save(any(Motorcycle.class))).thenReturn(motorcycleSalva);

        mockMvc.perform(post("/motorcycles")
                        .with(user("testuser"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", containsString("/motorcycles/99")))
                .andExpect(jsonPath("$.id", is(99)))
                .andExpect(jsonPath("$.license", is("ABC-1A23")))
                .andExpect(jsonPath("$.idModel", is(1)));
    }

    @Test
    void naoDeveRegistrarMotorcycleComDadosInvalidos() throws Exception {
        MotorcycleDTO dtoInvalido = new MotorcycleDTO(
                null,
                "chassis123",
                "engine123",
                StatusColor.RED,
                null,
                null
        );

        mockMvc.perform(post("/motorcycles")
                        .with(user("testuser"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest());
    }
}