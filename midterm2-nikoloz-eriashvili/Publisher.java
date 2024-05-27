public class Publisher {
    private String name;
    private String address;

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void displayPublisherInfo() {
        System.out.println("Publisher Information:");
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
    }
    @Override
    public String toString() {
        return "\n Publisher{name='" + name + "', address='" + address + "'}";
    }
}