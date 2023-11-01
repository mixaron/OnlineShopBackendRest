package ru.mixaron.secuirty.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mixaron.secuirty.dto.CategoryDTO;
import ru.mixaron.secuirty.models.Category;
import ru.mixaron.secuirty.repositories.CategoryRepo;
import ru.mixaron.secuirty.util.errorAdvice.CreateCategoryException;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Transactional
    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    @Transactional
    public void deleteCategory(CategoryDTO category) {
        Category category1 = categoryRepo.findByCategoryName(category.getCategoryName()).orElseThrow(CreateCategoryException::new);
        categoryRepo.deleteById(category1.getId());
    }
}
