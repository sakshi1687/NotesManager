import java.io.*;
import java.util.*;
public class Notes {
    private static final String FILE_NAME = "notes.txt";
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("      Notes Manager App" );
        System.out.println("  Manage Your Notes with Easy!");
        System.out.println("===================================");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Search Notes");
            System.out.println("4. Delete All Notes");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addNote();
                    break;
                case "2":
                    viewNotes();
                    break;
                case "3":
                    searchNotes();
                    break;
                case "4":
                    deleteAllNotes();
                    break;
                case "5":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    private static void addNote() {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(note + System.lineSeparator());
            System.out.println("  Note added successfully!");
        } catch (IOException e) {
            System.err.println("  Error writing to file: " + e.getMessage());
        }
    }
    private static void viewNotes() {
        System.out.println("\n--- All Notes ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                count++;
                System.out.println(count + ". " + line);
            }
            if (count == 0) {
                System.out.println("(No notes found)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("(No notes file found yet)");
        } catch (IOException e) {
            System.err.println("  Error reading file: " + e.getMessage());
        }
    }
    private static void searchNotes() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                count++;
                if (line.toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println("Found in Note " + count + ": " + line);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No matching notes found for: " + keyword);
            }
        } catch (FileNotFoundException e) {
            System.out.println("(No notes file found yet)");
        } catch (IOException e) {
            System.err.println("  Error reading file: " + e.getMessage());
        }
    }
    private static void deleteAllNotes() {
        File file = new File(FILE_NAME);
        if (file.delete()) {
            System.out.println(" All notes deleted successfully!");
        } else {
            System.out.println("No notes to delete.");
        }
    }
}