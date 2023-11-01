package ru.mixaron.secuirty.models;

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
    private Person person;

    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "name")
    private Product products;

    @Column(name = "status")
    private String status;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "price")
    private double price;
}
