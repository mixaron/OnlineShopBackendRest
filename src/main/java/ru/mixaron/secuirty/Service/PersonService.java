package ru.mixaron.secuirty.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mixaron.secuirty.models.Order;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.repositories.OrderRepo;
import ru.mixaron.secuirty.repositories.PersonRepo;
import ru.mixaron.secuirty.util.errorAdvice.NotFoundEmailException;
import ru.mixaron.secuirty.util.errorAdvice.UnkownPassword;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PersonService {
    private final PasswordEncoder passwordEncoder;



    private final PersonRepo personRepo;

    private final OrderRepo orderRepo;


    @Transactional
    public void changePassword(String oldPassword, String newPassword, String email) {
        Person person = personRepo.findByEmail(email).orElseThrow(NotFoundEmailException::new);
        if (oldPassword != null && passwordEncoder.matches(oldPassword, person.getPassword()) && !Objects.equals(oldPassword, newPassword)) {
            person.setPassword(passwordEncoder.encode(newPassword));
            personRepo.save(person);
        }
        else throw new UnkownPassword();
    }

    // не работает changeEmail
    @Transactional
    public boolean changeEmail(String oldEmail, String newEmail) {
        Person person = personRepo.findByEmail(oldEmail).orElseThrow(NotFoundEmailException::new);
        if (!Objects.equals(oldEmail, newEmail)) {
            List<Order> orders = orderRepo.findByPerson(person).orElseThrow(NotFoundEmailException::new);
            for (Order order : orders) {
                order.getPerson().setEmail(newEmail);
                orderRepo.save(order);
            }
            person.setEmail(newEmail);
            personRepo.save(person);
            return true;
        }
        return false;
    }
}
