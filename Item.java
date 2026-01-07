public class Item implements Weighable {
    private final String itemName;
    private final double weight;

    public Item(String itemName, double weight) {
        this.itemName = itemName;
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return itemName;
    }
}
