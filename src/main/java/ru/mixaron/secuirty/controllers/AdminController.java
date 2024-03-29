package ru.mixaron.secuirty.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mixaron.secuirty.Service.CategoryService;
import ru.mixaron.secuirty.Service.OrderService;
import ru.mixaron.secuirty.Service.ProductService;
import ru.mixaron.secuirty.dto.CategoryDTO;
import ru.mixaron.secuirty.dto.OrderDTO;
import ru.mixaron.secuirty.dto.ProductDTO;
import ru.mixaron.secuirty.models.Category;
import ru.mixaron.secuirty.models.Order;
import ru.mixaron.secuirty.models.Product;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {

    private final CategoryService categoryService;

    private final OrderService orderService;

    private final ProductService productService;

    private final ModelMapper modelMapper;

    @PostMapping("/createCategory")
    private ResponseEntity<HttpStatus> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = convertToCategory(categoryDTO);
        categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCategory")
    private ResponseEntity<HttpStatus> deleteCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.deleteCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpStatus.NO_CONTENT);
    }

    private Category convertToCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<HttpStatus> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = convertToOrder(orderDTO);
        orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteOrder")
    private ResponseEntity<HttpStatus> deleteOrder(@RequestBody OrderDTO orderDTO) {
        orderService.deleteOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpStatus.NO_CONTENT);
    }
    private Order convertToOrder(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }


    @PostMapping("/createProduct")
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = convertToProduct(productDTO);
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteProduct")
    private ResponseEntity<HttpStatus> deleteProduct(@RequestBody ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productService.deleteProduct(product);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpStatus.NO_CONTENT);
    }
    private Product convertToProduct(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
