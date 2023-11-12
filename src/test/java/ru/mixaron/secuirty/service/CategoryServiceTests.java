package ru.mixaron.secuirty.service;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;



import ru.mixaron.secuirty.Service.CategoryService;

import ru.mixaron.secuirty.dto.CategoryDTO;
import ru.mixaron.secuirty.models.Category;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class CategoryServiceTests {


    private final CategoryService categoryService = Mockito.mock(CategoryService.class);


    @Test
    public void testCreateCategory() {
        Category category = new Category("123");
        doNothing().when(categoryService).createCategory(category);
        categoryService.createCategory(category);
        verify(categoryService, times(1)).createCategory(category);
    }

    @Test
    public void testDeleteCategory() {
        CategoryDTO category = new CategoryDTO("123");
        doNothing().when(categoryService).deleteCategory(category);
        categoryService.deleteCategory(category);
        verify(categoryService, times(1)).deleteCategory(category);
    }

    @Test
    public void testReturnOne() {
        // Создаем тестовые данные
        CategoryDTO categoryDTO = new CategoryDTO("123");
        Category category = new Category("123");
        // Настройка заглушки для categoryRepo
        when(categoryService.returnOne(categoryDTO)).thenReturn(category);
        // Вызываем метод, который тестируем
        Category result = categoryService.returnOne(categoryDTO);

        // Проверяем, что результат соответствует ожиданиям
        assertEquals(result.getCategoryName(), category.getCategoryName());
    }
    @Test
    public void testReturnAll() {
        // Создаем тестовые данные

        List<Category> category = Arrays.asList(new Category("123"), new Category("234"));
        when(categoryService.returnAll()).thenReturn(category);
        List<Category> result = categoryService.returnAll();
        assertEquals("123", result.getFirst().getCategoryName());
        assertEquals("234", result.getLast().getCategoryName());
    }
}
