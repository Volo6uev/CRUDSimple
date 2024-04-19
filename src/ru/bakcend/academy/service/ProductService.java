package ru.bakcend.academy.service;

import ru.bakcend.academy.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> All();
    void add(String id,String name,String cost,String amount);
    void change(String id,String name,String cost,String amount);
    void removeById(UUID id);
}
