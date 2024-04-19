package ru.bakcend.academy.service;

import ru.bakcend.academy.models.Product;
import ru.bakcend.academy.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> All() {
        return productRepository.findAll();
    }

    @Override
    public void add(String id, String name, String cost, String amount) {
        Product product = new Product(UUID.fromString(id),name,Integer.valueOf(cost),Integer.valueOf(amount));
        productRepository.save(product);
    }

    @Override
    public void change(String id, String name, String cost, String amount) {
        Product product = new Product(UUID.fromString(id),name,Integer.valueOf(cost),Integer.valueOf(amount));
        productRepository.updateById(product);
    }

    @Override
    public void removeById(UUID id) {
        productRepository.deleteById(id);
    }
}
