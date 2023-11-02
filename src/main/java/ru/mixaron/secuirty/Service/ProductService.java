package ru.mixaron.secuirty.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mixaron.secuirty.dto.ProductDTO;
import ru.mixaron.secuirty.models.Category;
import ru.mixaron.secuirty.models.Product;
import ru.mixaron.secuirty.repositories.CategoryRepo;
import ru.mixaron.secuirty.repositories.ProductRepo;
import ru.mixaron.secuirty.util.errorAdvice.CreateCategoryException;
import ru.mixaron.secuirty.util.errorAdvice.NotFoundEmailException;
import ru.mixaron.secuirty.util.errorAdvice.NotFoundProductException;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {


    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Transactional
    public void createCategory(Product product) {
        product.setCategory(categoryRepo.findByCategoryName(product.getCategory().getCategoryName()).orElseThrow(CreateCategoryException::new));
        productRepo.save(product);
    }

    public List<Product> returnAllProducts() {
        return productRepo.findAll();
    }

    public String watchProduct(List<Product> products) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product product : products) {
            stringBuilder.append("Имя: ")
                    .append(product.getName())
                    .append(" Описание: ")
                    .append(product.getDescription()).append(" Цена: ")
                    .append(product.getPrice()).append(" Категория: ")
                    .append(product.getCategory().getCategoryName()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Transactional
    public void deleteProduct(ProductDTO product) {
        Product product1 = productRepo.findByName(product.getName()).orElseThrow(NotFoundEmailException::new);
        productRepo.deleteById(product1.getId());
    }

    public Product oneProduct(String name) {
        Product product = productRepo.findByName(name).orElseThrow(NotFoundProductException::new);
            return productRepo.findByName(name).orElseThrow(NotFoundProductException::new);
    }
}
