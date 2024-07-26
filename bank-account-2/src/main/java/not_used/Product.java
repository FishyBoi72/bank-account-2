package not_used;

public class Product {

    private double productCost;
    private int quantity;
    private String productName;

    // Constructor
    public Product(String productName, double productCost, int quantity) {
        this.productName = productName;
        this.productCost = productCost;
        this.quantity = quantity;
    }

    public void totalCost() {
        double total = productCost * quantity;
        System.out.println("Total cost for " + quantity + " units of " + productName + ": $" + total);
    }

    public void printProduct() {
        System.out.println("Product: " + productName);
        System.out.println("Cost per unit: $" + productCost);
        System.out.println("Quantity purchased: " + quantity);
    }
}