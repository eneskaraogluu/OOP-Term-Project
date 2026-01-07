import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US); // decimal consistency

        while (true) {
            System.out.println("\n=== SMART EARTHQUAKE EMERGENCY BAG ASSISTANT ===");
            System.out.println("1 - Create New Earthquake Bag");
            System.out.println("2 - Add New Item to Category");
            System.out.println("0 - Exit");
            System.out.print("Your choice: ");

            int choice = readInt(input);

            switch (choice) {
                case 1:
                    createBag(input);
                    pressEnter(input);
                    break;
                case 2:
                    addNewItemToCategory(input);
                    pressEnter(input);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    /* ================= CREATE BAG ================= */

    private static void createBag(Scanner input) {

        System.out.print("\nHow many family members are there? ");
        int count = readInt(input);

        Person[] people = new Person[count];

        for (int i = 0; i < count; i++) {
            System.out.print("\n" + (i + 1) + ". person's name: ");
            String name = input.nextLine();

            System.out.print("Select category: 1-Adult 2-Elderly 3-Baby 4-Chronic patient ");
            int cat = readInt(input);

            switch (cat) {
                case 1:
                    people[i] = new Adult(name);
                    break;
                case 2:
                    people[i] = new Elderly(name);
                    break;
                case 3:
                    people[i] = new Baby(name);
                    break;
                case 4:
                    people[i] = new ChronicPatient(name);
                    break;
                default:
                    System.out.println("Invalid category!");
                    i--;
            }
        }

        System.out.print("\nRisk level: 1-HIGH 2-MEDIUM 3-LOW ");
        int riskChoice = readInt(input);

        RiskLevel riskLevel;
        switch (riskChoice) {
            case 1:
                riskLevel = RiskLevel.HIGH;
                break;
            case 2:
                riskLevel = RiskLevel.MEDIUM;
                break;
            case 3:
                riskLevel = RiskLevel.LOW;
                break;
            default:
                System.out.println("Invalid risk level!");
                return;
        }

        System.out.print("\nBag capacity (kg): ");
        double capacity = readDouble(input);

        EmergencyBag bag = new EmergencyBag(capacity);

        try {
            String baseFile = getBaseFileByRisk(riskLevel);
            List<Item> baseItems = ItemLoader.loadItemsFromFile(baseFile);

            for (Person p : people) {
                for (Item item : baseItems) {
                    bag.addItem(item);
                }

                p.loadPersonalItems();
                for (Item item : p.getItems()) {
                    bag.addItem(item);
                }
            }
}       catch (OverweightBagException e) {
            System.out.println("Warning: " + e.getMessage());
}


        printSummary(people, bag);
    }

    /* ================= ADD NEW ITEM ================= */

    private static void addNewItemToCategory(Scanner input) {

        System.out.println("\nSelect category:");
        System.out.println("1- Adult");
        System.out.println("2- Baby");
        System.out.println("3- Elderly");
        System.out.println("4- Chronic patient");
        System.out.print("Your choice: ");

        int cat = readInt(input);

        String fileName;
        switch (cat) {
            case 1:
                fileName = "adult_items.txt";
                break;
            case 2:
                fileName = "baby_items.txt";
                break;
            case 3:
                fileName = "elderly_items.txt";
                break;
            case 4:
                fileName = "chronic_items.txt";
                break;
            default:
                System.out.println("Invalid category!");
                return;
        }

        System.out.print("\nItem name: ");
        String name = input.nextLine();

        System.out.print("Weight (e.g., 0.5): ");
        double weight = readDouble(input);

        if (weight <= 0) {
            System.out.println("Weight must be a positive number!");
            return;
        }

        File itemsDir = new File("items");
        if (!itemsDir.exists()) itemsDir.mkdirs();

        File file = new File(itemsDir, fileName);

        try (FileWriter fw = new FileWriter(file, true)) {

            if (file.length() > 0) {
                fw.write(System.lineSeparator());
            }

            fw.write(name + ";" + weight);
            System.out.println("\nItem appended to file.");

        } catch (Exception e) {
            System.out.println("\nFile write error!");
        }
    }

    /* ================= SUMMARY ================= */

    private static void printSummary(Person[] people, EmergencyBag bag) {

        System.out.println("\n==== BAG SUMMARY ====");
        System.out.println("Family members:");
        for (Person p : people) {
            System.out.println("- " + p.getName() + " (" + p.getCategory() + ")");
        }

        System.out.println("\nItems:");
        List<Item> items = bag.getItems();

        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            System.out.printf("%d. %-25s %6s kg%n",
                    i + 1,
                    it.getItemName(),
                    formatKg(it.getWeight()));
        }

        System.out.println("\nTotal Weight: " + formatKg(bag.getWeight()) + " kg");
        System.out.println("Maximum Capacity: " + formatKg(bag.getMaxWeight()) + " kg");

        System.out.println(
                "\nStatus: " +
                (bag.getWeight() <= bag.getMaxWeight()
                        ? "[OK] Within limit"
                        : "[X] Over limit")
        );
    }

    /* ================= HELPERS ================= */

    private static String getBaseFileByRisk(RiskLevel riskLevel) {
        switch (riskLevel) {
            case HIGH:
                return "base_items_high.txt";
            case MEDIUM:
                return "base_items_medium.txt";
            case LOW:
                return "base_items_low.txt";
            default:
                throw new IllegalStateException("Unexpected risk level");
        }
    }

    private static String formatKg(double w) {
        return String.format(Locale.GERMAN, "%.2f", w);
    }

    private static int readInt(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Please enter a valid integer: ");
            input.nextLine();
        }
        int v = input.nextInt();
        input.nextLine();
        return v;
    }

    private static double readDouble(Scanner input) {
        while (!input.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            input.nextLine();
        }
        double v = input.nextDouble();
        input.nextLine();
        return v;
    }

    private static void pressEnter(Scanner input) {
        System.out.print("\nPress Enter to return to the menu...");
        input.nextLine();
    }
}
