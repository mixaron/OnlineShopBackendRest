package ru.mixaron.secuirty.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mixaron.secuirty.Service.ProductService;
import ru.mixaron.secuirty.models.Product;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<String> watchProducts() {
        return ResponseEntity.ok(productService.watchProduct(productService.returnAllProducts()));
    }



}
