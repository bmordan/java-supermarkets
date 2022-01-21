import java.util.Stack;

public class Shopper {
    private Stack<Product> trolly = new Stack<>();

    public void addProduct(Product product) {
        this.trolly.push(product);
    }

    public Product getProduct() {
        return this.trolly.pop();
    }

    public boolean hasProducts() {
        return !trolly.isEmpty();
    }
}
