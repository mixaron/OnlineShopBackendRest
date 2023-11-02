package ru.mixaron.secuirty.controllers;

import lombok.AllArgsConstructor;
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

import java.util.List;

@RestController
@RequestMapping("/api/returnSomething")
@AllArgsConstructor
public class WatchController {
    private final ProductService productService;

    private final OrderService orderService;

    private final CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> watchProducts() {
        return new ResponseEntity<>(productService.returnAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> oneProduct(@RequestBody ProductDTO product) {
        return ResponseEntity.ok(productService.oneProduct(product.getName()));
    }
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> watchOrders() {
        return new ResponseEntity<>(orderService.returnAll(), HttpStatus.OK);
    }
    @PostMapping("/order")
    public ResponseEntity<List<Order>> oneProduct(@RequestBody OrderDTO order) {
        return ResponseEntity.ok(orderService.returnAllByPerson(order));
    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> watchCategories() {
        return ResponseEntity.ok(categoryService.returnAll());
    }
    @PostMapping("/category")
    public ResponseEntity<Category> oneProduct(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.returnOne(categoryDTO));
    }
}
