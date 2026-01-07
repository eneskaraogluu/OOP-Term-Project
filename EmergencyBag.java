import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmergencyBag implements Weighable {

    private final List<Item> items;
    private final double maxWeight;

    public EmergencyBag(double maxWeight) {
        this.maxWeight = maxWeight;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) throws OverweightBagException {
        double newTotalWeight = getWeight() + item.getWeight();

        if (newTotalWeight > maxWeight) {
            throw new OverweightBagException("Bag weight limit exceeded!");
        }

        items.add(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    @Override
    public double getWeight() {
        double total = 0;
        for (Item item : items) total += item.getWeight();
        return total;
    }
}
