package ru.mixaron.secuirty.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.mixaron.secuirty.models.Order;
import ru.mixaron.secuirty.models.Person;
import ru.mixaron.secuirty.models.Product;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO extends Order {


    @NotNull(message = "Person cannot be null")
    private Person person;


    @NotNull(message = "Product cannot be null")

    private Product products;


    private String status;

    @NotNull(message = "Price cannot be null")
    private double price;

    public OrderDTO(Person person, Product product, String status) {
        this.person = person;
        this.products = product;
        this.status = status;
    }
}
