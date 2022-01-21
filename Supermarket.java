import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Supermarket {
    private ArrayList<Product> inventory = new ArrayList<>();
    private HashMap<Integer, Integer> stock = new HashMap<>();
    private ArrayDeque<Shopper> checkout = new ArrayDeque<>();

    public Supermarket() {
        // Create add
        inventory.add(new Product("Coffee", 2.79));
        inventory.add(new Product("Tea", 1.49));
        inventory.add(new Product("Biscuits", 1.99));
        inventory.add(new Product("Oat Milk", 3.79));
        inventory.add(new Product("Milk", 0.79));
        for(Product product : inventory) {
            // Create .put(Key , Value);
            stock.put(product.getBarcode(), 10);
        }
        
        Shopper shopper1 = new Shopper();
        Shopper shopper2 = new Shopper();

        shopper1.addProduct(inventory.get(0));
        shopper1.addProduct(inventory.get(1));
        shopper1.addProduct(inventory.get(2));
        shopper1.addProduct(inventory.get(3));
        shopper2.addProduct(inventory.get(0));
        shopper2.addProduct(inventory.get(1));
        shopper2.addProduct(inventory.get(1));
        shopper2.addProduct(inventory.get(4));

        checkout.addLast(shopper1);
        checkout.addLast(shopper2);
        
        processCheckoutQueue();

        System.out.println("end");
    }

    private void processCheckoutQueue() {
        while(!checkout.isEmpty()) {
            Shopper shopper = checkout.pollFirst();
            double total = 0;
            while(shopper.hasProducts()) {
                Product product = shopper.getProduct();
                int barcode = product.getBarcode();
                total += product.getPrice();
                stock.replace(barcode, stock.get(barcode) - 1);
            }
            System.out.printf("Shopper %s total: %.2f\n", shopper.toString(), total);
        }
    }
    public static void main(String[] args) throws Exception {
        new Supermarket();
    }
}
