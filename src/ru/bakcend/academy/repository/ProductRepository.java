package ru.bakcend.academy.repository;

import ru.bakcend.academy.models.Product;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<UUID, Product> {
}
