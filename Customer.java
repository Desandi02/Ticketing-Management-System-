
public class Customer implements Runnable {
    private com.TicketingManagementSystem.Ticketing.Management.System.model.TicketPool ticketPool;
    private int customerRetrievalRate;
    private int quantity;

    public Customer(com.TicketingManagementSystem.Ticketing.Management.System.model.TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            com.TicketingManagementSystem.Ticketing.Management.System.model.Ticket ticket = ticketPool.buyTicket();
            System.out.println("CLI.Customer bought a CLI.Ticket - " + ticket + " - CLI.Customer name is - " + Thread.currentThread().getName());
            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
