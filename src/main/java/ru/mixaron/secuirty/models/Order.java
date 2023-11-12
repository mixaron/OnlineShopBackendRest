package ru.mixaron.secuirty.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "person_email", referencedColumnName = "email")
    @JsonManagedReference
    private Person person;

    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "name")
    @JsonManagedReference
    private Product products;

    @Column(name = "status")
    private String status;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "price")
    private double price;

    public Order(Person person, Product name, String status) {
        this.person = person;
        this.products = name;
        this.status = status;
    }
    public Order(UUID uuid, Person person, Product name, String status) {
        this.id = id;
        this.person = person;
        this.products = name;
        this.status = status;
    }


}
