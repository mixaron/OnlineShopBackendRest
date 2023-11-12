package ru.mixaron.secuirty.Repositories;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mixaron.secuirty.models.Order;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.models.Product;
import ru.mixaron.secuirty.repositories.OrderRepo;
import ru.mixaron.secuirty.util.Role;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderRepoTests {
    private final OrderRepo orderRepo = Mockito.mock(OrderRepo.class);

    private final Person person = new Person("Anton@gmail.com", Role.ROLE_USER);
    private final Product product = new Product("4080", 300);
    private final Order order = new Order(UUID.randomUUID(), person, product, "123");
    private final Person person2 = new Person("Anton1@gmail.com", Role.ROLE_USER);
    private final Product product2 = new Product("4070", 400);

    private final List<Order> orders = Arrays.asList(order, new Order(person2, product2, "1234"));

    private final List<Order> orders2 = Arrays.asList(order, order);

    @Test
    public void testFindAllByOrderByDateOfCreation() {
        when(orderRepo.findAllByOrderByPrice()).thenReturn(orders);
        List<Order> testReturn = orderRepo.findAllByOrderByPrice();
        assertEquals(300, testReturn.get(0).getProducts().getPrice()); // Проверяем цену первого элемента
        assertEquals(400, testReturn.get(1).getProducts().getPrice());
    }

    @Test
    public void testFindByPerson() {
        when(orderRepo.findByPerson(person)).thenReturn(Optional.of(orders2));
        Optional<List<Order>> returnOrders = orderRepo.findByPerson(person);
        assertEquals(returnOrders.get().getLast().getPerson(), person);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(orderRepo).deleteById(order.getId());
        orderRepo.deleteById(order.getId());
        verify(orderRepo, times(1)).deleteById(order.getId());
    }

    @Test
    public void testFindByPersonAndProducts() {
        when(orderRepo.findByPersonAndProducts(person, product)).thenReturn(Optional.of(order));
        Optional<Order> order1 = orderRepo.findByPersonAndProducts(person, product);
        assertEquals(order1.get().getPrice(), order.getPrice());
        assertEquals(order1.get().getProducts(), order.getProducts());
        assertEquals(order1.get().getPerson(), order.getPerson());
    }
}
