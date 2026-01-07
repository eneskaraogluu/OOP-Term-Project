import java.util.ArrayList;
import java.util.List;

public abstract class Person {

    protected String name;
    protected List<Item> items = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    // Person seviyesinde "base item handling" = item listesini yönetme
    protected void addItem(Item item) {
        items.add(item);
    }

    public abstract String getCategory();
    public abstract void loadPersonalItems();
}
