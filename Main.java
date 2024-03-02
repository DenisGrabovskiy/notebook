import java.util.*;

class Laptop {
    String brand;
    int ram;
    int storageSize;
    String os;
    String color;

    public Laptop(String brand, int ram, int storageSize, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storageSize = storageSize;
        this.os = os;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Brand: " + brand + ", RAM: " + ram + "GB, Storage Size: " + storageSize + "GB, OS: " + os + ", Color: " + color;
    }

    public boolean matchesCriteria(Map<String, Object> criteria) {
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case "RAM":
                    if (ram < (int) value) return false;
                    break;
                case "StorageSize":
                    if (storageSize < (int) value) return false;
                    break;
                case "OS":
                    if (!os.equals(value)) return false;
                    break;
                case "Color":
                    if (!color.equals(value)) return false;
                    break;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 8, 512, "Windows 10", "Silver"));
        laptops.add(new Laptop("HP", 16, 256, "Ubuntu", "Black"));
        laptops.add(new Laptop("Apple", 16, 512, "macOS", "Space Gray"));
        laptops.add(new Laptop("Dell", 4, 1000, "Windows 7", "Red"));
        laptops.add(new Laptop("LENOVO", 2, 126, "Windows XP", "Black"));
        laptops.add(new Laptop("ACER", 32, 2000, "Windows 11", "Red"));

        Map<String, Object> filterCriteria = new HashMap<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Фильтр что ищем:");
            System.out.println("1 - Память");
            System.out.println("2 - Жесткий диск");
            System.out.println("3 - Операционка");
            System.out.println("4 - Цвет");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Введите минимум памяти:");
                    int minRam = scanner.nextInt();
                    filterCriteria.put("RAM", minRam);
                    break;
                case 2:
                    System.out.println("Введите минимум жесткий диск:");
                    int minStorageSize = scanner.nextInt();
                    filterCriteria.put("StorageSize", minStorageSize);
                    break;
                case 3:
                    System.out.println("Введите Операционку:");
                    String os = scanner.next();
                    filterCriteria.put("OS", os);
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    String color = scanner.next();
                    filterCriteria.put("Color", color);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        for (Laptop laptop : laptops) {
            if (laptop.matchesCriteria(filterCriteria)) {
                System.out.println(laptop);
            }
        }
    }
}
