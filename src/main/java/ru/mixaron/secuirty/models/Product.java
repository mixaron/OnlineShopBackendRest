package ru.mixaron.secuirty.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @NotEmpty(message = "Product cannot be empty")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_name")
    @JsonManagedReference
    private Category category;

    @OneToMany(mappedBy = "products")
    @JsonBackReference
    Set<Order> orders;

    public Product(String name) {
        this.name = name;
    }


    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Product(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public Product(String name, String number, double i, Category order) {
        this.name = name;
        description = number;
        price = i;
        this.category = order;
    }
}
