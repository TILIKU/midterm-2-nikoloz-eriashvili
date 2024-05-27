import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bookstore {
    private String name;
    private List<Publisher> publishers = new ArrayList<>();

    public Bookstore(String name) {
        this.name = name;
    }


    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    public boolean removePublisher(Publisher publisher) {
        return publishers.remove(publisher);
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    @Override
    public String toString() {
        return "Bookstore{name='" + name + "', publishers=" + publishers + "}";
    }

    public void saveState() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("state.csv"))) {
            for (Publisher publisher : publishers) {
                writer.write(publisher.getName() + ";" + publisher.getAddress());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreState() {
        publishers.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("state.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");//i use ";" to seperate because some addresses might have "," and it messes the whole thing up
                if (parts.length == 2) {
                    publishers.add(new Publisher(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // Create a new bookstore and restore the state
        // We create new bookstore so our current bookstore wont overwrite the restored bookstore
        Bookstore restoredBookstore = new Bookstore("Restored Bookstore");
        restoredBookstore.restoreState();

        // Create publishers
        Publisher pub1 = new Publisher("Penguin Random House", "New York, NY");
        Publisher pub2 = new Publisher("HarperCollins", "New York, NY");

        // Create a bookstore
        Bookstore bookstore = new Bookstore("City Bookstore");

        // Add publishers to the bookstore
        bookstore.addPublisher(pub1);
        bookstore.addPublisher(pub2);

        // test setters
        pub2.setAddress("gamer street");
        pub1.setName("cool guys place");

        System.out.println(pub1.getName());
        System.out.println(pub2.getAddress());

        //print all bookstores
        System.out.println(bookstore);
        System.out.println(restoredBookstore);

        // Save the state at the end because we want to save the last state of our app
        bookstore.saveState();
    }
}
