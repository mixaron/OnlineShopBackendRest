package ru.mixaron.secuirty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mixaron.secuirty.models.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, Integer> {


    Optional<Product> findByName(String name);

    void deleteById(UUID uuid);
}
