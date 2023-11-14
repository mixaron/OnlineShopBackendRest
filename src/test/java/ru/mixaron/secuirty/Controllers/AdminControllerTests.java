package ru.mixaron.secuirty.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.mixaron.secuirty.Service.CategoryService;
import ru.mixaron.secuirty.Service.OrderService;
import ru.mixaron.secuirty.Service.ProductService;
import ru.mixaron.secuirty.config.JWTAuthenticationFilter;
import ru.mixaron.secuirty.config.JwtService;
import ru.mixaron.secuirty.config.TestSecurityConfiguration;
import ru.mixaron.secuirty.controllers.AdminController;
import ru.mixaron.secuirty.dto.CategoryDTO;
import ru.mixaron.secuirty.dto.OrderDTO;
import ru.mixaron.secuirty.models.Category;
import ru.mixaron.secuirty.models.Order;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.models.Product;
import ru.mixaron.secuirty.util.Role;

import java.security.Principal;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AdminController.class)
@WithMockUser(username = "user1", password = "pass", roles = "ADMIN")
@AutoConfigureMockMvc(addFilters = false)
public class AdminControllerTests {

    // mockBean дожны быть все как в котнроллере!
    @MockBean
    private CategoryService categoryService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JWTAuthenticationFilter jwtAuthenticationFilter;




    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    //    @Test
//    public void shouldCreateCategory() throws Exception {
//        CategoryDTO categoryDTO = new CategoryDTO("TestControo");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/admin/createProduct")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(categoryDTO)))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        Category category = new Category();
//        category.setCategoryName(categoryDTO.getCategoryName());
//
//        Mockito.when(modelMapper.map(categoryDTO, Category.class)).thenReturn(category);
//
//        Category category1 = categoryService.returnOne(categoryDTO);
//        Assertions.assertNull(category1);
//
//        categoryService.createProduct(category);
//
//        category1 = categoryService.returnOne(categoryDTO);
//        Assertions.assertNotNull(category1);
//    }
    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "ADMIN")
    void shouldCreateTutorial() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO("Spring Boot @WebMvcTest");

        mockMvc.perform(post("/api/v1/admin/createCategory").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }





    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "ADMIN")
    public void testDeleteCategory() throws Exception {
        // Создаем объект CategoryDTO для передачи в запрос
        CategoryDTO categoryDTO = new CategoryDTO("monitors");
        categoryService.deleteCategory(categoryDTO);
        // Выполняем запрос DELETE с передачей объекта CategoryDTO в теле запроса
        mockMvc.perform(delete("/api/v1/admin/deleteCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isNoContent());

        // Проверяем, что метод deleteCategory у categoryService был вызван с переданным объектом CategoryDTO
        Mockito.verify(categoryService).deleteCategory(Mockito.eq(categoryDTO));
    }

    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "ADMIN")
    public void testCreateOrder() throws Exception {
        Person person = new Person("Anton@gmail.com", Role.ROLE_USER);
        Product product = new Product("4080", 400);
        Order order = new Order(person, product, "123");

        mockMvc.perform(post("/api/v1/admin/createOrder").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "ADMIN")
    public void testDeleteOrder() throws Exception {
        Person person = new Person("Anton@gmail.com", Role.ROLE_USER);
        Product product = new Product("4080", 400);
        Order order = new Order(person, product, "123");


        mockMvc.perform(delete("/api/v1/admin/deleteOrder").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "ADMIN")
    public void testCreateProduct() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO("Spring Boot @WebMvcTest");
        Product product = new Product("3080", 200);
        product.setCategory(modelMapper.map(categoryDTO, Category.class));
        product.setDescription("123");
        mockMvc.perform(post("/api/v1/admin/createProduct").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    @WithMockUser(username = "user1", password = "pass", roles = "ADMIN")
    public void testDeleteProduct() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO("Spring Boot @WebMvcTest");
        Product product = new Product("3080", 200);
        product.setCategory(modelMapper.map(categoryDTO, Category.class));
        product.setDescription("123");
        mockMvc.perform(delete("/api/v1/admin/deleteProduct").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
