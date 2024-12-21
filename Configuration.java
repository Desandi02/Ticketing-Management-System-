
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maximumTicketCapacity;

    // Constructor with validation
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maximumTicketCapacity) {
        if (totalTickets <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0 || maximumTicketCapacity <= 0) {
            throw new IllegalArgumentException("All configuration values must be greater than zero.");
        }
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maximumTicketCapacity = maximumTicketCapacity;
    }

    // Getters
    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaximumTicketCapacity() {
        return maximumTicketCapacity;
    }

    // Static method to create configuration from user input
    public static Configuration createConfigurationFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total number of tickets: ");
        int totalTickets = getValidInput(scanner);

        System.out.print("Enter ticket release rate (milliseconds): ");
        int ticketReleaseRate = getValidInput(scanner);

        System.out.print("Enter customer retrieval rate (milliseconds): ");
        int customerRetrievalRate = getValidInput(scanner);

        System.out.print("Enter maximum ticket capacity: ");
        int maximumTicketCapacity = getValidInput(scanner);

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity);
    }

    // Helper method for input validation
    private static int getValidInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value > 0) {
                    return value;
                } else {
                    System.out.print("Value must be greater than zero. Please enter again: ");
                }
            } else {
                System.out.print("Invalid input! Please enter a number: ");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Serialize to JSON
    public String toJson() {
        return """
        totalTickets: %d
        ticketReleaseRate: %d
        customerRetrievalRate: %d
        maximumTicketCapacity: %d
        """.formatted(totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity);
    }

    // Deserialize from JSON
    public static Configuration fromJson(String json) {
        // Remove curly braces and split by commas
        String[] pairs = json.trim().replace("{", "").replace("}", "").split(",");

        int totalTickets = 0, ticketReleaseRate = 0, customerRetrievalRate = 0, maximumTicketCapacity = 0;

        for (String pair : pairs) {
            String[] keyValue = pair.trim().split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replace("\"", ""); // Remove quotes
                String value = keyValue[1].trim();

                switch (key) {
                    case "totalTickets" -> totalTickets = Integer.parseInt(value);
                    case "ticketReleaseRate" -> ticketReleaseRate = Integer.parseInt(value);
                    case "customerRetrievalRate" -> customerRetrievalRate = Integer.parseInt(value);
                    case "maximumTicketCapacity" -> maximumTicketCapacity = Integer.parseInt(value);
                    default -> System.err.println("Unknown key in JSON: " + key);
                }
            } else {
                System.err.println("Invalid key-value pair in JSON: " + pair);
            }
        }

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maximumTicketCapacity);
    }


    @Override
    public String toString() {
        return "CLI.Configuration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maximumTicketCapacity=" + maximumTicketCapacity +
                '}';
    }
}

