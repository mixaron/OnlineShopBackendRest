package ru.mixaron.secuirty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mixaron.secuirty.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

    Optional<Person> findByEmail(String email);

//    List<Person> findByProductsName(String name);


}
