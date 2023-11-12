package ru.mixaron.secuirty.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.mixaron.secuirty.Service.PersonService;
import ru.mixaron.secuirty.config.JWTAuthenticationFilter;
import ru.mixaron.secuirty.config.JwtService;
import ru.mixaron.secuirty.controllers.PersonController;
import ru.mixaron.secuirty.controllers.auth.AuthenticationService;
import ru.mixaron.secuirty.dto.ChangePasswordDTO;
import ru.mixaron.secuirty.dto.PersonDTO;
import ru.mixaron.secuirty.models.Person;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PersonControllerTests {


    @MockBean
    private  PersonService personService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    private AuthenticationService authenticationService;

    @MockBean
    PersonDTO personDTO;


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "USER")
    public void testChangePassword() throws Exception {

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO("user1", "oldPass", "newPass");

        when(personService.changePassword(changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword(), changePasswordDTO.getEmail())).thenReturn(true);
        mockMvc.perform(put("/api/changePerson/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changePasswordDTO)))
                .andExpect(status().isOk());

    }
    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "USER")
    public void testChangeEmail() throws Exception {

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO("user1", "oldPass", "newPass");

         when(personService.changeEmail(changePasswordDTO.getEmail(), "TestUserChange@mail.ru")).thenReturn(true);
        mockMvc.perform(put("/api/changePerson/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(changePasswordDTO)))
                .andExpect(status().is2xxSuccessful());

    }
}
