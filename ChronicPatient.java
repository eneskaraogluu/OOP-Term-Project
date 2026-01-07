public class ChronicPatient extends Person {

    public ChronicPatient(String name) {
        super(name);
    }

    @Override
    public void loadPersonalItems() {
        items.addAll(
            ItemLoader.loadItemsFromFile("chronic_items.txt")
        );
    }

    @Override
    public String getCategory() {
        return "Chronic Patient";
    }
}
