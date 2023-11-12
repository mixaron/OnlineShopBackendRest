package ru.mixaron.secuirty.Repositories;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mixaron.secuirty.dto.CategoryDTO;
import ru.mixaron.secuirty.models.Category;
import ru.mixaron.secuirty.repositories.CategoryRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryRepoTests {

    private final CategoryRepo categoryRepo = Mockito.mock(CategoryRepo.class);

    @Test
    public void testFindByCategoryName() {
        Category category = new Category("123");
        when(categoryRepo.findByCategoryName("123")).thenReturn(Optional.of(category));
        Optional<Category> result = categoryRepo.findByCategoryName("123");
        assertEquals(Optional.of(category), result);
    }
}
