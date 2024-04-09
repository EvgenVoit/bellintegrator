package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task5 implements Basket {

    private final Map<String, Integer> products;

    public Task5() {
        this.products = new HashMap<>();
    }

    public static void main(String[] args) {
        Basket basket = new Task5();
        basket.addProduct("Coca-Cola", -150);
        basket.addProduct("Coca-Cola", 650);
        basket.addProduct("Apple", 500);
        basket.addProduct("Bread", 1000);
        basket.addProduct("Fish", 350);
        basket.addProduct("Fish",900);


        List<String> products = basket.getProducts();
        System.out.println("List of products: ");
        for (String product : products) {
            int quantity = basket.getProductsQuantity(product);
            System.out.println("Product - " + product + " |" + " Quantity - " + quantity);
        }

        String productToMakeOptions = "Apple";
        basket.updateProductQuantity(productToMakeOptions, 1000);
        System.out.println("\nQuantity for " + productToMakeOptions + " after update - " +
                basket.getProductsQuantity(productToMakeOptions));

        basket.removeProduct(productToMakeOptions);
        System.out.println("\nList of products after remove " +productToMakeOptions + " - " + basket.getProducts());

    }

    @Override
    public void addProduct(String product, int quantity) {
        if(quantity>=0){
            products.put(product, products.getOrDefault(product, 0) + quantity);
        } else {
            System.out.println("\nInvalid input for quantity!\n");
        }

    }

    @Override
    public void removeProduct(String product) {
        products.remove(product);
    }

    @Override
    public void updateProductQuantity(String product, int quantity) {
        if(quantity>=0){
            products.put(product, quantity);
        } else {
            System.out.println("\nInvalid input for quantity!");
        }

    }

    @Override
    public void clear() {
        products.clear();
    }

    @Override
    public List<String> getProducts() {
        return new ArrayList<>(products.keySet());

    }

    @Override
    public int getProductsQuantity(String product) {
        return products.getOrDefault(product, 0);
    }

}