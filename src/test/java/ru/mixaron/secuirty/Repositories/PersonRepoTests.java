package ru.mixaron.secuirty.Repositories;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.repositories.PersonRepo;
import ru.mixaron.secuirty.util.Role;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PersonRepoTests {
    private final PersonRepo personRepo = Mockito.mock(PersonRepo.class);

    @Test
    public void testFindByEmail() {
        Person person = new Person("Anton2@mail.ru", Role.ROLE_USER);
        when(personRepo.findByEmail(person.getEmail())).thenReturn(Optional.of(person));
        Optional<Person> testReturnPerson = personRepo.findByEmail(person.getEmail());
        assertEquals(testReturnPerson.get().getEmail(), person.getEmail());
    }
}
