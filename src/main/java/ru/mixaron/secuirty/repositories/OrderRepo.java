package ru.mixaron.secuirty.repositories;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mixaron.secuirty.models.Order;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.models.Product;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findAllByOrderByDateOfCreation();

    List<Order> findAllByOrderByStatus();

    List<Order> findAllByOrderByPerson();

    List<Order> findAllByOrderByPrice();

    Optional<Order> findById(UUID id);

    Optional<List<Order>> findByPerson(Person person);

    void deleteById(UUID id);

    Optional<Order> findByPersonAndProducts(Person person, Product product);


}
