package ru.mixaron.secuirty.Repositories;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mixaron.secuirty.models.Product;
import ru.mixaron.secuirty.repositories.ProductRepo;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductRepoTests {
    private final ProductRepo productRepo = Mockito.mock(ProductRepo.class);

    private final Product product = new Product(UUID.randomUUID(), "name", 400);
    @Test
    public void testFindByName() {
        when(productRepo.findByName("name")).thenReturn(Optional.of(product));
        Optional<Product> testReturn = productRepo.findByName("name");
        assertEquals(testReturn.get().getName(), product.getName());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(productRepo).deleteById(product.getId());
        productRepo.deleteById(product.getId());
        verify(productRepo, times(1)).deleteById(product.getId());
    }
}
