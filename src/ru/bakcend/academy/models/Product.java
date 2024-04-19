package ru.bakcend.academy.models;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private Integer cost;
    private Integer amount;

    public Product(UUID id, String name, Integer cost, Integer amount) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.amount = amount;
    }

    public Product(String name, Integer cost, Integer amount) {
        this.name = name;
        this.cost = cost;
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, amount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", amount=" + amount +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
