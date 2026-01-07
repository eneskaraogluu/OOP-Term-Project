import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemLoader {

    public static List<Item> loadItemsFromFile(String fileName) {
        List<Item> items = new ArrayList<>();

        File file = new File("items", fileName);

        if (!file.exists()) {
            System.out.println(fileName + " not found!");
            return items;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) 
                    continue;

                // Format: ItemName;weight
                String[] parts = line.split(";");
                String name = parts[0].trim();
                double weight = Double.parseDouble(parts[1].trim());

                items.add(new Item(name, weight));
            }
        } catch (Exception e) {
            System.out.println("Error reading " + fileName + ": " + e.getMessage());
        }

        return items;
    }
}
