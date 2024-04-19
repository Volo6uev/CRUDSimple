package ru.bakcend.academy.ui;

import ru.bakcend.academy.repository.ProductRepository;
import ru.bakcend.academy.repository.ProductRepositoryFileImpl;
import ru.bakcend.academy.service.ProductService;
import ru.bakcend.academy.service.ProductServiceImpl;

import java.util.Scanner;
import java.util.UUID;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductRepository productRepository = new ProductRepositoryFileImpl("products.txt");
    private final ProductService productService = new ProductServiceImpl(productRepository);

    public void start() {
        while (true) {
            printMainMenu();

            String command = scanner.next();

            switch (command) {
                case "exit" -> System.exit(0);
                case "create" -> {
                    String id = scanner.next();
                    String name = scanner.next();
                    String cost = scanner.next();
                    String amount = scanner.next();
                    productService.add(id,name,cost,amount);
                }
                case "update" -> {
                    String id = scanner.next();
                    String name = scanner.next();
                    String cost = scanner.next();
                    String amount = scanner.next();
                    productService.change(id,name,cost,amount);
                }
                case "read" -> {
                    productService.All().forEach(System.out::println);
                }
                case "delete" -> {
                    UUID id = UUID.fromString(scanner.next());
                    productService.removeById(id);
                }
            }
        }
    }

    private void printMainMenu() {
        System.out.println("Enter request");
        System.out.println("**Note id of the product must be UUID");
        System.out.println("If you want to exit type exit");
    }
}
