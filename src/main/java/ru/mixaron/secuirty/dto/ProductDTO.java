package ru.mixaron.secuirty.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.mixaron.secuirty.models.Category;
@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    @NotNull(message = "name cannot be null")
    private String name;

    private String description;

    @NotNull(message = "Price cannot be null")
    private double price;

    @NotNull(message = "Category cannot be null")
    private Category category;

}
