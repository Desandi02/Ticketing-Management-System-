
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private int maximumTicketCapacity;
    private Queue<Ticket> ticketQueue;

    public TicketPool(int maximumTicketCapacity) {
        this.maximumTicketCapacity = maximumTicketCapacity;
        this.ticketQueue = new LinkedList<>();
    }

    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ticketQueue.add(ticket);
        notifyAll();
        System.out.println("CLI.Vendor added a CLI.Ticket - " + Thread.currentThread().getName() + " - current size is - " + ticketQueue.size());
    }

    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Ticket ticket = ticketQueue.poll();
        notifyAll();
        System.out.println("CLI.Customer purchased a CLI.Ticket - " + Thread.currentThread().getName() + " - current size is - " + ticketQueue.size());
        return ticket;
    }
}

