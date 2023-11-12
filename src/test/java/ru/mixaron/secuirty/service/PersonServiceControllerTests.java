package ru.mixaron.secuirty.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mixaron.secuirty.Service.PersonService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class PersonServiceControllerTests {
    PersonService personService = Mockito.mock(PersonService.class);

    @Test
    public void testChangePassword() {
        String oldEmail = "Anton1@mail.ru";
        String newEmail = "Anton2@mail.ru";
        when(personService.changeEmail(oldEmail, newEmail)).thenReturn(true);
        boolean isTrue = personService.changeEmail(oldEmail, newEmail);
        assertTrue(isTrue);
    }
    @Test
    public void testChangeEmail() {
        String email = "Anton1@mail.ru";
        String oldPassword = "oldPass";
        String newPass = "newPass";
        when(personService.changePassword(oldPassword, newPass, email)).thenReturn(true);
        boolean isTrue = personService.changePassword(oldPassword, newPass, email);
        assertTrue(isTrue);
    }
}
