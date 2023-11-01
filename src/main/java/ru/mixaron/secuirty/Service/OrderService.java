package ru.mixaron.secuirty.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mixaron.secuirty.dto.OrderDTO;
import ru.mixaron.secuirty.models.Order;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.models.Product;
import ru.mixaron.secuirty.repositories.OrderRepo;
import ru.mixaron.secuirty.repositories.PersonRepo;
import ru.mixaron.secuirty.repositories.ProductRepo;
import ru.mixaron.secuirty.util.errorAdvice.NotFoundEmailException;
import ru.mixaron.secuirty.util.errorAdvice.NotFoundOrderById;
import ru.mixaron.secuirty.util.errorAdvice.NotFoundProductException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;

    private final PersonRepo personRepo;

    private final ProductRepo productRepo;

    @Transactional
    public void CreateOrder(Order order) {
        order.setDateOfCreation(new Date());
        order.setStatus("Оплачен");
        order.setPerson(personRepo.findByEmail(order.getPerson().getEmail()).orElseThrow(NotFoundEmailException::new));
        Product product  = productRepo.findByName(order.getProducts().getName()).orElseThrow(NotFoundProductException::new);
        order.setProducts(product);
        order.setPrice(product.getPrice());
        orderRepo.save(order);
    }

    public List<Order> orderByDate() {
        return orderRepo.findAllByOrderByDateOfCreation();
    }

    public List<Order> orderByPerson() {
        return orderRepo.findAllByOrderByPerson();
    }

    public List<Order> orderByStatus() {
        return orderRepo.findAllByOrderByStatus();
    }

    public List<Order> orderByPrice() {
        return orderRepo.findAllByOrderByPrice();
    }

    public Order watchOrder(UUID id) {
        return orderRepo.findById(id).orElseThrow(NotFoundOrderById::new);
    }

    @Transactional
    public void deleteOrder(OrderDTO order) {
        Order order1 = orderRepo.findByPersonAndProducts(order.getPerson(), order.getProducts()).orElseThrow(NotFoundOrderById::new);
        orderRepo.deleteById(order1.getId());
    }
}
