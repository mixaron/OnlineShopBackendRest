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
import ru.mixaron.secuirty.Service.CategoryService;
import ru.mixaron.secuirty.Service.OrderService;
import ru.mixaron.secuirty.Service.ProductService;
import ru.mixaron.secuirty.config.JWTAuthenticationFilter;
import ru.mixaron.secuirty.config.JwtService;
import ru.mixaron.secuirty.controllers.WatchController;
import ru.mixaron.secuirty.dto.OrderDTO;
import ru.mixaron.secuirty.dto.ProductDTO;
import ru.mixaron.secuirty.models.Category;
import ru.mixaron.secuirty.models.Order;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.models.Product;
import ru.mixaron.secuirty.util.Role;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WatchController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WatchControllerTests {

    @MockBean
    private  ProductService productService;

    @MockBean
    private  OrderService orderService;

    @MockBean
    private  CategoryService categoryService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper  objectMapper;

    @MockBean
    private JWTAuthenticationFilter jwtAuthenticationFilter;


    @Autowired
    private  MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "USER")
    public void testWatchProducts() throws Exception {
        Category order = new Category("name");

        List<Product> expectedProducts = Arrays.asList(new Product("name", "123", 400,order), new Product("name1", "1234", 400,order));
        when(productService.returnAllProducts()).thenReturn(expectedProducts);
        mockMvc.perform(get("/api/returnSomething/products"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect( jsonPath("$", hasSize(2)))
                .andExpect( jsonPath("$[0].name", is("name")))
                .andExpect( jsonPath("$[1].name", is("name1")))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "USER")
    public void testOneProduct() throws Exception {
        Category order = new Category("name");
        // Подготовка тестовых данных
        ProductDTO productDTO = new ProductDTO("name", "123", 400,order);

        // Мокаем ProductService для возвращения ожидаемого продукта
        Product expectedProduct = new Product("name", "123", 400,order);
        when(productService.oneProduct("name")).thenReturn(expectedProduct);

        // Выполняем POST-запрос и проверяем возвращаемые данные
        mockMvc.perform(post("/api/returnSomething/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO))) // Проверяем тип контента
                .andExpect(status().is2xxSuccessful()); // Проверяем возвращаемый продукт
    }
    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "USER")
    public void testWatchOrders() throws Exception {
        Category order = new Category("name");
        Person person = new Person("test@mail.ru", Role.ROLE_USER);
        Person person2 = new Person("test1@mail.ru", Role.ROLE_USER);
        List<Order> orders = Arrays.asList(new Order(person, new Product("name", "123", 400, order), "Оплачен"),
                new Order(person2, new Product("name", "123", 400, order), "Оплачен"));
        when(orderService.returnAll()).thenReturn(orders);
        mockMvc.perform(get("/api/returnSomething/orders"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].person.email", is("test@mail.ru")))
                .andExpect(jsonPath("$[1].person.email", is("test1@mail.ru")))
                .andDo(print());
    }
    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "USER")
    public void testOneOrder() throws Exception {
        Category order = new Category("name");
        Person person = new Person("test@mail.ru", Role.ROLE_USER);
        OrderDTO order1 = new OrderDTO(person, new Product("name", "123", 400, order), "Оплачен");
        when(orderService.returnAllByPerson(order1)).thenReturn(Collections.singletonList(order1));
        mockMvc.perform(post("/api/returnSomething/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order1)));
    }

    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "USER")
    public void testWatchCategories() throws Exception {
        Category order = new Category("132");
        Category order1 = new Category("234");
        List<Category> categories = Arrays.asList(order, order1);
        when(categoryService.returnAll()).thenReturn(categories);
        mockMvc.perform(get("/api/returnSomething/categories"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].categoryName", is(order.getCategoryName())))
                .andExpect(jsonPath("$[1].categoryName", is(order1.getCategoryName())))
                .andDo(print());
    }
}
