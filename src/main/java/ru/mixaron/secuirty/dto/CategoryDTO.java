package ru.mixaron.secuirty.dto;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import ru.mixaron.secuirty.models.Product;

import java.util.Set;


@Getter
@Setter
public class CategoryDTO {

    @NotEmpty(message = "Category name cannot be empty")
    private String categoryName;

}
