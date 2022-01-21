public class Product {
    private double RRP;
    private String desc;

    public Product(String desc, double RRP) {
        this.desc = desc;
        this.RRP = RRP;
    }

    public double getPrice() {
        return this.RRP;
    }

    public int getBarcode() {
        return new String(this.desc).hashCode();
    }
}
