package ru.mixaron.secuirty.service;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.mixaron.secuirty.Service.ProductService;
import ru.mixaron.secuirty.models.Category;
import ru.mixaron.secuirty.models.Product;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTests {

    ProductService productService = Mockito.mock(ProductService.class);

    private final Category category = new Category("123");

    private final Product product = new Product("TestName", "TestDescription", 400, category);

    @Test
    public void testCreateProduct() {
        doNothing().when(productService).createProduct(product);
        productService.createProduct(product);
        verify(productService, times(1)).createProduct(product);
    }

    @Test
    public void testDeleteCategory() {
        doNothing().when(productService).deleteProduct(product);
        productService.deleteProduct(product);
        verify(productService, times(1)).deleteProduct(product);
    }

    @Test
    public void testReturnOne() {
        when(productService.oneProduct(product.getName())).thenReturn(product);
        Product product1 = productService.oneProduct(product.getName());
        assertEquals(product1.getName(), product.getName());
        assertEquals(product1.getDescription(), product.getDescription());
        assertEquals(product1.getPrice(), product.getPrice());
    }

    @Test
    public void testReturnAllProducts() {
        List<Product> products = Arrays.asList(product, new Product("TestName2", "TestDescription2", 4001, category));
        when(productService.returnAllProducts()).thenReturn(products);
        List<Product> testReturn = productService.returnAllProducts();
        assertEquals(testReturn.getFirst().getPrice(), products.getFirst().getPrice());
        assertEquals(testReturn.getLast().getPrice(), products.getLast().getPrice());
        assertEquals(testReturn.getFirst().getName(), products.getFirst().getName());
    }
}
