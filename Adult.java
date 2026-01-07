public class Adult extends Person {

    public Adult(String name) {
        super(name);
    }

    @Override
    public void loadPersonalItems() {
        items.addAll(
            ItemLoader.loadItemsFromFile("adult_items.txt")
        );
    }

    @Override
    public String getCategory() {
        return "Adult";
    }
}
