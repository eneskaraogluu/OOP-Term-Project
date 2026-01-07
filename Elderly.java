public class Elderly extends Person {

    public Elderly(String name) {
        super(name);
    }

    @Override
    public void loadPersonalItems() {
        items.addAll(
            ItemLoader.loadItemsFromFile("elderly_items.txt")
        );
    }

    @Override
    public String getCategory() {
        return "Elderly";
    }
}
