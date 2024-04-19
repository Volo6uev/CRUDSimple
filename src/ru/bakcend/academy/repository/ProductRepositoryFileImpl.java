package ru.bakcend.academy.repository;

import ru.bakcend.academy.models.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

public class ProductRepositoryFileImpl implements ProductRepository {

    private final String fileName;

    public ProductRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    private static final Function<Product, String> productToString = product ->
            product.getId() + "|" + product.getName() + "|" + product.getCost() + "|" + product.getAmount();

    private static final Function<String, Product> stringToProduct = string -> new Product(
            UUID.fromString(string.split("\\|")[0]),
            string.split("\\|")[1],
            Integer.parseInt(string.split("\\|")[2]),
            Integer.parseInt(string.split("\\|")[3])
    );

    private static final Function<Product, String> userToStringWithoutId = product ->
            product.getName()
                    + "|" + product.getCost()
                    + "|" + product.getAmount();


    @Override
    public List<Product> findAll() {
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            List<Product> products;
            products = lines.map(stringToProduct).toList();
            return products;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(Product entity) {
        try (Writer writer = new FileWriter(fileName,true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)){
            String productAsString = productToString.apply(entity);
            bufferedWriter.write(productAsString);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void updateById(Product entity) {
        File tempFile = new File("TempFile.txt");
        try (Reader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader);
             Writer writer = new FileWriter(tempFile,true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (currentLine.startsWith(String.valueOf(entity.getId()))) {
                    currentLine = productToString.apply(entity);
                }
                bufferedWriter.write(currentLine);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        boolean successful = tempFile.renameTo(new File(fileName));

    }

    @Override
    public void deleteById(UUID id) {
        File tempFile = new File("myTempFile.txt");
        try (Reader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader);
             Writer writer = new FileWriter(tempFile,true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            String currentLine;
            String productId = id.toString();
            while ((currentLine = bufferedReader.readLine()) != null) {
                if (currentLine.startsWith(productId)) {
                    continue;
                }
                bufferedWriter.write(currentLine);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        boolean successful = tempFile.renameTo(new File(fileName));
    }
}
