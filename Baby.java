public class Baby extends Person {

    public Baby(String name) {
        super(name);
    }

    @Override
    public void loadPersonalItems() {
        items.addAll(
            ItemLoader.loadItemsFromFile("baby_items.txt")
        );
    }

    @Override
    public String getCategory() {
        return "Baby";
    }
}
