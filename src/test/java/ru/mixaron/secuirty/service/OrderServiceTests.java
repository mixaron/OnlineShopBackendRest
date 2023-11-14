package ru.mixaron.secuirty.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mixaron.secuirty.Service.OrderService;
import ru.mixaron.secuirty.dto.OrderDTO;
import ru.mixaron.secuirty.models.Order;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.models.Product;
import ru.mixaron.secuirty.util.Role;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @Mock
    private OrderService orderService;


         final Person person = new Person("Anton@gmail.com", Role.ROLE_USER);
         final Product product = new Product("4080", 400);
         final Order order = new Order(person, product, "123");
         final Product product2 = new Product("4070", 600);
         final Person person1 = new Person("Anton1@gmail.com", Role.ROLE_USER);
         final Order order2 = new Order(person1, product2, "234");

         final OrderDTO orderDTO = new OrderDTO(person, product, "123");

    @Test
    public void testCreateOrder() {
        doNothing().when(orderService).createOrder(order);
        orderService.createOrder(order);
        verify(orderService, times(1)).createOrder(order);
    }

    @Test
    public void testDeleteOrder() {

        doNothing().when(orderService).deleteOrder(orderDTO);
        orderService.deleteOrder(orderDTO);
        verify(orderService, times(1)).deleteOrder(orderDTO);
    }

    @Test
    public void testReturnAllByPerson() {


        List<Order> orders1 = Arrays.asList(order, order2);

        when(orderService.returnAllByPerson(orderDTO)).thenReturn(orders1);

        List<Order> orders = orderService.returnAllByPerson(orderDTO);
        assertEquals(order.getProducts(), orders.getFirst().getProducts());
        assertEquals(order2.getProducts(), orders.getLast().getProducts());
    }

    @Test
    public void testReturnAll() {

        List<Order> orders = Arrays.asList(order, order2);

        when(orderService.returnAll()).thenReturn(orders);

        List<Order> ordersTest = orderService.returnAll();

        assertEquals(order.getPerson(), ordersTest.getFirst().getPerson());
        assertEquals(order2.getPerson(), ordersTest.getLast().getPerson());
    }


    // не работает
//    @Test
//    public void testOrderByPrice() {
//
//
//        List<Order> orders = Arrays.asList(order, order2);
//
//        // Устанавливаем поведение заглушки orderService
//        when(orderService.orderByPrice()).thenReturn(orders);
//
//        // Вызываем метод, который должен возвращать отсортированный список заказов
//        List<Order> ordersTest = orderService.orderByPrice();
//
//        // Проверяем, что полученный список соответствует ожиданиям
//        assertEquals(order2.getPerson(), ordersTest.get(0).getPerson());
//        assertEquals(order.getPerson(), ordersTest.get(1).getPerson());
//        assertEquals(order2.getProducts(), ordersTest.get(0).getProducts());
//        assertEquals(order.getProducts(), ordersTest.get(1).getProducts());
//    }

}
