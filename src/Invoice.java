import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private List<LineItem> lineItems;
    private Customer customer;

    public Invoice(Customer customer) {
        this.lineItems = new ArrayList<>();
        this.customer = customer;
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public void removeLineItem(LineItem item) {
        lineItems.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (LineItem item : lineItems) {
            total += item.calculateTotal();
        }
        return total;
    }

    public String displayInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice for: ").append(customer.getName()).append("\n");
        sb.append("Address: ").append(customer.getAddress()).append("\n");
        sb.append("\nLine Items:\n");
        for (LineItem item : lineItems) {
            sb.append(item.getProduct().getName()).append(": ")
                    .append(item.getQuantity()).append(" x $").append(item.getProduct().getUnitPrice())
                    .append(" = $").append(item.calculateTotal()).append("\n");
        }
        sb.append("\nTotal Amount Due: $").append(calculateTotal());
        return sb.toString();
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
